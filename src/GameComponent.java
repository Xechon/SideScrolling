import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hempstead on 5/8/2014.
 */
public class GameComponent extends JComponent {
    private static Background background = new Background();
    private static ArrayList<GameObject> things;
    private static ArrayList<Actor> actors = new ArrayList<Actor>();
    private static ArrayList<Item> items = new ArrayList<Item>();
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static ArrayList<BufferedImage> levels = new ArrayList<BufferedImage>();
    public final static int SCALE = 100;

    public void setLevels(ArrayList<String> l){
        for (String s : l) {
            try {
                levels.add(ImageIO.read(new File(s)));
            }
            catch(IOException e){
                e.printStackTrace();
                System.out.println("IOException");
            }
        }
        LevelReader.setLevels(levels);
    }

    public void loadLevel(){
        things = LevelReader.readLevel().getThings();

        for(GameObject e: things){
            if(e instanceof Item){
                items.add((Item) e);
            }
            if(e instanceof Player){
                players.add((Player) e);
            }
            if(e instanceof Actor){
                actors.add((Actor) e);
            }
        }
        new Controller(this);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        background.draw(g2);
        for(GameObject e: things){
            e.draw(g2);
        }
    }

    public void update(){
        for(int i = 0; i < things.size(); i++){
            things.get(i).update(things);
            if(things.get(i) instanceof Actor){
                Actor a = (Actor) things.get(i);
                if(a.isDead()) {
                    things.remove(i);
                    i--;
                }
            }
        }
        repaint();
    }

    public void centerScreen() {//call when player moves
        //if (background.getX() > Main.getScreenSize().getWidth() - background.getWidth() && background.getY() > Main.getScreenSize().getHeight() - background.getHeight()) {

        //}
    }

    public static ArrayList<GameObject> getThings(){
        return things;
    }
    public static ArrayList<Actor> getActors(){
        return actors;
    }
    public static ArrayList<Item> getItems(){
        return items;
    }
    public static ArrayList<Player> getPlayers(){
        return players;
    }
    public static ArrayList<BufferedImage> getLevels(){
        return levels;
    }
}
