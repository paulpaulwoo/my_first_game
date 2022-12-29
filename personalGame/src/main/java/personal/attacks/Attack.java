package personal.attacks;

import personal.Entity;
import personal.GameEngine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import personal.player.Player;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Attack extends Entity {
    int totalFrames;
    int baseDirection;
    int direction;
    int animationFrames;
    int divider;
    int currentFrame;
    String imagePrefix;
    String imageSuffix;
    private BufferedImage imageArray[];
    private BufferedImage displayArray[];
    Entity source;
    boolean piercing;
    int damage;
    int iFrames;
    int effectId;

    public Attack(int totalFrames,int baseDirection, int direction, int animationFrames, int divider, String imagePrefix, String imageSuffix, Entity source, boolean piercing, int damage, int iFrames, int sprite_width, int sprite_height, int width, int height) {
        this.totalFrames = totalFrames;
        this.baseDirection = baseDirection;
        this.direction = direction;
        this.animationFrames = animationFrames;
        this.divider = divider;
        this.imagePrefix = imagePrefix;
        this.imageSuffix = imageSuffix;
        this.source = source;
        this.piercing = piercing;
        this.damage = damage;
        position = source.position; // TOCHANGE!!!!!!!!!!
        this.iFrames = iFrames;
        currentFrame = 0;
        this.imageArray = new BufferedImage[animationFrames];
        for (int i = 0; i < animationFrames; i++) {
            try {
                imageArray[i] = ImageIO.read(getClass().getResource(imagePrefix + (1+ i) + imageSuffix));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        entities.add(this);
        this.displayArray = new BufferedImage[animationFrames];
        for (int i = 0; i < animationFrames; i++) {
            displayArray[i] = rotate(imageArray[i], getRotationDegrees(baseDirection, direction), width, height);
        }
        this.setOpaque(false);
        this.setBounds(position[0], position[1], width, height);
    }

    public Attack(int totalFrames, int baseDirection, int direction, int animationFrames, int divider, BufferedImage[] imageArray, Entity source, boolean piercing, int damage, int iFrames, int sprite_width, int sprite_height, int width, int height) {
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
        
        entities.add(this);
        this.displayArray = new BufferedImage[animationFrames];
        for (int i = 0; i < animationFrames; i++) {
            displayArray[i] = rotate(imageArray[i], getRotationDegrees(baseDirection, direction), width, height);
        }
        this.setOpaque(false);
        this.setBounds(position[0], position[1], width, height);
    }
    
    private int [] setPosition(int [] position, int direction, int width, int height, Entity source) {// init for position based on height and width, direction
        int[] returnpos = new int[2];
        if (direction == 1) {
            returnpos[0] = position[0] + source.SPRITEWIDTH;
            returnpos[1] = position[1];
        } else if (direction == 2) {
            returnpos[0] = position[0] - width;
            returnpos[1] = position[1];
        } else if (direction == 3) {
            returnpos[0] = position[0];
            returnpos[1] = position[1] - source.SPRITEHEIGHT;
        } else if (direction == 4) {
            returnpos[0] = position[0];
            returnpos[1] = position[1] + height;
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

    public void update() {
        if (totalFrames <= 0) {
            GameEngine.engine.toRemove.add(this);
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

