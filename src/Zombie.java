import java.awt.*;
import java.util.ArrayList;

/**
 * Created by shann_000 on 5/8/2014.
 */
//should move towards the player if within line of sight, ignore obstacles, and accelerate as it gets closer
public class Zombie extends Humanoid{
    public Zombie(int x, int y){
        super(x, y, Main.getScreenSize().width/20, Main.getScreenSize().height/5);
        ArrayList<String> zImgs = new ArrayList<String>();
        zImgs.add("Zombie");
        setImages("Actors" , zImgs);
    }

    @Override
    public void update(ArrayList<GameObject> things) {
        for(Player e: GameComponent.getPlayers()){
            getPosition().setXVelocity(e.getPosition().getX() - getPosition().getX());
            setFocus(new Point(e.getPosition().getXLocation(), e.getPosition().getYLocation()));
        }
        super.update(things);
    }
}
