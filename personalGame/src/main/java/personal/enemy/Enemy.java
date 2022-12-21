package personal.enemy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import personal.Entity;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Enemy extends Entity {
    public int[] position;// index 0: x coords
                            // index 1: y coords        
    public void update() {}; //AI triggerevery .02 secs //playercoords x and y
    public void changeState(int state) {}; // changes the sequence, sequence change changes the animation too.
    public void attack() {}; // attack sequence
    public void move() {}; //move sequence
    public void die() {}; //kill sequence
    public void stunned() {}; // stunned sequence (by attack)
    public void spawn() {};
    public int WIDTH;
    public int HEIGHT;
}
 