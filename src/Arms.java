import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by shann_000 on 5/14/2014.
 */
public class Arms extends Item {
    private Item heldItem;
    public Arms(int x, int y, int w, int h){
        super(x,y,w,h);
        setRotationAnchor(w/8, h/2);
    }

    public void setHeldItem(Item i){
        heldItem = i;
        heldItem.getPosition().setX(getPosition().getX() + getWidth());
        heldItem.getPosition().setY(getPosition().getY() + getHeight() / 2);
    }

    public Item getHeldItem(){
        return heldItem;
    }

    @Override
    public void draw(Graphics2D g2){
        super.draw(g2);
        if(heldItem != null){
            heldItem.draw(g2);
        }
    }

    @Override
    public void update(int x, int y, Point p){
        super.update(x,y,p);
        if(heldItem != null) {
            heldItem.setAt(getAt());
            heldItem.update(getPosition().getXLocation() + 3 * getWidth() / 4, y - getHeight(), getMouseLocation());
        }
    }

    public void toss() {
        if (heldItem != null) {
            heldItem.getPosition().setPolarVelocity(10, getAngle());
            heldItem.isTossed(true);
            heldItem.setRotationAnchor(heldItem.getWidth()/2, heldItem.getHeight()/2);
            heldItem = null;
        }
    }
}
