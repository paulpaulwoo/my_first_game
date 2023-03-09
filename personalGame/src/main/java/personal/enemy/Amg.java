package personal.enemy;

import personal.Entity;
import personal.GameEngine;
import personal.attacks.Slashattack;
import personal.player.Player;

public class Amg extends Enemy {

    //ENEMY BASE DATA CONSTANTS

    public static final int AMGSPRITEWIDTH = 80;
    public static final int AMGSPRITEHEIGHT = 100;
    public static final int AMGWidth = 100;
    public static final int AMGHeight = 100;
    public static final int AMGSpeed = 5;
    public static final int AMGMaxHP = 100;
    public static final int AMGCurentHP = 100;
    public static final int AMGStates = 15;
    public static final int AMGAttackRange = 65;
    public static final int AMGAttackWidth = 65;
    public static final int AMGAttackDamage = 10;
    public static final int AMGIframes = 15;
    public static final int[] AMGanimationLengths = {0, 2, 2, 2, 2, 50, 2, 2, 2, 2, 0, 2, 2, 2, 2};
    public static final String[] AMGanimationPrefixes = {"", "last-guardian-sprites/amg1_rt", "last-guardian-sprites/amg1_lf", "last-guardian-sprites/amg1_bk", "last-guardian-sprites/amg1_fr", "../effects/spawn/spawn (", "last-guardian-sprites/amg1_rt", "last-guardian-sprites/amg1_lf", "last-guardian-sprites/amg1_bk", "last-guardian-sprites/amg1_fr",  "", "last-guardian-sprites/amg1_rt", "last-guardian-sprites/amg1_lf", "last-guardian-sprites/amg1_bk", "last-guardian-sprites/amg1_fr"};
    public static final String[] AMGanimationSuffixes = {".gif",".gif",".gif",".gif",".gif",").png",".gif",".gif",".gif",".gif",".gif",".gif",".gif",".gif",".gif"};
    public static final int[] AMGdividers = {1, 8, 8, 8, 8, 1, 5, 5, 5, 5, 150, 150, 150, 150, 150};

    //ENEMY BASE DATA CONSTANTS END


    public Amg(Player player, int xpos, int ypos) {
        super(AMGSPRITEWIDTH, AMGSPRITEHEIGHT, AMGWidth, AMGHeight, AMGSpeed, 
        xpos, ypos, AMGMaxHP, AMGCurentHP, AMGStates, AMGAttackRange, 
        AMGAttackWidth, player, AMGanimationLengths, AMGanimationPrefixes, 
        AMGanimationSuffixes, AMGdividers);

        
    }
    
    @Override
    public void changeState(int state) {
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
                divider = 5;
                break;

                case 7: totalImages = imageArray[7].length;
                currentFrame = 0;
                this.state = state;
                divider = 5;
                break;

                case 8: totalImages = imageArray[8].length;
                currentFrame = 0;
                this.state = state;
                divider = 5;
                break;

                case 9: totalImages = imageArray[9].length;
                currentFrame = 0;
                this.state = state;
                divider = 5;
                break;

                case 10: totalImages = 1;
                currentFrame = 0;
                divider = 150;
                this.state = lookDirection + 10;
                break;
                
                case 11: totalImages = imageArray[11].length;
                currentFrame = 0;
                this.state = state;
                divider = 150;
                break;

                case 12: totalImages = imageArray[12].length;
                currentFrame = 0;
                this.state = state;
                divider = 150;
                break;

                case 13: totalImages = imageArray[13].length;
                currentFrame = 0;
                this.state = state;
                divider = 150;
                break;

                case 14: totalImages = imageArray[14].length;
                currentFrame = 0;
                this.state = state;
                divider = 150;
                break;
            }
        }
        changeReady = false;
    }

    @Override
    public void attack(int direction) {
        if (delay > 0) {
            return;
        }
        Slashattack slashAttack = new Slashattack(1, direction, (Entity) this, 10, this.position, 15, GameEngine.loader);
        GameEngine.engine.frame.getLayeredPane().add(slashAttack);
        GameEngine.engine.frame.getLayeredPane().setLayer(slashAttack, 1900);
        delay = slashAttack.totalFrames * 2;
    }
}
