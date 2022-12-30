package personal.attacks;

import personal.Entity;
import personal.GameEngine;
import personal.Sounds.Sound;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import personal.player.Player;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;

public class Attack extends Entity {
    public int totalFrames;
    int baseDirection;
    int direction;
    int animationFrames;
    int divider;
    int currentFrame;
    String imagePrefix;
    String imageSuffix;
    private BufferedImage imageArray[];
    private BufferedImage displayArray[];
    public Entity source;
    boolean piercing;
    int damage;
    int iFrames;
    int effectId;
    HashSet<Entity> hitTracker;
    int soundId;
    int soundHitId;

    public Attack(int totalFrames, int baseDirection, int direction, int animationFrames, int divider, BufferedImage[] imageArray, Entity source, boolean piercing, int damage, int iFrames, int sprite_width, int sprite_height, int width, int height, int soundId, int soundHitId) {
        super(sprite_width, sprite_height, width, height, 0, 0, 0);
        this.totalFrames = totalFrames;
        this.direction = direction;
        this.baseDirection = baseDirection;
        this.animationFrames = animationFrames;
        this.divider = divider;
        this.source = source;
        this.piercing = piercing;
        this.damage = damage;
        this.iFrames = iFrames;
        currentFrame = 0;
        this.imageArray = imageArray;
        this.position = setPosition(source.position, direction, width, height, source);
        this.displayArray = new BufferedImage[animationFrames];
        this.soundId = soundId;
        this.soundHitId = soundHitId;
        hitTracker = new HashSet<Entity>();
        for (int i = 0; i < animationFrames; i++) {
            displayArray[i] = rotate(imageArray[i], getRotationDegrees(baseDirection, direction), width, height);
        }
        this.setBounds(position[0], position[1], width, height);
        Sound.playSound(soundId);
    }
    
    private int [] setPosition(int [] position, int direction, int width, int height, Entity source) {// init for position based on height and width, direction
        int[] returnpos = new int[2];
        if (direction == 1) {
            returnpos[0] = source.spritePosition[0] + source.SPRITEWIDTH;
            returnpos[1] = source.spritePosition[1] + ((source.SPRITEHEIGHT - height) / 2);
        } else if (direction == 2) {
            returnpos[0] = source.spritePosition[0] - width;
            returnpos[1] = source.spritePosition[1] + ((source.SPRITEHEIGHT - height) / 2);
        } else if (direction == 3) {
            returnpos[0] = source.spritePosition[0] + ((source.SPRITEWIDTH - width) / 2);
            returnpos[1] = source.spritePosition[1] - SPRITEHEIGHT / 2;
            System.out.println("Original pos : " + position[0] + ", new pos :" + returnpos[0]) ;
        } else if (direction == 4) {
            returnpos[0] = source.spritePosition[0] + ((source.SPRITEWIDTH - width) / 2);
            returnpos[1] = source.spritePosition[1] + SPRITEHEIGHT;
        }
        return returnpos;
    }
    public static int getRotationDegrees(int originalDirection, int direction) {
        System.out.println("OriginalDirection :" + originalDirection);
        System.out.println("direction :" + direction);
        if (originalDirection == direction) {
            return 0;
        } else if (originalDirection == 1) {
            if (direction == 1) {
                return 0;
            } else if (direction == 2) {
                return 180;
            } else if (direction == 3) {
                return 270;
            } else {
                return 90;
            }
        } else if (originalDirection == 2) {
            if (direction == 1) {
                return 180;
            } else if (direction == 2) {
                return 0;
            } else if (direction == 3) {
                return 90;
            } else {
                return 270;
            }
        } else if (originalDirection == 3) {
            if (direction == 1) {
                return 90;
            } else if (direction == 2) {
                return 270;
            } else if (direction == 3) {
                return 0;
            } else {
                return 180;
            }
        } else {
            if (direction == 1) {
                return 270;
            } else if (direction == 2) {
                return 90;
            } else if (direction == 3) {
                return 180;
            } else {
                return 0;                
            }
        }
        
    }


    //base function from geeksforgeeks
    public static BufferedImage rotate(BufferedImage img, int degrees, int width, int height) {
        // Getting Dimensions of image        
        // Creating a new buffered image
        BufferedImage newImage = new BufferedImage(width, height, img.getType()); //hack, png is 5
         
        // creating Graphics in buffered image
        Graphics2D g2 = newImage.createGraphics();
         
        // Rotating image by degrees using toradians()
        // method
        // and setting new dimension on it
        g2.rotate(Math.toRadians(degrees), width / 2,
            height / 2);
        g2.drawImage(img, null, 0, 0);
        // Return rotated buffer image
        return newImage;
    }
    
    public void attackLogic(Entity source, Entity target) {
        System.out.println("ATTACK HIT, didn't override");
        // TODO: Develop attack logic, including being stunned, lowering hp, and invincibility frames
        Sound.playSound(soundHitId);
    };


    public void update() {
        if (totalFrames <= 0) {
            GameEngine.engine.toRemove.add(this);
        }
        for (int i = 0; i < entities.size(); i++) {
            if ((colisionCheck(direction, this, entities.get(i)) == false) && (!hitTracker.contains(entities.get(i)))) {
                attackLogic(source, entities.get(i));
                hitTracker.add(entities.get(i));
            }
        }
    }

    //need to set Position, WIDTH, HEIGHT, SPRITEWIDTH, SPRITEHEIGHT
    public void calculatePositions(int baseDirection, int direction, int width, int height, int sprite_width, int sprite_height) {
        int toRotate = getRotationDegrees(baseDirection, direction);
        //getheight
    }

    @Override
    public void paintComponent(Graphics g) {
        currentFrame++;
        totalFrames--;
        if (currentFrame >= imageArray.length * divider)  {
            currentFrame = 0;
        }
        super.paintComponent(g);
//        imageArray_walkRight[currentFrame].drawImage()
        g.drawImage(displayArray[currentFrame / divider], 0, 0, this.getWidth(), this.getHeight(), this);
    }

    
}
