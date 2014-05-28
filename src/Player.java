import java.util.ArrayList;

/**
 * Created by hempstead on 5/8/2014.
 */
public class Player extends Humanoid{
    static final ArrayList<String> fileNames = new ArrayList<String>();
    public Player(int x, int y) {

        super(x, y, Main.getScreenSize().width/20, Main.getScreenSize().height/5);
        setSpeed(10);
        fileNames.add("Player");
        setImages("Actors" , fileNames);
    }

    @Override
    public void update(ArrayList<GameObject> things){
        setFocus(getMouseLocation());
        super.update(things);
    }
}
