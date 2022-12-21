package personal.player;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import personal.Entity;
import personal.enemy.Enemy;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
//sprite created by sylvius fischer
public class Player extends Entity{
    
    private ArrayList<Enemy> enemies;
    private BufferedImage imageArray[][]; // i = state, j = frame
    private int lookDirection; // changed by change state, 1 : right, 2 : left, 3 : up, 4 : down
    private final int walkRight_totalFrames = 4;
    private final int walkLeft_totalFrames = 4;
    private final int walkUp_totalFrames = 4;
    private final int walkDown_totalFrames = 4;
    private int currentFrame = 0;
    private int state;
    private int totalImages = 1;
    private int speed;
    public final int WIDTH = 100;
    public final int HEIGHT = 100;
    private boolean changeReady = false;
    private int[] position; // index 0: x coords
                    // index 1: y coords

    
    public Player(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
        state = 1;
        speed = 10;
        imageArray = new BufferedImage[9][];
        this.setSize(100, 100);

        //setting X and Y coords
        position = new int[2];
        position[0] = 500;
        position[1] = 350;
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
                
        this.setOpaque(false);

    }

    public void changeState(int state) {
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
                break;

                case 2: totalImages = imageArray[2].length;
                currentFrame = 0;
                lookDirection = 2;
                this.state = state;
                break;

                case 3: totalImages = imageArray[3].length;
                currentFrame = 0;
                lookDirection = 3;
                this.state = state;
                break;

                case 4: totalImages = imageArray[4].length;
                currentFrame = 0;
                lookDirection = 4;
                this.state = state;
                break;

                case 5: totalImages = imageArray[5].length;
                currentFrame = 0;
                this.state = state;
                break;

                case 6: totalImages = imageArray[6].length;
                currentFrame = 0;
                this.state = state;
                break;

                case 7: totalImages = imageArray[7].length;
                currentFrame = 0;
                this.state = state;
                break;

                case 8: totalImages = imageArray[8].length;
                currentFrame = 0;
                this.state = state;
                break;
            }

        }
    }



    @Override
    public void paintComponent(Graphics g) {
        currentFrame++;
        if (currentFrame >= imageArray[state].length * 8)  {
            currentFrame = 0;
            changeReady = true;
        }
        super.paintComponent(g);
//        imageArray_walkRight[currentFrame].drawImage()
        g.drawImage(imageArray[state][currentFrame / 8], 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void handleKey (int keyPressed) {
        //System.out.println("x: " + this.getWidth() + ", y: " + this.getHeight());
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

        }
        this.setBounds(position[0], position[1], 100,  100 );
    }

    @Override
    public int[] getPosition() {
        return position;
    }
    
    public boolean colisionDetection(int direction) {
        //1 : right, 2 : left, 3 : up, 4 : down
        if (direction == 1) {
            //System.out.println("colision check right");
            for (int i = 0; i < enemies.size(); i++) {
                System.out.println("Player right bound = " + (position[0] + WIDTH) + ", Enemy left bound = " + enemies.get(i).position[0] + ", Enemy right bound= " + (enemies.get(i).position[0] + enemies.get(i).WIDTH)
                + ", Player top bound = " + position[1] + ", Enemy top bound = " + enemies.get(i).position[1]);
                if ((((position[0] + WIDTH + speed > enemies.get(i).position[0]) && (position[0] + WIDTH + speed < enemies.get(i).position[0] + enemies.get(i).WIDTH)) 
                    && ((((position[1] + (HEIGHT / 2) > enemies.get(i).position[1]) && (position[1] + (HEIGHT / 2) < enemies.get(i).position[1] + enemies.get(i).HEIGHT))) 
                    /*|| (((position[1] + HEIGHT > enemies.get(i).position[1]) && (position[1] + HEIGHT < enemies.get(i).position[1] + enemies.get(i).HEIGHT)))*/))) {
                    return false;
                }
            }
            
        } else if (direction == 2) {
            for (int i = 0; i < enemies.size(); i++) {
                if ((((position[0] - speed > enemies.get(i).position[0]) && (position[0] - speed < enemies.get(i).position[0] + enemies.get(i).WIDTH)) 
                    && ((position[1] + (HEIGHT / 2) > enemies.get(i).position[1]) && (position[1] + (HEIGHT / 2) < enemies.get(i).position[1] + enemies.get(i).HEIGHT)))) {
                    return false;
                }
            }
        } else if (direction == 3) {
            
        } else {

        }
        //System.out.println("colision passed");
        return true;
    }

    public void update () {
        //System.out.println("State: " + state + ", position : " + position[1]);
        switch (state) {
            case 1:
            if ((position[0] < 870) && (colisionDetection(1))) {
                position[0] += speed;
            }
            break;
            case 2:
            if ((position[0] > 50) && (colisionDetection(2))) {
                position[0] -= speed;
            }
            break;
            case 3:
            if (position[1] > 30) {
                position[1] -= speed;
                //setComponentZOrder(this, position[1]);
            }
            break;
            case 4:
            if (position[1] < 700) {
                position[1] += speed;
            }
            break;
        }
    }
    
}