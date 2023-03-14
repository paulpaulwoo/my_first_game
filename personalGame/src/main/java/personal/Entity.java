package personal;



import java.util.ArrayList;

import javax.swing.JPanel;

import personal.attacks.Attack;

public class Entity extends JPanel {

    public int[] position;// index 0: x coords
                            // index 1: y coords 
    public static ArrayList<Entity> entities = new ArrayList<>();       
    public int iFrames;
    public int SPRITEWIDTH;
    public int SPRITEHEIGHT;
    public int WIDTH;
    public int HEIGHT;
    public int speed;
    public static int movebuffer = 10;
    public int[] spritePosition;
    public int maxHP;
    public int currentHP;
    public boolean invincible;

    public Entity(int SPRITEWIDTH, int SPRITEHEIGHT, int WIDTH, int HEIGHT, int speed, int xpos, int ypos, int maxHP, int currentHP) {
        //TODO
        iFrames = 0;
        invincible = false;
        this.SPRITEWIDTH = SPRITEWIDTH;
        this.SPRITEHEIGHT = SPRITEHEIGHT;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.speed = speed;
        this.position = new int[2];
        this.spritePosition = new int[2];
        position[0] = xpos;
        position[1] = ypos;
        spritePosition[0] = (position[0] + ((WIDTH - SPRITEWIDTH) / 2));
        spritePosition[1] = (position[1] + ((HEIGHT - SPRITEHEIGHT) / 2));
        this.setSize(WIDTH, HEIGHT);
        entities.add(this);
        this.setOpaque(false);
        this.currentHP = currentHP;
        this.maxHP = maxHP;
    }

    public static void resetEntities() {
        entities = new ArrayList<>();
    }

