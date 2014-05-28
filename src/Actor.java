import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by hempstead on 5/8/2014.
 */

public abstract class Actor extends GameObject{
    public boolean walking;

    private double speed;
    private Item[] appendages;

    private Item[] inventory = new Item[10];
    private int selectedItem = 0;

    private int health = 3;
    private boolean dead;

    private Point focus;

    public Actor(int x, int y, int width, int height){
        super(x, y, width, height);
    }

    @Override
    public void update(ArrayList<GameObject> things){
        if(walking){
            getPosition().setXVelocity(speed);
        }
        else{
            getPosition().setXVelocity(0);
        }
        super.update(things);
    }

    public void jump(){
        getPosition().setYVelocity(-20);//yVelocity is constantly being set to 0 by GameObject.act() because
                                        //GameObject.checkIfGrounded() returns false positives.
        setGrounded(false);//Shouldn't need to do this.
    }

    public void changeSelectedItem(int slot){
        selectedItem = slot;
    }

    public void setIsWalking(boolean b){
        walking = b;
    }

    public void setSpeed(double s){
        speed = s;
    }

    @Override
    public void draw(Graphics2D g2){
        super.draw(g2);
        for(Item e: appendages) {
            if(e != null) {
                e.draw(g2);
            }
        }
    }

    public boolean isWalking(){
        return walking;
    }

    public void pickupItem(){

    }

    public Item[] getAppendages(){
        return appendages;
    }

    public void setAppendages(int i){
        appendages = new Item[i];
    }

    public double getSpeed(){
        return speed;
    }

    public void hurt(double a){
        health -= a/9;
        if(health <= 0){
            dead = true;
        }
    }

    public boolean isDead(){
        return dead;
    }

    public void setFocus(Point p){
        focus = p;
    }

    public Point getFocus(){
        return focus;
    }
}
