import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * Created by hempstead on 5/19/2014.
 */

//A class to encompass vectors given in polar or cartesian coordinate systems.
public class Vector implements Serializable{
    private Point2D.Double position;
    private Point2D.Double cartesianVelocity;
    private Point2D.Double polarVelocity;

    public Vector(){
        position = new Point2D.Double(0,0);
        cartesianVelocity = new Point2D.Double(0,0);
        polarVelocity = new Point2D.Double(0,0);
    }

    public Vector(double xPosition, double yPosition){
        position = new Point2D.Double(xPosition,yPosition);
        cartesianVelocity = new Point2D.Double(0,0);
        polarVelocity = new Point2D.Double(0,0);
    }

    public Vector(Point2D.Double pos, double xVelocity, double yVelocity){
        position = pos;
        cartesianVelocity = new Point2D.Double(xVelocity,yVelocity);
        polarVelocity = new Point2D.Double(findSpeed(xVelocity,yVelocity), findAngle(xVelocity,yVelocity));
    }

    public Vector(double xPosition, double yPosition, double speed, double angle){
        position = new Point2D.Double(xPosition, yPosition);
        polarVelocity = new Point2D.Double(speed, angle);
        cartesianVelocity = new Point2D.Double(findXVelocity(speed,angle), findYVelocity(speed,angle));
    }

    public static double findSpeed(double xVelocity, double yVelocity){
        return Math.sqrt((xVelocity*xVelocity) + (yVelocity*yVelocity));
    }

    public static double findAngle(double xVelocity, double yVelocity){
        return Math.atan2(yVelocity, xVelocity);
    }

    public static double findXVelocity(double speed, double angle){
        return speed*Math.cos(angle);
    }

    public static double findYVelocity(double speed, double angle){
        return speed*Math.sin(angle);
    }

    public static Point2D.Double findCartesianVelocity(double speed, double angle){
        return new Point2D.Double(findXVelocity(speed,angle), findYVelocity(speed,angle));
    }

    public static Point2D.Double findPolarVelocity(double xVelocity, double yVelocity){
        return new Point2D.Double(findSpeed(xVelocity,yVelocity), findAngle(xVelocity,yVelocity));
    }

    public Point2D.Double getPosition(){
        return position;
    }

    public Point2D.Double getCartesianVelocity(){
        return cartesianVelocity;
    }

    public Point2D.Double getPolarVelocity() {
        return polarVelocity;
    }

    public double getX(){
        return position.getX();
    }

    public int getXLocation(){
        return (int) getX();
    }

    public double getY(){
        return position.getY();
    }

    public int getYLocation(){
        return (int) getY();
    }

    public double getXVelocity(){
        return cartesianVelocity.getX();
    }

    public double getYVelocity(){
        return cartesianVelocity.getY();
    }

    public double getSpeed(){
        return polarVelocity.getX();
    }

    public double getAngle(){
        return polarVelocity.getY();
    }

    public void setPosition(Point2D.Double position){
        this.position = position;
    }

    public void setPosition(double x, double y){
        position = new Point2D.Double(x,y);
    }

    public void setCartesianVelocity(Point2D.Double cartesianVelocity){
        this.cartesianVelocity = cartesianVelocity;
        polarVelocity = findPolarVelocity(findSpeed(getXVelocity(), getYVelocity()), findAngle(getXVelocity(), getYVelocity()));
    }

    public void setCartesianVelocity(double xVelocity, double yVelocity){
        setCartesianVelocity(new Point2D.Double(xVelocity,yVelocity));
    }

    public void setPolarVelocity(Point2D.Double polarVelocity){
        this.polarVelocity = polarVelocity;
        cartesianVelocity = findCartesianVelocity(findXVelocity(getSpeed(),getAngle()), findYVelocity(getSpeed(), getAngle()));
    }

    public void setPolarVelocity(double speed, double angle){
        setPolarVelocity(new Point2D.Double(speed, angle));
    }

    public void setX(double x){
        setPosition(x, position.getY());
    }

    public void setY(double y){
        setPosition(position.getX(), y);
    }

    public void setXVelocity(double xVelocity){
        setCartesianVelocity(xVelocity, cartesianVelocity.getY());
    }

    public void setYVelocity(double yVelocity){
        setCartesianVelocity(cartesianVelocity.getX(), yVelocity);
    }

    public void setSpeed(double speed){
        setPolarVelocity(speed,polarVelocity.getY());
    }

    public void setAngle(double angle){
        setPolarVelocity(polarVelocity.getX(), angle);
    }

    public void update(){
        position.setLocation(position.getX() + cartesianVelocity.getX(), position.getY() + cartesianVelocity.getY());
    }
}
