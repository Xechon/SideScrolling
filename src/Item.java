import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Created by hempstead on 5/9/2014.
 */

//probably unnecessary. Things in here should be moved to GameObject.
public abstract class Item extends GameObject{
    boolean collidable;
    boolean pickupable;
    boolean breakable;
    boolean movable;
    boolean tossed;

    public Item(int x, int y, int width, int height){
        super(x, y, width, height);
    }

    @Override
    public void update(ArrayList<GameObject> things){
        getAt().setToIdentity();
        if(movable) {
            super.update(things);
        }
        if(tossed){
            //setAngle(getAngle() + 1);
            //getAt().rotate(getAngle());
        }
    }

    //don't actually need this. It is throwing things off.
    public void update(int x, int y, Point p){
        getPosition().setX(x);
        getPosition().setY(y);
        if(p != null) {
            setMouseLocation(p);
            updateRotation();
        }
        if(tossed){
           setAngle(getAngle() + 1);
           getAt().rotate(getPosition().getAngle());
        }
    }

    public void act(int i){

    }

    public void isTossed(boolean b){
        tossed = b;
        setAt(new AffineTransform());
    }
}
