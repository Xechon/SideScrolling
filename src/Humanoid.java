import java.util.ArrayList;

/**
 * Created by hempstead on 5/16/2014.
 */
public class Humanoid extends Actor {
    private Arms arms;
    public Humanoid(int x, int y, int width, int height){
        super(x,y,width,height);
        arms = new Arms(x/2,y/2,3*height/4, width/2);
        setAppendages(1);
        getAppendages()[0] = arms;
    }

    public Arms getArms(){
        return arms;
    }

    @Override
    public void update(ArrayList<GameObject> things){
        super.update(things);
        arms.update(getPosition().getXLocation() + getWidth()/4, getPosition().getYLocation() + getHeight()/4, getFocus());
        setFacingRight(arms.updateRotation());
    }

    @Override
    public void pickupItem(){
        if(arms.getHeldItem() == null && getHoveredItem() != null){
            arms.setHeldItem(getHoveredItem());
            setHoveredItem(null);
        }
    }
}
