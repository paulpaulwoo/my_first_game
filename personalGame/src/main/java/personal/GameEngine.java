package personal;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import personal.Background;
import personal.player.Player;


public class GameEngine implements KeyListener {

    private Player player;
    private int keyPressed;
    private Background background;
    private JFrame frame;
    private JPanel mainPanel;

    public GameEngine() {
        frame = new JFrame();
        player = new Player();
        background = new Background();
        //mainPanel = new JPanel();
        //mainPanel.add(background);
        frame.add(background);
        background.add(player);
        frame.setLayout(null);
        frame.setSize(2000, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
    }

    public void run(GameEngine game) {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.update();
            }
        });
        thread.start();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keyPressed = e.getKeyCode();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyPressed = e.getKeyCode();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keyPressed = e.getKeyCode();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keyPressed = e.getKeyCode();
        }
    }

    public void update() {
        player.handleKey(keyPressed);
        keyPressed = -1;
        player.repaint();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // Called when a key is released
        keyPressed = 0;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Called when a character is typed
    }
    
    public static void main(String[] args) {
        
        Thread thread = new Thread(() -> {
            GameEngine game = new GameEngine();
            while (true) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.update();
            }
        });
        thread.run();
    }
}