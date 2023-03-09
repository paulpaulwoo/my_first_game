package personal;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;

import personal.attacks.Attack;


public class Loader { //class to load in images and store it in Ram rather than using File IO
                      //every single time
        
    public Map<Integer, BufferedImage []> images = new HashMap<>();
    public ReentrantLock mutex = new ReentrantLock(true);
    
    public Loader() {

    }
    

    public Loader (ArrayList<loadedImage> toLoad) { // sizes = array of each of the 
        for (int i = 0; i < toLoad.size(); i++) {
            BufferedImage[] animation = new BufferedImage[toLoad.get(i).animationLength];
            for (int j = 0; j < animation.length; j++) {
                try {                    
                    animation[j] = ImageIO.read(getClass().getResource(toLoad.get(i).prefix + (j + 1) + toLoad.get(i).suffix));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
            images.put(Integer.valueOf(toLoad.get(i).identifier), animation);
        }
    }



    public void unLoadAll() {
        images = new HashMap<>();
    }


    public void Load(ArrayList<loadedImage> toLoad) {
        for (int i = 0; i < toLoad.size(); i++) {
            BufferedImage[] animation = new BufferedImage[toLoad.get(i).animationLength];
            for (int j = 0; j < animation.length; j++) {
                try {
                    System.out.println(toLoad.get(i).prefix + (j + 1) + toLoad.get(i).suffix);
                    //System.out.println(getClass());
                    //animation[j] = ImageIO.read(getClass().getResource(toLoad.get(i).prefix + (j + 1) + toLoad.get(i).suffix));
                    animation[j] = ImageIO.read(getClass().getResource(toLoad.get(i).prefix + (j + 1) + toLoad.get(i).suffix));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
            images.put(Integer.valueOf(toLoad.get(i).identifier), animation);
        }
    }


    public void Load(Deque<loadedImage> stack) {
        //Deque<loadedImage> stack = new ArrayDeque<Integer>();
        while (!stack.isEmpty()) {
            loadedImage currentAnimation = stack.pop();
            BufferedImage[] animation = new BufferedImage[currentAnimation.animationLength];
            for (int j = 0; j < animation.length; j++) {
                try {
                    System.out.println(currentAnimation.prefix + (j + 1) + currentAnimation.suffix);
                    //System.out.println(getClass());
                    //animation[j] = ImageIO.read(getClass().getResource(toLoad.get(i).prefix + (j + 1) + toLoad.get(i).suffix));
                    animation[j] = ImageIO.read(getClass().getResource(currentAnimation.prefix + (j + 1) + currentAnimation.suffix));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
            images.put(Integer.valueOf(currentAnimation.identifier), animation);
        }
    }

    public void loadIndividual (loadedImage currentAnimation) {
        BufferedImage[] animation = new BufferedImage[currentAnimation.animationLength];
            for (int j = 0; j < animation.length; j++) {
                try {
                    System.out.println(currentAnimation.prefix + (j + 1) + currentAnimation.suffix);
                    //System.out.println(getClass());
                    //animation[j] = ImageIO.read(getClass().getResource(toLoad.get(i).prefix + (j + 1) + toLoad.get(i).suffix));
                    animation[j] = ImageIO.read(getClass().getResource(currentAnimation.prefix + (j + 1) + currentAnimation.suffix));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            images.put(Integer.valueOf(currentAnimation.identifier), animation);
    }


    public static BufferedImage[] getRotatedAnimation(BufferedImage[] original, int animationLength, int originalDirection, int rotateDirection) {
        BufferedImage[] rotatedImages = new BufferedImage[animationLength];
        int degrees = Attack.getRotationDegrees(originalDirection, rotateDirection);
        for(int i = 0; i < animationLength; i++) {
            rotatedImages[i] = Attack.rotate(original[i], degrees, original[i].getWidth(), original[i].getHeight());
        }
        return rotatedImages;
    }

    public void combatLoad() {
        mutex.lock();

        loadIndividual(new loadedImage("./effects/slash/slash (", ").png", 10, 1));
        images.put(2, getRotatedAnimation(images.get(1), 10, 1, 2));
        images.put(3, getRotatedAnimation(images.get(1), 10, 1, 3));
        images.put(4, getRotatedAnimation(images.get(1), 10, 1, 4));
        mutex.unlock();
    }

    public void combatUnload() {
        unLoad(1);
        unLoad(2);
        unLoad(3);
        unLoad(4);
    }

    public BufferedImage[] getImages (int identifier) {
        return images.get(Integer.valueOf(identifier));
    }

    public void unLoad(int identifier) {
        images.remove(Integer.valueOf(identifier));
    }
}
