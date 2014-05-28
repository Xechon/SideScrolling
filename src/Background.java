import java.awt.*;

/**
 * Created by hempstead on 5/8/2014.
 */
public class Background{
    private static double gravity = 1;
    private Image backgroundImg;
    public Background(){
        backgroundImg = Toolkit.getDefaultToolkit().createImage("Resources/Levels/Testing/background.png");
    }
    public static double getGravity(){
        return gravity;
    }

    public void draw(Graphics2D g2){
        //g2.drawImage(backgroundImg, 0,0,Main.getScreenSize().width, Main.getScreenSize().height, null);
    }
}
