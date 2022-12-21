package personal;

import personal.player.Player;
import personal.enemy.Enemy;
import javax.swing.JPanel;

public class Entity extends JPanel {
    public boolean moveup = false; // used for height
    public boolean movedown = false;// used for height
    public static boolean colisionCheck(int direction, Player player, Enemy enemy) {
        
        if (direction == 1) {
            //System.out.println("colision check right");
            if ((((player.getPosition()[0] + player.WIDTH + player.speed > enemy.getPosition()[0]) && (player.getPosition()[0] + player.WIDTH + player.speed < enemy.position[0] + enemy.WIDTH)) 
                && ((((player.getPosition()[1] + (player.HEIGHT / 1.4) > enemy.getPosition()[1]) && (player.getPosition()[1] + (player.HEIGHT / 1.4) < enemy.getPosition()[1] +enemy.HEIGHT)))))) {
                return false;
            }
        } else if (direction == 2) {
            if ((((player.getPosition()[0] - player.speed> enemy.getPosition()[0]) && (player.getPosition()[0] - player.speed < enemy.position[0] + enemy.WIDTH)) 
                && ((((player.getPosition()[1] + (player.HEIGHT / 1.4) > enemy.getPosition()[1]) && (player.getPosition()[1] + (player.HEIGHT / 1.4) < enemy.getPosition()[1] +enemy.HEIGHT)))))) {
                return false;
            }
        } else if (direction == 3) {
            if ((((player.getPosition()[0] + player.WIDTH > enemy.getPosition()[0]) && (player.getPosition()[0] < enemy.position[0] + enemy.WIDTH)) 
                && ((((player.getPosition()[1] + (player.HEIGHT / 1.4)  - player.speed > enemy.getPosition()[1]) && (player.getPosition()[1] + (player.HEIGHT / 1.4)  - player.speed < enemy.getPosition()[1] +enemy.HEIGHT)))))) {
                return false;
            }
        } else if (direction == 4) {
            System.out.println("triggered");
            if ((((player.getPosition()[0] + player.WIDTH > enemy.getPosition()[0]) && (player.getPosition()[0] < enemy.position[0] + enemy.WIDTH)) 
                && ((((player.getPosition()[1] + (player.HEIGHT / 1.4) + player.speed> enemy.getPosition()[1]) && (player.getPosition()[1] + (player.HEIGHT / 1.4) + player.speed < enemy.getPosition()[1] +enemy.HEIGHT)))))) {
                return false;
            }
        }
        return true;
    }
    public int[] getPosition() {
        return null;
    }
}


/*
 * 
 
 */
