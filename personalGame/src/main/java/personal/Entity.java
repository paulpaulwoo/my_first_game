package personal;



import java.util.ArrayList;

import javax.swing.JPanel;

import personal.attacks.Attack;

public class Entity extends JPanel {

    public int[] position;// index 0: x coords
                            // index 1: y coords 
    public static ArrayList<Entity> entities = new ArrayList<>();       
    public int iFrames = 0;
    public int SPRITEWIDTH;
    public int SPRITEHEIGHT;
    public int WIDTH;
    public int HEIGHT;
    public int speed;
    public static int movebuffer = 10;
    public static boolean colisionCheck(int direction, Entity player, Entity enemy) {
        if (player == enemy) {
            return true;
        }
        if (enemy instanceof Attack ) {
            return true;
        }
        /* 
        System.out.println("player coords: " + player.getPosition()[0] + ", " + player.getPosition()[1]+ "\n" +
        "dimensions: " + player.WIDTH + ", " + player.HEIGHT + ", " + player.SPRITEWIDTH + ", " + player.SPRITEHEIGHT);
        System.out.println("enemy coords: " + enemy.getPosition()[0] + ", " + enemy.getPosition()[1]+ "\n" +
        "dimensions: " + enemy.SPRITEWIDTH + ", " + enemy.SPRITEHEIGHT);
        */
        //DEBUG
        if (direction == 1) {
            //System.out.println("colision check right");
            if (((((player.getPosition()[0] + player.SPRITEWIDTH + movebuffer >= enemy.getPosition()[0]) && (player.getPosition()[0] + player.SPRITEWIDTH + movebuffer <= enemy.position[0] + enemy.SPRITEWIDTH)) 
                    ||((player.getPosition()[0] + movebuffer >= enemy.getPosition()[0]) && (player.getPosition()[0] + movebuffer <= enemy.position[0] + enemy.SPRITEWIDTH)))
                && (((((player.getPosition()[1] + (player.SPRITEHEIGHT) >= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT) <= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))
                    || ((((player.getPosition()[1] + (player.SPRITEHEIGHT / 2) >= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT / 2) <= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))
                    || ((((player.getPosition()[1] + (player.SPRITEHEIGHT / 2) <= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT) >= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))))) {

                return false;
            }
            
        } else if (direction == 2) {
            if (((((player.getPosition()[0] + player.SPRITEWIDTH - movebuffer >= enemy.getPosition()[0]) && (player.getPosition()[0] + player.SPRITEWIDTH - movebuffer <= enemy.position[0] + enemy.SPRITEWIDTH)) 
                    ||((player.getPosition()[0] - movebuffer >= enemy.getPosition()[0]) && (player.getPosition()[0] - movebuffer <= enemy.position[0] + enemy.SPRITEWIDTH)))
                && (((((player.getPosition()[1] + (player.SPRITEHEIGHT) >= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT) <= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))
                    || ((((player.getPosition()[1] + (player.SPRITEHEIGHT / 2) >= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT / 2) <= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))
                    || ((((player.getPosition()[1] + (player.SPRITEHEIGHT / 2) <= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT) >= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))))) {

                return false;
            }

        } else if (direction == 3) {
            if (((((player.getPosition()[0] + player.SPRITEWIDTH >= enemy.getPosition()[0]) && (player.getPosition()[0] + player.SPRITEWIDTH <= enemy.position[0] + enemy.SPRITEWIDTH)) 
                    ||((player.getPosition()[0] >= enemy.getPosition()[0]) && (player.getPosition()[0] <= enemy.position[0] + enemy.SPRITEWIDTH))
                    ||((player.getPosition()[0] <= enemy.getPosition()[0]) && (player.getPosition()[0] + player.SPRITEWIDTH >= enemy.position[0] + enemy.SPRITEWIDTH)))
                && (((((player.getPosition()[1] + (player.SPRITEHEIGHT) - movebuffer >= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT) - movebuffer <= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))
                    || ((player.getPosition()[1] + (player.SPRITEHEIGHT / 2) - movebuffer >= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT / 2) - movebuffer <= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))) {

                return false;
            }
        } else if (direction == 4) {
            if (((((player.getPosition()[0] + player.SPRITEWIDTH >= enemy.getPosition()[0]) && (player.getPosition()[0] + player.SPRITEWIDTH <= enemy.position[0] + enemy.SPRITEWIDTH)) 
            ||((player.getPosition()[0] >= enemy.getPosition()[0]) && (player.getPosition()[0] <= enemy.position[0] + enemy.SPRITEWIDTH))
            ||((player.getPosition()[0] <= enemy.getPosition()[0]) && (player.getPosition()[0] + player.SPRITEWIDTH >= enemy.position[0] + enemy.SPRITEWIDTH)))
        && (((((player.getPosition()[1] + (player.SPRITEHEIGHT) + movebuffer >= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT) + movebuffer <= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))
            || ((((player.getPosition()[1] + (player.SPRITEHEIGHT / 2) + movebuffer >= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT / 2) + movebuffer <= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))))) {
        return false;
    }
        }
        return true;
    }

    public static boolean attackCheck(int direction, Entity player, Entity enemy) {
        if (player == enemy) {
            return true;
        }
        /* 
        System.out.println("player coords: " + player.getPosition()[0] + ", " + player.getPosition()[1]+ "\n" +
        "dimensions: " + player.WIDTH + ", " + player.HEIGHT + ", " + player.SPRITEWIDTH + ", " + player.SPRITEHEIGHT);
        System.out.println("enemy coords: " + enemy.getPosition()[0] + ", " + enemy.getPosition()[1]+ "\n" +
        "dimensions: " + enemy.SPRITEWIDTH + ", " + enemy.SPRITEHEIGHT);
        */
        //DEBUG
        if (direction == 1) {
            //System.out.println("colision check right");
            if (((((player.getPosition()[0] + player.SPRITEWIDTH + movebuffer >= enemy.getPosition()[0]) && (player.getPosition()[0] + player.SPRITEWIDTH + movebuffer <= enemy.position[0] + enemy.SPRITEWIDTH)) 
                    ||((player.getPosition()[0] + movebuffer >= enemy.getPosition()[0]) && (player.getPosition()[0] + movebuffer <= enemy.position[0] + enemy.SPRITEWIDTH)))
                && (((((player.getPosition()[1] + (player.SPRITEHEIGHT) >= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT) <= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))
                    || ((((player.getPosition()[1] + (player.SPRITEHEIGHT / 2) >= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT / 2) <= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))
                    || ((((player.getPosition()[1] + (player.SPRITEHEIGHT / 2) <= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT) >= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))))) {
                    System.out.println("colided");
                return false;
            }
            
        } else if (direction == 2) {
            if (((((player.getPosition()[0] + player.SPRITEWIDTH - movebuffer >= enemy.getPosition()[0]) && (player.getPosition()[0] + player.SPRITEWIDTH - movebuffer <= enemy.position[0] + enemy.SPRITEWIDTH)) 
                    ||((player.getPosition()[0] - movebuffer >= enemy.getPosition()[0]) && (player.getPosition()[0] - movebuffer <= enemy.position[0] + enemy.SPRITEWIDTH)))
                && (((((player.getPosition()[1] + (player.SPRITEHEIGHT) >= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT) <= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))
                    || ((((player.getPosition()[1] + (player.SPRITEHEIGHT / 2) >= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT / 2) <= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))
                    || ((((player.getPosition()[1] + (player.SPRITEHEIGHT / 2) <= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT) >= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))))) {
                    System.out.println("colided");
                return false;
            }

        } else if (direction == 3) {
            if (((((player.getPosition()[0] + player.SPRITEWIDTH >= enemy.getPosition()[0]) && (player.getPosition()[0] + player.SPRITEWIDTH <= enemy.position[0] + enemy.SPRITEWIDTH)) 
                    ||((player.getPosition()[0] >= enemy.getPosition()[0]) && (player.getPosition()[0] <= enemy.position[0] + enemy.SPRITEWIDTH))
                    ||((player.getPosition()[0] <= enemy.getPosition()[0]) && (player.getPosition()[0] + player.SPRITEWIDTH >= enemy.position[0] + enemy.SPRITEWIDTH)))
                && (((((player.getPosition()[1] + (player.SPRITEHEIGHT) - movebuffer >= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT) - movebuffer <= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))
                    || ((player.getPosition()[1] + (player.SPRITEHEIGHT / 2) - movebuffer >= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT / 2) - movebuffer <= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))) {
                    System.out.println("colided");
                return false;
            }
        } else if (direction == 4) {
            if (((((player.getPosition()[0] + player.SPRITEWIDTH >= enemy.getPosition()[0]) && (player.getPosition()[0] + player.SPRITEWIDTH <= enemy.position[0] + enemy.SPRITEWIDTH)) 
            ||((player.getPosition()[0] >= enemy.getPosition()[0]) && (player.getPosition()[0] <= enemy.position[0] + enemy.SPRITEWIDTH))
            ||((player.getPosition()[0] <= enemy.getPosition()[0]) && (player.getPosition()[0] + player.SPRITEWIDTH >= enemy.position[0] + enemy.SPRITEWIDTH)))
        && (((((player.getPosition()[1] + (player.SPRITEHEIGHT) + movebuffer >= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT) + movebuffer <= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))
            || ((((player.getPosition()[1] + (player.SPRITEHEIGHT / 2) + movebuffer >= enemy.getPosition()[1] + enemy.SPRITEHEIGHT / 2) && (player.getPosition()[1] + (player.SPRITEHEIGHT / 2) + movebuffer <= enemy.getPosition()[1] +enemy.SPRITEHEIGHT))))))) {
            System.out.println("colided");
        return false;
    }
        }
        return true;
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
