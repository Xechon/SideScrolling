import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hempstead on 5/14/2014.
 */
public class Grid implements Serializable{ //figure out how serializing works, save/load game, multiplayer?
    ArrayList<GameObject> things = new ArrayList<GameObject>();

    public void add(GameObject thing){
        things.add(thing);
    }

    public ArrayList<GameObject> getThings(){
        return things;
    }

    public void removeFromGrid(GameObject thing){
        things.remove(thing);
    }
}
