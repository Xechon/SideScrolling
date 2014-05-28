import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by shann_000 on 5/8/2014.
 */

//tidy up code, change all instances to private, provide getters and setters, comment, sort code by type and alphabetically
public abstract class GameObject {
    private ArrayList<Image> images = new ArrayList<Image>(); //Possibly make array?
    private ArrayList<GameObject> things = new ArrayList<GameObject>();
    private Image currentImage; //use index instead?
    private Vector position;
    private int width, height, imgWidth, imgX; //make points?
    private Point2D.Double rotationAnchor; //maybe separate?
    private double angle, weight; //make vectors?
    private boolean grounded, facingRight; //maybe don't need these?
    private Rectangle hitbox;
    private AffineTransform at = new AffineTransform();
    private Point mouseLocation;
    private Item hoveredItem;

    public GameObject(double x, double y, int w, int h){
        position = new Vector(x,y);
        width = w;
        height = h;
        hitbox = new Rectangle(position.getXLocation(), position.getYLocation(), width, height);
        imgWidth = width;
        setRotationAnchor(width / 2, height / 2);
    }

    public void draw(Graphics2D g2){
        g2.setTransform(at);
        hitbox = new Rectangle(position.getXLocation(),position.getYLocation(), width,height);
        if(Main.getDebug()) {
            g2.draw(hitbox);
        }
        g2.drawImage(currentImage, position.getXLocation() + imgX, position.getYLocation(), imgWidth, height, null);
    }

    public void update(ArrayList<GameObject> things){
        this.things = things;
        if(!grounded){
            position.setYVelocity(position.getYVelocity() + Background.getGravity());
        }
        else{
            position.setYVelocity(0);
        }
        grounded = checkIfGrounded(things);
        move();
        for(GameObject e: things){
            highlightItem(e);
        }
    }

    public void move(){
        position.update();
    }

    //returns false positives
    public boolean checkIfGrounded(ArrayList<GameObject> things){
        for(GameObject e: things){
            double a = isColliding(e)[1];
            if(a < height && a > 0){
                position.setY(e.getPosition().getY() - height + 1);
                e.isBeingStoodOn();
                return true;
            }
            else if(!grounded && a > e.getHeight()){
                position.setY(e.getPosition().getY() + e.getHeight() + 1);
            }
        }
        return false;
    }

    //always (incorrectly) returns false
    public boolean checkIfCanMove(ArrayList<GameObject> things){
        for(GameObject e: things){
            if((isColliding(e)[0] > 0 && facingRight) || (isColliding(e)[0] < 0 && !facingRight)){
                return true;
            }
        }
        return false;
    }

    public void checkForGround(){
        //only called for if not grounded
        //looks for collidable things from current location to that plus xVel and yVel
        //if it finds one, it stops looking and puts you there on the next update instead of move(), and calls a hitGround
        //method with the remaining velocities as parameters
        //Line2D.Double velLine = new Line2D.Double(rotationAnchor, new Point2D.Double(rotationAnchor.getX() + xVelocity, rotationAnchor.getY() + yVelocity));
        //if (velLine.intersects())
    }

    public void collide(double xVel, double yVel){
        //calculates force and angle of collision from parameters, and applies any applicable affects based on these
    }

    public boolean updateRotation(){
        double deltaY = getMouseLocation().getY() - (position.getY() + rotationAnchor.getY());
        double deltaX = getMouseLocation().getX() - (position.getX() + rotationAnchor.getX());
        setAngle(Math.atan2(deltaY, deltaX));
        at.setToRotation(getAngle(), position.getX() + rotationAnchor.getX(), position.getY() + rotationAnchor.getY());
        if(Math.abs(position.getAngle()) > Math.PI/2){
            setFacingRight(false);
        }
        else{
            setFacingRight(true);
        }
        return facingRight;
    }

    //should return distance and angle between centers, should be renamed, should correctly check if two GameObjects are equal
    public double[] isColliding(GameObject thing){
        //positive on right or down, negative left or up, in left/right, up/down order. Integers represent difference
        //between starting point of the two objects, or {0,0} if it doesn't matter.
        double[] rightDown = new double[2];
            if(thing.getHitbox().intersects(hitbox)){
                rightDown[0] = thing.getPosition().getX() - position.getX();
                rightDown[1] = thing.getPosition().getY() - position.getY();
            }
        return rightDown;
    }

    public void isBeingStoodOn(){
        //sounds or other events for being stood on go here
    }

    public void setFacingRight(boolean b){
        facingRight = b;
        if(b){
            imgWidth = Math.abs(imgWidth);
            imgX = 0;
        }
        else{
            imgWidth = -Math.abs(imgWidth);
            imgX = width;
        }
    }

    public void setGrounded(boolean b){
        grounded = b;
    }

    public void setImages(String a, ArrayList<String> i){
        for(String e: i) {
            images.add(Toolkit.getDefaultToolkit().createImage("Resources/" + a + "/" + e + ".gif"));
        }
        setCurrentImage(0);
    }

    public void setCurrentImage(int index){
        currentImage = images.get(index);
    }

    public boolean isGrounded(){
        return grounded;
    }

    public boolean isFacingRight(){
        return facingRight;
    }

    //just making things complicated making the hitbox a shape
    public Shape getHitbox(){
        return hitbox;
    }

    int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public void toggleDebug(){
        Main.setDebug(!Main.getDebug());
    }

    public void setRotationAnchor(int x, int y){
        rotationAnchor = new Point2D.Double(x, y);
    }

    public AffineTransform getAt(){
        return at;
    }

    public Point2D.Double getRotationAnchor(){
        return rotationAnchor;
    }

    public ArrayList<GameObject> getThings(){
        return things;
    }

    public void setAt(AffineTransform a){
        at = a;
    }

    public Vector getPosition(){
        return position;
    }

    public double getAngle(){
        return angle;
    }

    public void setAngle(double a){
        angle = a;
    }

    public Point getMouseLocation(){
        return mouseLocation;
    }

    public void setMouseLocation(Point position){
        mouseLocation = position;
    }

    public void highlightItem(GameObject thing){
        if(thing instanceof Item && ((Item) thing).pickupable && mouseLocation != null && thing.getHitbox().contains(mouseLocation)){
            hoveredItem = (Item) thing;
        }
    }

    public Item getHoveredItem(){
        return hoveredItem;
    }

    public void setHoveredItem(Item thing){
        hoveredItem = thing;
    }

    public void act(){

    }
}
