import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by hempstead on 5/9/2014.
 */
public abstract class LevelReader extends Thread{//figure out how threads work

    private static ArrayList<BufferedImage> levels;
    private static int[][][] pixelGrid;
    private static Grid[] things;
    private static int index = 0;

    public static void setLevels(ArrayList<BufferedImage> l){
        levels = l;
        pixelGrid = new int[levels.size()][][];
        things = new Grid[levels.size()];
        changeLevel(index);
    }

    public static Grid readLevel(){ //add a separate check for the first line, to determine background etc.
        things[index] = new Grid();
        for(int i = 0; i < pixelGrid[index].length; i++){
            for(int k = 0; k < pixelGrid[index][i].length; k++){
                lookFor(pixelGrid[index][i][k], new GroundTile(k*GameComponent.SCALE, i*GameComponent.SCALE), Color.BLACK);
                lookFor(pixelGrid[index][i][k], new Player(k*GameComponent.SCALE, i*GameComponent.SCALE), Color.YELLOW);
                lookFor(pixelGrid[index][i][k], new Zombie(k*GameComponent.SCALE, i*GameComponent.SCALE), Color.GREEN);
                lookFor(pixelGrid[index][i][k], new Stick(k*GameComponent.SCALE, i*GameComponent.SCALE), Color.BLUE);
            }
        }
        return things[index]; //
    }

    public static void lookFor(int value, GameObject thing, Color indicator){
        if(value == indicator.getRGB()){
            things[index].add(thing);
        }
    }

    public static void changeLevel(int n){
        index = n;
        BufferedImage level = levels.get(index);
        pixelGrid[index] = new int[level.getHeight()][level.getWidth()];
        for(int i = 0; i < level.getHeight(); i++){
            for(int k =0; k < level.getWidth(); k++){
                pixelGrid[index][i][k] = level.getRGB(k,i);
            }
        }
    }
}
