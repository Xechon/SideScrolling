import java.util.ArrayList;

/**
 * Created by shann_000 on 5/18/2014.
 */
public class Stick extends Item {
    public Stick(int x, int y){
        super(x,y,5,50);
        movable = true;
        pickupable = true;
    }

    @Override
    public void act(int i){
        if(i == 1){
            for(Actor e: GameComponent.getActors()){
                if(getHitbox().intersects(e.getHitbox().getBounds())){
                    e.hurt(getPosition().getSpeed());
                }
            }
        }
    }

    @Override
    public void update(ArrayList<GameObject> things) {
        super.update(things);
        for(Actor e: GameComponent.getActors()){
            if(getHitbox().intersects(e.getHitbox().getBounds())){
                e.hurt(getPosition().getSpeed());
                tossed = false;
            }
        }
    }
}
