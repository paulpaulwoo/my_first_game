package personal.enemy;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import personal.player.Player;
import java.awt.Graphics;
import personal.Entity;
import personal.GameEngine;
import personal.attacks.Slashattack;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.lang.Math;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class Enemy extends Entity {
    protected boolean DEBUG = false;
    protected BufferedImage imageArray[][]; // i = state, j = frame
    protected int lookDirection; // changed by change state, 1 : right, 2 : left, 3 : up, 4 : down, 5 : spawn

    protected int[] animationLengths;
    protected String[] animationPrefixes;
    protected String[] animationSuffixes;
    protected int[] dividers;
    protected int currentFrame = 0;
    protected int state = 1; // 1: spawn
    protected int totalImages;
    protected boolean changeReady = false;
    protected int attackRange;
    protected int attackWidth;
    protected Player player;
    protected boolean stunned = false;
    protected int divider = 1;
    protected int delay = 0;
    protected float transparancy = 1f;
    protected int attackDamage;


    public Enemy(int SPRITEWIDTH, int SPRITEHEIGHT, int WIDTH, int HEIGHT, int speed, int xpos, int ypos, int maxHP, int currentHP, int states, int attackRange, int attackWidth, Player player,
        int[] animationLengths, String[] animationPrefixes, String[] animationSuffixes, int[] dividers) {
        super(SPRITEWIDTH, SPRITEHEIGHT, WIDTH, HEIGHT, speed, xpos, ypos, maxHP, currentHP);
        imageArray = new BufferedImage[states][];
        this.player = player;
        this.attackRange = attackRange;
        this.attackWidth = attackWidth;
        this.animationLengths = animationLengths;
        this.animationPrefixes = animationPrefixes;
        this.animationSuffixes = animationSuffixes;
        this.dividers = dividers;

        for (int i = 0; i < states; i++) {
            imageArray[i] = new BufferedImage[animationLengths[i]];
            if (DEBUG) {
                System.out.println("i: " + i + ", j length: " + animationLengths[i]);
            }
            for (int j = 0; j < animationLengths[i]; j++) {
                try {
                    if (DEBUG) {
                        System.out.println(animationPrefixes[i] + (1+ j) + animationSuffixes[i]);
                    }
                    imageArray[i][j] = ImageIO.read(getClass().getResource(animationPrefixes[i] + (1+ j) + animationSuffixes[i]));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        this.setOpaque(false);
        this.changeState(5);


    }

    @Override
    public int[] getPosition() {
        return position;
    }
    
    public int inAttackRange() { 

        if ((((spritePosition[0] + SPRITEWIDTH + attackRange > player.spritePosition[0]) && (spritePosition[0] + SPRITEWIDTH < player.spritePosition[0])) 
            && ((spritePosition[1] + ((SPRITEHEIGHT - attackWidth) / 2) < player.spritePosition[1] + player.SPRITEHEIGHT) && (spritePosition[1] + ((SPRITEHEIGHT + attackWidth) / 2) > player.spritePosition[1] + (player.SPRITEHEIGHT / 2)))) 
        ) {
            //right
            return 6;
        } else if ((((spritePosition[0] - attackRange < player.spritePosition[0] + player.SPRITEWIDTH) && (spritePosition[0] > player.spritePosition[0])) 
            && ((spritePosition[1] + ((SPRITEHEIGHT - attackWidth) / 2) < player.spritePosition[1] + player.SPRITEHEIGHT) && (spritePosition[1] + ((SPRITEHEIGHT + attackWidth) / 2) > player.spritePosition[1] + (player.SPRITEHEIGHT / 2)))) 
        ) {
            //left
            return 7;
        } else if ((((spritePosition[0] + ((SPRITEWIDTH - attackWidth) / 2) < player.spritePosition[0] + player.SPRITEWIDTH) && (spritePosition[0] + ((SPRITEWIDTH + attackWidth) / 2) > player.spritePosition[0]) ) 
            && ((spritePosition[1] + SPRITEHEIGHT / 2 - attackRange < player.spritePosition[1] + player.SPRITEHEIGHT) && (spritePosition[1]  + SPRITEHEIGHT / 2 > player.spritePosition[1] + player.SPRITEHEIGHT / 2))) 
        ) {
        //up
            return 8;
        } else if ((((spritePosition[0] + ((SPRITEWIDTH - attackWidth) / 2) < player.spritePosition[0] + player.SPRITEWIDTH) && (spritePosition[0] + ((SPRITEWIDTH + attackWidth) / 2) > player.spritePosition[0]) ) 
            && ((spritePosition[1] + SPRITEHEIGHT + attackRange > player.spritePosition[1] + player.SPRITEHEIGHT / 2) && (spritePosition[1] + SPRITEHEIGHT < player.spritePosition[1] + player.SPRITEHEIGHT))) 
        ) {
        //down
            return 9;
        }
        return 0;

    }

    @Override
    public void update() { // AI
        // TODO Auto-generated method stub
        if (changeReady) {
            int attackDirection = inAttackRange();
            if (attackDirection != 0) {
                changeState(attackDirection);
                return;
            } else if (stunned) {

            } else {
                
                //get direction to move                
                if (Math.abs(position[0] - this.player.getPosition()[0]) >= Math.abs(position[1] - this.player.getPosition()[1])) {
                    //move horizontal
                    if (position[0] - this.player.getPosition()[0] < 0) {
                        changeState(1);
                        return;
                    } else {
                        changeState(2);
                        return;
                    }
                } else {
                    //move vertical
                    if (position[1] - this.player.getPosition()[1] > 0) {
                        changeState(3);
                        return;
                    } else {
                        changeState(4);
                        return;
                    }
                }
            }
        } else {

            switch (state) {
                case 0:
                    break;
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
                case 5:
                break;
                case 6:
                    attack(1);
                    break;
                case 7:
                    attack(2);
                    break;
                case 8:
                    attack(3);
                    break;
                case 9:
                    attack(4);
                    break;
                case 10:
                break;
                case 11:
                    fade();
                break;
                case 12:
                    fade();
                break;
                case 13:
                    fade();
                break;
                case 14:
                    fade();
                break;
            }
        }

        this.setBounds(position[0], position[1], WIDTH, HEIGHT);
    }

    @Override
    public void changeState(int state) {
        System.out.println("WARNING: DIDNT OVERRITE ENEMY CHANGESTATE!");
        // TODO Auto-generated method stub
        if (this.state == state) {
            changeReady = false;
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
                divider = dividers[state];
                break;

                case 2: totalImages = imageArray[2].length;
                currentFrame = 0;
                lookDirection = 2;
                this.state = state;
                divider = dividers[state];
                break;

                case 3: totalImages = imageArray[3].length;
                currentFrame = 0;
                lookDirection = 3;
                this.state = state;
                divider = dividers[state];
                break;

                case 4: totalImages = imageArray[4].length;
                currentFrame = 0;
                lookDirection = 4;
                this.state = state;
                divider = dividers[state];
                break;

                case 5: totalImages = imageArray[5].length;
                currentFrame = 0;
                this.state = state;
                divider = dividers[state];
                break;

                case 6: totalImages = imageArray[6].length;
                currentFrame = 0;
                this.state = state;
                divider = dividers[state];
                break;

                case 7: totalImages = imageArray[7].length;
                currentFrame = 0;
                this.state = state;
                divider = dividers[state];
                break;

                case 8: totalImages = imageArray[8].length;
                currentFrame = 0;
                this.state = state;
                divider = dividers[state];
                break;

                case 9: totalImages = imageArray[9].length;
                currentFrame = 0;
                this.state = state;
                divider = dividers[state];
                break;

                case 10: totalImages = 1;
                currentFrame = 0;
                divider = dividers[state];
                this.state = lookDirection + 10;
                break;
                
                case 11: totalImages = imageArray[11].length;
                currentFrame = 0;
                this.state = state;
                divider = dividers[state];
                break;

                case 12: totalImages = imageArray[12].length;
                currentFrame = 0;
                this.state = state;
                divider = dividers[state];
                break;

                case 13: totalImages = imageArray[13].length;
                currentFrame = 0;
                this.state = state;
                divider = dividers[state];
                break;

                case 14: totalImages = imageArray[14].length;
                currentFrame = 0;
                this.state = state;
                divider = dividers[state];
                break;
            }
        }
        changeReady = false;
    }



    public void spawn() {

    }

    public boolean colisionDetection(int direction) {
        for (int i = 0; i < entities.size(); i++) {
            if (colisionCheck(direction, this, entities.get(i)) == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void deathSequence() {
        changeState(10);
        invincible = true;
    }



    public void fade() {
        if (transparancy > 0) {
            transparancy -= 0.02f;
            if (transparancy <= 0) {
                GameEngine.engine.toRemove.add(this);
                transparancy = 0.01f;
            }
        } else {
            GameEngine.engine.toRemove.add(this);
        }
    }

    public void move(int direction) {
        // TODO Auto-generated method stub

        switch (direction){
            case 1:
        if (position[0] < 870 && (colisionDetection(direction))) {
            position[0] += speed;
            spritePosition[0] += speed;
        }
        break;
        case 2:
        if (position[0] > 50 && (colisionDetection(direction))) {
            position[0] -= speed;
            spritePosition[0] -= speed;
        }
        break;
        case 3:
        if (position[1] > 30 && (colisionDetection(direction))) {
            position[1] -= speed;
            spritePosition[1] -= speed;
            //setComponentZOrder(this, position[1]);
        }
        break;
        case 4:
        if (position[1] < 700 && (colisionDetection(direction))) {
            position[1] += speed;
            spritePosition[1] += speed;
        }
        
        }
        
    }


    public void stunned() {
        // TODO Auto-generated method stub
        
    }


    public void attack(int direction) {
        System.out.println("WARNING: DIDNT OVERRIDE ATTACK!");
        if (delay > 0) {
            return;
        }

        Slashattack slashAttack = new Slashattack(1, direction, (Entity) this, 10, this.position, 15, GameEngine.engine.loader);
        GameEngine.engine.frame.getLayeredPane().add(slashAttack);
        GameEngine.engine.frame.getLayeredPane().setLayer(slashAttack, 1900);
        delay = slashAttack.totalFrames * 2;
    }

    public void die() {
        // TODO Auto-generated method stub

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
//        imageArray_walkRight[currentFrame].drawImage()
        Graphics2D g2d = (Graphics2D)(g);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparancy));
        
        g.drawImage(imageArray[state][currentFrame / divider], 0, 0, this.getWidth(), this.getHeight(), this);
    }

}
