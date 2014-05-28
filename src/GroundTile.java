import java.util.ArrayList;

/**
 * Created by hempstead on 5/8/2014.
 */
public class GroundTile extends Item{
    ArrayList<String> groundImgs = new ArrayList<String>();
    public GroundTile(int x, int y){
        super(x,y,GameComponent.SCALE, GameComponent.SCALE);
        collidable = true;
        groundImgs.add("Tile");
        setImages("Levels" , groundImgs);
    }
}
