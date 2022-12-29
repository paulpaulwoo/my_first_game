package personal.attacks;
import personal.Entity;
import personal.Loader;


public class Slashattack extends Attack {
    public Slashattack(int baseDirection, int direction, Entity source, int damage, int[] position, int iFrames, Loader loader) {
        
        super(10, baseDirection, direction, 10, 1, loader.getImages(1), source, true, damage, 15, 100, 100, 100, 100);
        System.out.println(loader.getImages(1).length);
        effectId = 1;
    }
    public Slashattack(int baseDirection, int direction, Entity source, int damage, int[] position, int iFrames) {
        super(10, baseDirection, direction, 10, 1, "../effects/slash/slash (", ").png", source, true, damage, 15, 100, 100, 100, 100);
        effectId = 1;
    }
}
