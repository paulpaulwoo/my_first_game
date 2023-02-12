package personal.attacks;
import personal.Entity;
import personal.Loader;
import personal.Sounds.Sound;


public class Slashattack extends Attack {
    public Slashattack(int baseDirection, int direction, Entity source, int damage, int[] position, int iFrames, Loader loader) {
        super(10, baseDirection, direction, 10, 1, loader.getImages(1), source, true, damage, 30, 65, 65, 65, 65, 0, 1);
        effectId = 1;
    }

    @Override
    public void attackLogic(Entity source, Entity target) {
        //TODO
        Sound.playSound(soundHitId);
        target.takeDamage(this.damage, this.iFrames);
    }
}
