import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by shann_000 on 5/8/2014.
 */
//Tapping is not faster than holding. Delay between inputs when held.
//Maybe use KeyMap instead?
public class Controller{
    InputMap im;
    ActionMap am;
    public Controller(final JComponent c){
        final GameComponent gc = (GameComponent) c;
        ArrayList<Player> players = GameComponent.getPlayers();
        im = gc.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        am = gc.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "space-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "d-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0 ,true), "d-released");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "a-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "a-released");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, false), "e-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, false), "q-pressed");

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, false), "1-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0 ,false), "2-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0 ,false), "3-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0 ,false), "4-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0 ,false), "5-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0 ,false), "6-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0 ,false), "7-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0 ,false), "8-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0 ,false), "9-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_0, 0 ,false), "0-pressed");

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0, true), "F12-pressed");

        for (Player p: players){
            final Player player = p;

            am.put("space-pressed", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(player.isGrounded()) {
                        player.jump();
                    }
                }
            });

            am.put("d-pressed", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //player.setFacingRight(true);//temporary
                    if(player.isGrounded() && !player.isWalking()) {
                        player.setIsWalking(true);
                        player.setSpeed(Math.abs(player.getSpeed()));
                    }
                }
            });

            am.put("d-released", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    player.setIsWalking(false);
                }
            });

            am.put("a-pressed", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(player.isGrounded() && !player.isWalking()) {
                        player.setIsWalking(true);
                        player.setSpeed(-Math.abs(player.getSpeed()));
                    }
                }
            });

            am.put("a-released", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    player.setIsWalking(false);
                }
            });

            am.put("e-pressed", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    player.pickupItem();
                }
            });

            am.put("q-pressed", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    player.getArms().toss();
                }
            });

            for(int i = 0; i < 10; i++) {
                final int q = i;
                am.put(i + "-pressed", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        player.changeSelectedItem(q);
                    }
                });
            }

            am.put("F12-pressed", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    player.toggleDebug();
                }
            });

            class MouseControls extends MouseAdapter{
                @Override
                public void mouseClicked(MouseEvent e) {
                    player.getArms().act();
                }

                @Override
                public void mouseMoved(MouseEvent e){
                    player.setMouseLocation(e.getPoint());
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    player.setMouseLocation(e.getPoint());
                }

                @Override
                public void mouseWheelMoved(MouseWheelEvent e){
                }
            }
            MouseControls mc = new MouseControls();
            gc.addMouseListener(mc);
            gc.addMouseMotionListener(mc);
            gc.addMouseWheelListener(mc);
        }
    }
}