    public static boolean colisionCheck(int direction, Entity player, Entity enemy) { // false if collided
        int[] playerPosition = {player.position[0] + ((player.WIDTH - player.SPRITEWIDTH) / 2), player.position[1] + ((player.HEIGHT - player.SPRITEHEIGHT) / 2)};
        int[] enemyPosition = {enemy.position[0] + ((enemy.WIDTH - enemy.SPRITEWIDTH) / 2), enemy.position[1] + ((enemy.HEIGHT - enemy.SPRITEHEIGHT) / 2)};
        if (player == enemy) {
            return true;
        }
        if (enemy instanceof Attack ) {
            return true;
        }
        if (player instanceof Attack ) {
            if (((Attack)player).source == enemy) {
                return true;
            }
        }


        if (direction == 1) {
            if (((((playerPosition[0] + player.SPRITEWIDTH + movebuffer >= enemyPosition[0]) && (playerPosition[0] + player.SPRITEWIDTH + movebuffer <= enemyPosition[0] + enemy.SPRITEWIDTH)) 
                    ||((playerPosition[0] + movebuffer >= enemyPosition[0]) && (playerPosition[0] + movebuffer <= enemyPosition[0] + enemy.SPRITEWIDTH)))
                && (((((playerPosition[1] + (player.SPRITEHEIGHT) >= enemyPosition[1] + enemy.SPRITEHEIGHT / 2) && (playerPosition[1] + (player.SPRITEHEIGHT) <= enemyPosition[1] +enemy.SPRITEHEIGHT))))
                    || ((((playerPosition[1] + (player.SPRITEHEIGHT / 2) >= enemyPosition[1] + enemy.SPRITEHEIGHT / 2) && (playerPosition[1] + (player.SPRITEHEIGHT / 2) <= enemyPosition[1] +enemy.SPRITEHEIGHT))))
                    || ((((playerPosition[1] + (player.SPRITEHEIGHT / 2) <= enemyPosition[1] + enemy.SPRITEHEIGHT / 2) && (playerPosition[1] + (player.SPRITEHEIGHT) >= enemyPosition[1] +enemy.SPRITEHEIGHT))))))) {

                return false;
            }
            
        } else if (direction == 2) {
            if (((((playerPosition[0] + player.SPRITEWIDTH - movebuffer >= enemyPosition[0]) && (playerPosition[0] + player.SPRITEWIDTH - movebuffer <= enemyPosition[0] + enemy.SPRITEWIDTH)) 
                    ||((playerPosition[0] - movebuffer >= enemyPosition[0]) && (playerPosition[0] - movebuffer <= enemyPosition[0] + enemy.SPRITEWIDTH)))
                && (((((playerPosition[1] + (player.SPRITEHEIGHT) >= enemyPosition[1] + enemy.SPRITEHEIGHT / 2) && (playerPosition[1] + (player.SPRITEHEIGHT) <= enemyPosition[1] +enemy.SPRITEHEIGHT))))
                    || ((((playerPosition[1] + (player.SPRITEHEIGHT / 2) >= enemyPosition[1] + enemy.SPRITEHEIGHT / 2) && (playerPosition[1] + (player.SPRITEHEIGHT / 2) <= enemyPosition[1] +enemy.SPRITEHEIGHT))))
                    || ((((playerPosition[1] + (player.SPRITEHEIGHT / 2) <= enemyPosition[1] + enemy.SPRITEHEIGHT / 2) && (playerPosition[1] + (player.SPRITEHEIGHT) >= enemyPosition[1] +enemy.SPRITEHEIGHT))))))) {

                return false;
            }

        } else if (direction == 3) {
            if (((((playerPosition[0] + player.SPRITEWIDTH >= enemyPosition[0]) && (playerPosition[0] + player.SPRITEWIDTH <= enemyPosition[0] + enemy.SPRITEWIDTH)) 
                    ||((playerPosition[0] >= enemyPosition[0]) && (playerPosition[0] <= enemyPosition[0] + enemy.SPRITEWIDTH))
                    ||((playerPosition[0] <= enemyPosition[0]) && (playerPosition[0] + player.SPRITEWIDTH >= enemyPosition[0] + enemy.SPRITEWIDTH)))
                && (((((playerPosition[1] + (player.SPRITEHEIGHT) - movebuffer >= enemyPosition[1] + enemy.SPRITEHEIGHT / 2) && (playerPosition[1] + (player.SPRITEHEIGHT) - movebuffer <= enemyPosition[1] +enemy.SPRITEHEIGHT))))
                    || ((playerPosition[1] + (player.SPRITEHEIGHT / 2) - movebuffer >= enemyPosition[1] + enemy.SPRITEHEIGHT / 2) && (playerPosition[1] + (player.SPRITEHEIGHT / 2) - movebuffer <= enemyPosition[1] +enemy.SPRITEHEIGHT))))) {

                return false;
            } 
        } else if (direction == 4) {
            if (((((playerPosition[0] + player.SPRITEWIDTH >= enemyPosition[0]) && (playerPosition[0] + player.SPRITEWIDTH <= enemyPosition[0] + enemy.SPRITEWIDTH)) 
            ||((playerPosition[0] >= enemyPosition[0]) && (playerPosition[0] <= enemyPosition[0] + enemy.SPRITEWIDTH))
            ||((playerPosition[0] <= enemyPosition[0]) && (playerPosition[0] + player.SPRITEWIDTH >= enemyPosition[0] + enemy.SPRITEWIDTH)))
        && (((((playerPosition[1] + (player.SPRITEHEIGHT) + movebuffer >= enemyPosition[1] + enemy.SPRITEHEIGHT / 2) && (playerPosition[1] + (player.SPRITEHEIGHT) + movebuffer <= enemyPosition[1] +enemy.SPRITEHEIGHT))))
            || ((((playerPosition[1] + (player.SPRITEHEIGHT / 2) + movebuffer >= enemyPosition[1] + enemy.SPRITEHEIGHT / 2) && (playerPosition[1] + (player.SPRITEHEIGHT / 2) + movebuffer <= enemyPosition[1] +enemy.SPRITEHEIGHT))))))) {
        return false;
            }
        }
        return true;
    }

    public void takeDamage(int damage, int iFrames) {
        this.currentHP -= damage;
        this.iFrames = iFrames;
        this.invincible = true;
        if (currentHP <= 0) {
            //TRIGGER DEATH
            this.iFrames = 0;
            deathSequence();
        }
    }

    public void changeState(int state) {
        //for override
    }

    public void deathSequence() {
        //for override
    }
    

    public int[] getPosition() {
        //for override
        return position;
    }
    public void update() {
        //for override
    }
}


/*
 * 
 
 */
