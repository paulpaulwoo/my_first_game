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
import personal.attacks.Slashattack;
import personal.enemy.Amg1;
import personal.enemy.Enemy;
import personal.player.Player;
//import personal.enemy.Amg1;


public class GameEngine implements KeyListener {
    public static GameEngine engine;
    private Player player;
    private int keyPressed;
    private Background background;
    public JFrame frame;
    private JPanel mainPanel;
    private ArrayList<Enemy> enemies;
    public ArrayList<Entity> toRemove = new ArrayList<>();
    private boolean rightkeyPressed;
    private boolean leftkeyPressed;
    private boolean upkeyPressed;
    private boolean downkeyPressed;
    private boolean spacePressed;
    public ArrayList<loadedImage> toLoad= new ArrayList<>();
    public Loader loader;

    public GameEngine() {
        engine = this;
        frame = new JFrame();
        
        toLoad.add(new loadedImage("effects/slash/slash (", ").png", 10, 1));
        loader = new Loader(toLoad);
        spacePressed = false;
        rightkeyPressed = false;
        leftkeyPressed = false;
        upkeyPressed = false;
        downkeyPressed = false;
        background = new Background();
        enemies = new ArrayList<Enemy>();
        player = new Player();

        Enemy a = new Amg1(player);
        enemies.add(a);

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

        //TEST
        //Slashattack slashAttack = new Slashattack(2, 4, (Entity) player, 10, player.position, 15, loader);

        //TEST END
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
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftkeyPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightkeyPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upkeyPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downkeyPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
    }



    
    public void keyUpdate() {
        if (spacePressed == true) {
            keyPressed = KeyEvent.VK_SPACE;
            return;
        }
        if (leftkeyPressed) {
            keyPressed = KeyEvent.VK_LEFT;
            return;
        }
        if (rightkeyPressed) {
            keyPressed = KeyEvent.VK_RIGHT;
            return;
        }
        if (upkeyPressed) {
            keyPressed = KeyEvent.VK_UP;
            return;
        }
        if (downkeyPressed) {
            keyPressed = KeyEvent.VK_DOWN;
            return;
        }
        keyPressed = 0;
    }

    public void update() {
        keyUpdate();
        player.handleKey(keyPressed);
        keyPressed = -1;
        spacePressed = false;
        for (int i = 0; i < Entity.entities.size(); i++) {
            Entity.entities.get(i).update();
            frame.getLayeredPane().setLayer(Entity.entities.get(i), Entity.entities.get(i).getPosition()[1]);
            Entity.entities.get(i).repaint();
        }
        for (int i = 0; i < toRemove.size(); i++) {
            Entity.entities.remove(toRemove.get(i));
            frame.getLayeredPane().remove(toRemove.get(i));
        }
    }


    
    @Override
    public void keyReleased(KeyEvent e) {
        // Called when a key is released

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftkeyPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightkeyPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upkeyPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downkeyPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Called when a character is typed
    }
    
}