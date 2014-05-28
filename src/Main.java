import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by hempstead on 5/8/2014.
 */
public class Main extends JFrame {
    static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    private static final Dimension ScreenSize = new Dimension(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight()); //shouldn't be final

    private static boolean debug = true;

    private static ArrayList<String> levels = new ArrayList<String>();
    private static GameComponent gc = new GameComponent();

    public Main(){
        super("Just keep scrolling...");
        setSize(ScreenSize);
        setResizable(false); //Shouldn't do this
        //setUndecorated(true); //In the future, have a fullscreen option with this
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        levels.add("Resources/Levels/Testing/1.png"); //Have a folder select in Levels, and add all pngs in folder.
        gc.setLevels(levels);
        gc.loadLevel();
        add(gc); //Add splashscreen and menu components first, dispose once done, recreate if needed.
    }

    public static void main(String[] args){

        try {//Should learn what this code is all about
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    final Main main = new Main();
                    main.setVisible(true);
                    Timer t = new Timer(20, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            main.getBg().update();
                        }
                    });
                    t.start();
                }
            });
        } catch(InterruptedException e){
            e.printStackTrace();
        } catch (InvocationTargetException e){
            e.printStackTrace();
        }
    }

    public GameComponent getBg(){
        return gc;
    }

    public static void setDebug(boolean b){
        debug = b;
    }

    public static boolean getDebug(){
        return debug;
    }

    public static void selectLevelPack(String s){
        for(int i = 0; i > -1; i++){
            if(new File("Resources/Levels/" + s + "/" + i + ".png") != null) {
                levels.add("Resources/Levels/" + s + "/" + i + ".png");
            }
            else{
                break;
            }
        }
    }

    public static Dimension getScreenSize(){
        return ScreenSize;
    }
}
