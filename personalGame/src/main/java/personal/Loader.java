package personal;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;


public class Loader { //class to load in images and store it in Ram rather than using File IO
                      //every single time
        
    public Map<Integer, BufferedImage []> images = new HashMap<>();
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

    public void Load(ArrayList<loadedImage> toLoad) {
        for (int i = 0; i < toLoad.size(); i++) {
            BufferedImage[] animation = new BufferedImage[toLoad.get(i).animationLength];
            for (int j = 0; j < animation.length; j++) {
                try {
                    System.out.println(toLoad.get(i).prefix + (j + 1) + toLoad.get(i).suffix);
                    //System.out.println(getClass());
                    //animation[j] = ImageIO.read(getClass().getResource(toLoad.get(i).prefix + (j + 1) + toLoad.get(i).suffix));
                    animation[j] = ImageIO.read(new FileInputStream(toLoad.get(i).prefix + (j + 1) + toLoad.get(i).suffix));
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

    public void unLoad(int identifier) {
        images.remove(Integer.valueOf(identifier));
    }
}
