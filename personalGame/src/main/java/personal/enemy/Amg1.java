package personal.enemy;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import personal.player.Player;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.lang.Math;
public class Amg1 extends Enemy {
    private BufferedImage imageArray[][]; // i = state, j = frame
    private int lookDirection; // changed by change state, 1 : right, 2 : left, 3 : up, 4 : down, 5 : spawn
    private final int walkRight_totalFrames = 2;
    private final int walkLeft_totalFrames = 2;
    private final int walkUp_totalFrames = 2;
    private final int walkDown_totalFrames = 2;
    private final int spawn_totalFrames = 50;
    private int currentFrame = 0;
    private int state; // 1: spawn
    public int speed;
    private int totalImages;
    private boolean changeReady = false;
    private int attackRange = 50;
    private Player player;
    private boolean stunned = false;
    
    private int divider = 1;

    public Amg1(Player player) {
        WIDTH = 100;
        HEIGHT = 100;
        state = 5;
        speed = 8;
        imageArray = new BufferedImage[9][];
        //setting X and Y coords
        position = new int[2];
        position[0] = (int) (Math.random() * 820 + 50);
        position[1] = (int) (Math.random() * 670 + 30);
        //setComponentZOrder(this, position[1]);
        this.player = player;

        this.setSize(80, 100);



        imageArray[1] = new BufferedImage[walkRight_totalFrames];
        for (int i = 0; i < walkRight_totalFrames; i++) {
            try {
                imageArray[1][i] = ImageIO.read(getClass().getResource("last-guardian-sprites/amg1_rt" + (1+ i) + ".gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Walk left animation
        imageArray[2] = new BufferedImage[walkLeft_totalFrames];
        for (int i = 0; i < walkLeft_totalFrames; i++) {
            try {
                imageArray[2][i] = ImageIO.read(getClass().getResource("last-guardian-sprites/amg1_lf" + (1+ i) + ".gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        imageArray[3] = new BufferedImage[walkUp_totalFrames];
        for (int i = 0; i < walkUp_totalFrames; i++) {
            try {
                imageArray[3][i] = ImageIO.read(getClass().getResource("last-guardian-sprites/amg1_bk" + (1+ i) + ".gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imageArray[4] = new BufferedImage[walkDown_totalFrames];
        for (int i = 0; i < walkDown_totalFrames; i++) {
            try {
                imageArray[4][i] = ImageIO.read(getClass().getResource("last-guardian-sprites/amg1_fr" + (1+ i) + ".gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        imageArray[5] = new BufferedImage[spawn_totalFrames];
        for (int i = 0; i < spawn_totalFrames; i++) {
            String adder = new String();
            if (i < 9) {
                adder = "0" + (1 + i) ;
            } else {
                adder = "" + (1 + i);
            }
            try {
                imageArray[5][i] = ImageIO.read(getClass().getResource("../effects/spawn/00" + adder + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.setOpaque(false);
    }


    @Override
    public int[] getPosition() {
        return position;
    }
    
    public int inAttackRange() { 
        // return values: 0 if not in range
        // 1 if right
        // 2 if left
        // 3 if up
        // 4 if down
        if ((((position[0] + WIDTH + attackRange > player.getPosition()[0]) && (position[0] + WIDTH + attackRange < player.getPosition()[0] + player.WIDTH)) 
            && ((position[1] > player.getPosition()[1]) && (position[1] < player.getPosition()[1] + player.HEIGHT))) 
        /*||  (((position[0] + WIDTH + attackRange > player.getPosition()[0]) && (position[0] + WIDTH + attackRange < player.getPosition()[0] + player.WIDTH)) 
            && ((position[1] + HEIGHT > player.getPosition()[1]) && (position[1] + HEIGHT < player.getPosition()[1] + player.HEIGHT)) )*/
        )
        {
            //right
            return 1;
        }
        return 0;

    }

    @Override
    public void update() { // AI
        // TODO Auto-generated method stub
        if (changeReady) {
            //if ()
            if (inAttackRange() != 0) {
                //DEBUG
                System.out.println("IN ATTACK RANGE");
                //DEBUG
                changeState(1);
            } else if (stunned) {

            } else if (false) {

            }
        }

        this.setBounds(position[0], position[1], WIDTH, HEIGHT);
    }

    @Override
    public void changeState(int state) {
        // TODO Auto-generated method stub
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
                divider = 1;
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
        changeReady = false;
    }


    @Override
    public void spawn() {

    }


    @Override
    public void move() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void stunned() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void attack() {
        
    }
    @Override
    public void die() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void paintComponent(Graphics g) {
        
        currentFrame++;
        if (currentFrame >= imageArray[state].length * divider)  {
            currentFrame = 0;
            changeReady = true;
        }
        super.paintComponent(g);
//        imageArray_walkRight[currentFrame].drawImage()
        g.drawImage(imageArray[state][currentFrame / divider], 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
