package personal;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import personal.Sounds.Sound;
import personal.Sounds.SoundData;
import personal.UI.HpBar;
import personal.UI.LoadScreen;
import personal.UI.MainScreen;
import personal.UI.NewgameScreen;
import personal.UI.PauseScreen;
import personal.UI.TitleScreen;
import personal.enemy.Amg;
import personal.enemy.Enemy;
import personal.events.Event;
import personal.player.Player;
import personal.player.PlayerData;


public class GameEngine implements KeyListener {
    public static boolean DEBUG = false;
    public static GameEngine engine;
    public static int frameWidth = 1024;
    public static int frameHeight = 900;
    public static Thread mainThread;
    public static boolean run;
    private static Player player;
    private int keyPressed;
    public JFrame frame;
    private static ArrayList<Entity> enemies = new ArrayList<Entity>();
    public static ArrayList<Entity> toRemove = new ArrayList<>();
    private boolean rightkeyPressed;
    private boolean leftkeyPressed;
    private boolean upkeyPressed;
    private boolean downkeyPressed;
    private boolean spacePressed;
    private boolean escapePressed;
    public ArrayList<loadedImage> toLoad= new ArrayList<>();
    public static Loader loader;
    public static ArrayList<JPanel> uiComponents = new ArrayList<>();
    public static ArrayList<JPanel> combatComponents = new ArrayList<>();
    public static String saveString;
    public static PlayerData currentData;
    public static int saveIndex; 
    public static boolean isPaused = false;
    public static PauseScreen pauseScreen;

    public GameEngine() {
        engine = this;
        frame = new JFrame();
        spacePressed = false;
        rightkeyPressed = false;
        leftkeyPressed = false;
        upkeyPressed = false;
        downkeyPressed = false;

        //frame.setSize(1024, 900);
        frame.setSize(1035, 935);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        Event.Init();
        loader = new Loader();
        player = new Player();
        frame.setLocationRelativeTo(null);

        initComponents();

        SoundData.soundThread.start();

        titleSequenceInit();


        frame.repaint();

        //combatSequenceInit(null, null);
        
    }

    public void loadSequenceInit() {
        JPanel panel = new LoadScreen();
        frame.getLayeredPane().add(panel);
    }

    public void clearCombat() {
        Entity.resetEntities();
        loader.combatUnload();
        uiComponents = new ArrayList<>();
        enemies = new ArrayList<>();
        player = new Player();
        initComponents();
    }
    
    public void titleSequenceInit() {
        JPanel panel = new TitleScreen();
        frame.getLayeredPane().add(panel);
    }

    public void mainSequenceInit(PlayerData data) {
        JPanel panel = new MainScreen(data);
        frame.getLayeredPane().add(panel);
    }

    public void mainSequenceResume() {
        JPanel panel = new MainScreen();
        frame.getLayeredPane().add(panel);
    }

    public void newGameSequenceInit() {
        JPanel panel = new NewgameScreen();
        frame.getLayeredPane().add(panel);
    }

    public void initComponents() {
        combatComponents = new ArrayList<>();
        combatComponents.add(new HpBar(player));
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
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            escapePressed= true;
        }
    }

    public void combatLoad() {
        loader.combatLoad();
    }

    public void combatSequenceInit(ArrayList<Enemy> eventEnemies) {
        frame.getLayeredPane().invalidate();
        frame.getLayeredPane().removeAll();
        frame.getLayeredPane().validate();
        frame.getLayeredPane().repaint();
        uiComponents.clear();
        loader.mutex.lock();
        loader.mutex.unlock();
        //TO CHANGE DYNAMICALLY
        Background background = new Background();
        Amg a = new Amg(player, 200, 200);
        enemies.add(a);
        //TO CHANGE END

        frame.getLayeredPane().add(player);
        for (int i = 0; i < enemies.size(); i++) {
            frame.getLayeredPane().add(enemies.get(i));
            frame.getLayeredPane().setLayer(enemies.get(i), enemies.get(i).position[1]);
        }
        frame.getLayeredPane().add(background);
        frame.getLayeredPane().setLayer(background, 0);
        frame.getLayeredPane().setLayer(player, player.position[1]);
        
        for (int i = 0; i < combatComponents.size(); i++) {
            uiComponents.add(combatComponents.get(i));
        }

        for (int i = 0; i < uiComponents.size(); i++) {
            frame.getLayeredPane().add(uiComponents.get(i));
            frame.getLayeredPane().setLayer(uiComponents.get(i), 3000);
        }

        if (DEBUG) {
            player.setOpaque(true);
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).setOpaque(true);
            }
        }
        mainThread = new Thread(() -> {
            
            while (run) {
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                engine.update();
            }
        });
        mainThread.start();

    }

    public static void combatSequenceEnd(int situation) {
        if (situation == 0) {//to main screen from pause screen 
            endPause();
            frameClear();
            GameEngine.engine.titleSequenceInit();
            GameEngine.run = false;
            GameEngine.engine.clearCombat();
        }
        
        if (situation == 1) { // player lost
            
        }

        if (situation == 2) { // player won
            engine.mainSequenceResume();
        }

    }

    
    public void keyUpdate() {
        if (spacePressed == true) {
            keyPressed = KeyEvent.VK_SPACE;
            return;
        }
        if (escapePressed) {
            keyPressed = KeyEvent.VK_ESCAPE;
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


    public static JFrame frameClear() {
        JFrame frame = GameEngine.engine.frame;
        frame.getLayeredPane().invalidate();
        frame.getLayeredPane().removeAll();
        frame.getLayeredPane().validate();
        frame.getLayeredPane().repaint();
        return frame;
    }

    public void update() {
        keyUpdate();
        if (escapePressed) {
            keyPressed = 1;
            combatPauseSequence();
        }
        if (Entity.entities.size() == 1) {
            if (Entity.entities.get(0) == player) {
                run = false;
                combatSequenceEnd(2);
            }
        }
        player.handleKey(keyPressed);
        escapePressed = false;
        keyPressed = -1;
        spacePressed = false;
        
        for (int i = 0; i < Entity.entities.size(); i++) {
            Entity.entities.get(i).update();
            frame.getLayeredPane().setLayer(Entity.entities.get(i), Entity.entities.get(i).getPosition()[1]);
        }
        for (int i = 0; i < toRemove.size(); i++) {
            Entity.entities.remove(toRemove.get(i));
            frame.getLayeredPane().remove(toRemove.get(i));   
        }     
        frame.repaint();
    }

    public void combatPauseSequence () {
        
        isPaused = true;
        Thread pauseThread = new Thread(() -> {
            pauseScreen = new PauseScreen();
            frame.getLayeredPane().add(pauseScreen);
            frame.getLayeredPane().setLayer(pauseScreen, 4000);
            
            while(isPaused) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });
        pauseThread.start();
        try {
            pauseThread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //mainThread.notify();
    }

    
    public static void endPause() {
        isPaused = false;
        engine.frame.getLayeredPane().remove(pauseScreen);
        engine.frame.getLayeredPane().revalidate();
    }

    public static void gameOverSequence() {
        System.out.println("gameOver");
        stop();
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

    //START OF APP
    public static void start() {
        run = true;
        engine = new GameEngine();
    }
    
    public static void stop() {
        run = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Called when a character is typed
    }
    
}