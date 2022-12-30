package personal;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Image;
import javax.imageio.ImageIO;

import personal.loadedImage;



public class Loader { //class to load in images and store it in Ram rather than using File IO
                      //every single time
        
    public Map<Integer, BufferedImage []> images = new HashMap<>();
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
    public BufferedImage[] getImages (int identifier) {
        return images.get(Integer.valueOf(identifier));
    }
}
