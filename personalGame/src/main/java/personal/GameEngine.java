package personal;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import personal.Background;
import personal.enemy.Amg1;
import personal.enemy.Enemy;
import personal.player.Player;
//import personal.enemy.Amg1;


public class GameEngine implements KeyListener {

    private Player player;
    private int keyPressed;
    private int keyCode = -1;
    private Background background;
    private JFrame frame;
    private JPanel mainPanel;
    private ArrayList<Enemy> enemies;
    private int keysDown;
    public ArrayList<Entity> order = new ArrayList<>();
    public GameEngine() {

        frame = new JFrame();
        

        keysDown = 0;
        background = new Background();
        enemies = new ArrayList<Enemy>();
        player = new Player(enemies);
        order.add(player);
        Enemy a = new Amg1(player);
        enemies.add(a);
        order.add(a);
        
        mainPanel = new JPanel();
        mainPanel.add(background);
        //frame.add(background);
        //background.add(player);
        //background.add(enemies.get(0));

        //Z LAYOUT START
        
        
        frame.getLayeredPane();
        frame.getLayeredPane().add(player);
        frame.getLayeredPane().add(enemies.get(0));
        
        frame.getLayeredPane().add(background);
        frame.getLayeredPane().setLayer(background, 0);
        frame.getLayeredPane().setLayer(player, 900);
        frame.getLayeredPane().setLayer(enemies.get(0), 500);

        //Z LAYOUT END
        //frame.setLayout(null);
        frame.setSize(1024, 900);
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
        keysDown = 0;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keyPressed = e.getKeyCode();
            keyCode = e.getKeyCode();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyPressed = e.getKeyCode();
            keyCode = e.getKeyCode();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keyPressed = e.getKeyCode();
            keyCode = e.getKeyCode();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keyPressed = e.getKeyCode();
            keyCode = e.getKeyCode();
        }
    }

    public void update() {
        player.handleKey(keyPressed);
        keyPressed = -1;
        player.update();
        frame.getLayeredPane().setLayer(player, player.getPosition()[1]);
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
            frame.getLayeredPane().setLayer(enemies.get(i), enemies.get(i).getPosition()[1]);
            enemies.get(i).repaint();
        }
        
        player.repaint();

    }


    
    @Override
    public void keyReleased(KeyEvent e) {
        // Called when a key is released
        if (e.getKeyCode() == keyCode) {
            keyPressed = 0;
        }
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Called when a character is typed
    }
    
}