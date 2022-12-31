package personal.player;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import personal.Entity;
import personal.GameEngine;
import personal.attacks.Slashattack;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
//sprite created by sylvius fischer
public class Player extends Entity{
    

    private BufferedImage imageArray[][]; // i = state, j = frame
    private int lookDirection; // changed by change state, 1 : right, 2 : left, 3 : up, 4 : down
    private final int walkRight_totalFrames = 4;
    private final int walkLeft_totalFrames = 4;
    private final int walkUp_totalFrames = 4;
    private final int walkDown_totalFrames = 4;
    private int currentFrame = 0;
    private int state;
    private int totalImages = 1;
    private int divider = 1;
    public int attackFrames = 10;
    private float transparancy = 1f;
    int delay = 0;
    private boolean changeReady = false;
    

    public Player() {
        super(100, 70, 100, 100, 10, 500, 350, 100, 100);
        invincible = false;
        this.state = 1;
        imageArray = new BufferedImage[19][];
        this.lookDirection = 1;
        //setting X and Y coords
        //Walk right animation 
        imageArray[1] = new BufferedImage[walkRight_totalFrames];
        
        for (int i = 0; i < walkRight_totalFrames; i++) {
            try {
                imageArray[1][i] = ImageIO.read(getClass().getResource("sprites/walkRight_" + (1+ i) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Walk left animation
        imageArray[2] = new BufferedImage[walkLeft_totalFrames];
        for (int i = 0; i < walkLeft_totalFrames; i++) {
            try {
                imageArray[2][i] = ImageIO.read(getClass().getResource("sprites/walkLeft_" + (1+ i) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Walk up animation
        imageArray[3] = new BufferedImage[walkUp_totalFrames];
        for (int i = 0; i < walkUp_totalFrames; i++) {
            try {
                imageArray[3][i] = ImageIO.read(getClass().getResource("sprites/walkUp_" + (1+ i) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Walk down animation
        imageArray[4] = new BufferedImage[walkDown_totalFrames];
        for (int i = 0; i < walkDown_totalFrames; i++) {
            try {
                imageArray[4][i] = ImageIO.read(getClass().getResource("sprites/walkDown_" + (1+ i) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Look right animation 
        imageArray[5] = new BufferedImage[1];
        for (int i = 0; i < 1; i++) {
            try {
                imageArray[5][i] = ImageIO.read(getClass().getResource("sprites/walkRight_" + (1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Look left animation
        imageArray[6] = new BufferedImage[1];
        for (int i = 0; i < 1; i++) {
            try {
                imageArray[6][i] = ImageIO.read(getClass().getResource("sprites/walkLeft_" + (2) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Look up animation
        imageArray[7] = new BufferedImage[1];
        for (int i = 0; i < 1; i++) {
            try {
                imageArray[7][i] = ImageIO.read(getClass().getResource("sprites/walkUp_" + (2) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Look down animation
        imageArray[8] = new BufferedImage[1];
        for (int i = 0; i < 1; i++) {
            try {
                imageArray[8][i] = ImageIO.read(getClass().getResource("sprites/walkDown_" + (2) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
                

        //imageArray[9] = new BufferedImage[1]; // DUMMY
        //for (int i = 0; i < 1; i++) {
        //    try {
        //        imageArray[9][i] = ImageIO.read(getClass().getResource("sprites/walkDown_" + (2) + ".png"));
        //    } catch (IOException e) {
        //        e.printStackTrace();
        //    }
        //}
        imageArray[10] = new BufferedImage[1]; // AttackRight
        for (int i = 0; i < 1; i++) {
            try {
                imageArray[10][i] = ImageIO.read(getClass().getResource("sprites/walkRight_" + (2) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imageArray[11] = new BufferedImage[1]; // AttackLeft
        for (int i = 0; i < 1; i++) {
            try {
                imageArray[11][i] = ImageIO.read(getClass().getResource("sprites/walkLeft_" + (2) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imageArray[12] = new BufferedImage[1]; // AttackUp
        for (int i = 0; i < 1; i++) {
            try {
                imageArray[12][i] = ImageIO.read(getClass().getResource("sprites/walkUp_" + (2) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imageArray[13] = new BufferedImage[1]; // AttackDown
        for (int i = 0; i < 1; i++) {
            try {
                imageArray[13][i] = ImageIO.read(getClass().getResource("sprites/walkDown_" + (2) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        imageArray[14] = new BufferedImage[1]; // Death
        for (int i = 0; i < 1; i++) {
            try {
                imageArray[14][i] = ImageIO.read(getClass().getResource("sprites/walkDown_" + (2) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        imageArray[15] = new BufferedImage[1];
        for (int i = 0; i < 1; i++) {
            try {
                imageArray[15][i] = ImageIO.read(getClass().getResource("sprites/walkRight_" + (1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Look left animation
        imageArray[16] = new BufferedImage[1];
        for (int i = 0; i < 1; i++) {
            try {
                imageArray[16][i] = ImageIO.read(getClass().getResource("sprites/walkLeft_" + (2) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Look up animation
        imageArray[17] = new BufferedImage[1];
        for (int i = 0; i < 1; i++) {
            try {
                imageArray[17][i] = ImageIO.read(getClass().getResource("sprites/walkUp_" + (2) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Look down animation
        imageArray[18] = new BufferedImage[1];
        for (int i = 0; i < 1; i++) {
            try {
                imageArray[18][i] = ImageIO.read(getClass().getResource("sprites/walkDown_" + (2) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        changeState(0);
    }
    @Override
    public void changeState(int state) {
        if (!changeReady) {
            return;
        }
        if (this.state == state) {
            return;
        } else {
            switch (state) {
                case 0: totalImages = 1;
                currentFrame = 0;
                this.state = 4 + lookDirection;
                break;

                case 1: totalImages = imageArray[1].length;
                currentFrame = 0;
                lookDirection = 1;
                this.state = state;
                divider = 8;
                break;

                case 2: totalImages = imageArray[2].length;
                currentFrame = 0;
                lookDirection = 2;
                this.state = state;
                divider = 8;
                break;

                case 3: totalImages = imageArray[3].length;
                currentFrame = 0;
                lookDirection = 3;
                this.state = state;
                divider = 8;
                break;

                case 4: totalImages = imageArray[4].length;
                currentFrame = 0;
                lookDirection = 4;
                this.state = state;
                divider = 8;
                break;

                case 5: totalImages = imageArray[5].length;
                currentFrame = 0;
                this.state = state;
                divider = 8;
                break;

                case 6: totalImages = imageArray[6].length;
                currentFrame = 0;
                this.state = state;
                divider = 8;
                break;

                case 7: totalImages = imageArray[7].length;
                currentFrame = 0;
                this.state = state;
                divider = 8;
                break;

                case 8: totalImages = imageArray[8].length;
                currentFrame = 0;
                this.state = state;
                divider = 8;
                break;

                case 9: totalImages = 1;
                currentFrame = 0;
                changeState(9 + lookDirection);
                break;

                case 10: totalImages = attackFrames;
                currentFrame = 0;
                this.state = state;
                divider = 10;
                changeReady = false;
                break;

                case 11: totalImages = attackFrames;
                currentFrame = 0;
                this.state = state;
                divider = 10;
                changeReady = false;
                break;

                case 12: totalImages = attackFrames;
                currentFrame = 0;
                this.state = state;
                divider = 10;
                changeReady = false;
                break;

                case 13: totalImages = attackFrames;
                currentFrame = 0;
                this.state = state;
                divider = 10;
                changeReady = false;
                break;
                
                //DEATH
                case 14: totalImages = 1;
                currentFrame = 0;
                divider = 150;
                changeReady = false;
                this.state = 14 + lookDirection;
                break;

                case 15: totalImages = imageArray[15].length;
                currentFrame = 0;
                this.state = state;
                divider = 120;
                break;

                case 16: totalImages = imageArray[16].length;
                currentFrame = 0;
                this.state = state;
                divider = 120;
                break;

                case 17: totalImages = imageArray[17].length;
                currentFrame = 0;
                this.state = state;
                divider = 120;
                break;

                case 18: totalImages = imageArray[18].length;
                currentFrame = 0;
                this.state = state;
                divider = 120;
                break;
            }
            

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        currentFrame++;
        if (delay > 0) {
            delay--;
        }
        if (iFrames > 0) {
            iFrames--;
            transparancy = 1 - ((0.5f) * ((iFrames / 5) % 2));
            if (iFrames == 0) {
                invincible = false;
            }
        }

        if (currentFrame >= imageArray[state].length * divider)  {
            currentFrame = 0;
            changeReady = true;
        }
        super.paintComponent(g);
        //imageArray_walkRight[currentFrame].drawImage()
        Graphics2D g2d = (Graphics2D)(g);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparancy));
        g2d.drawImage(imageArray[state][currentFrame / divider], 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void handleKey (int keyPressed) {
        if (keyPressed == 0) {
            changeState(0);
        } else if (keyPressed == KeyEvent.VK_LEFT) {
            changeState(2);
        } else if (keyPressed == KeyEvent.VK_RIGHT) {
            changeState(1);
        } else if (keyPressed == KeyEvent.VK_UP) {
            changeState(3);
        } else if (keyPressed == KeyEvent.VK_DOWN) {
            changeState(4);
        } else if (keyPressed == KeyEvent.VK_SPACE) {
            changeState(9);
        }
    }

    @Override
    public int[] getPosition() {
        return position;
    }
    
    public boolean colisionDetection(int direction) {
        for (int i = 0; i < entities.size(); i++) {
            if (colisionCheck(direction, this, entities.get(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public void attack (int direction) {
        if (delay > 0) {
            return;
        }

        Slashattack slashAttack = new Slashattack(1, direction, (Entity) this, 10, this.position, 15, GameEngine.engine.loader);
        GameEngine.engine.frame.getLayeredPane().add(slashAttack);
        GameEngine.engine.frame.getLayeredPane().setLayer(slashAttack, 1900);
        delay = attackFrames;
    }

    @Override
    public void deathSequence() {
        changeState(14);
        invincible = true;
    }
    public void fade() {
        if (transparancy > 0) {
            transparancy -= 0.02f;
            if (transparancy <= 0) {
                GameEngine.gameOverSequence();
                transparancy = 0.01f;
            }
        } else {
            System.out.println("Death over");
            GameEngine.gameOverSequence();
            
        }
        
    }

    public void move (int direction) {
        switch (direction) {
            case 1:
            if ((position[0] < 870) && (colisionDetection(1))) {
                position[0] += speed;
                spritePosition[0] += speed;
            }
            break;
            case 2:
            if ((position[0] > 50) && (colisionDetection(2))) {
                position[0] -= speed;
                spritePosition[0] -= speed;
            }
            break;
            case 3:
            if (position[1] > 30 && (colisionDetection(3))) {
                position[1] -= speed;
                spritePosition[1] -= speed;
                //setComponentZOrder(this, position[1]);
            }
            break;
            case 4:
            if (position[1] < 700 && (colisionDetection(4))) {
                position[1] += speed;
                spritePosition[1] += speed;
            }
            break;
        }
        this.setBounds(position[0], position[1], this.WIDTH,  this.HEIGHT );
    }

    @Override
    public void update () {
        switch (state) {
            case 1:
            move(1);
            break;
            case 2:
            move(2);
            break;
            case 3:
            move(3);
            break;
            case 4:
            move(4);
            break;
            case 10:
            attack(1);
            break;
            case 11:
            attack(2);
            break;
            case 12:
            attack(3);
            break;
            case 13:
            attack(4);
            break;
            case 14:
            fade();
            break;
            case 15:
            fade();
            break;
            case 16:
            fade();
            break;
            case 17:
            fade();
            break;
            case 18:
            fade();
            break;
        }
    }
    
}