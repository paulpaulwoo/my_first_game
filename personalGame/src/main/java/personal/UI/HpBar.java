package personal.UI;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

import personal.Entity;
import personal.GameEngine;
import personal.player.Player;

public class HpBar extends JPanel {
    int width;
    int height;
    Entity player;
    //hpbar width = screen width / 10
    //hpbar height = screen height / 20

    public HpBar(Player player) {
        width = GameEngine.frameWidth / 5;
        height = GameEngine.frameHeight / 30;
        this.player = player;
        setSize(width, height);
        setBounds(width / 2, height, width, height);
        this.setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, (int)(width * ((float)(player.currentHP) / player.maxHP)), height);
        //g.fillRect(0, 0, 50, height);
        g.setColor(Color.GRAY);
        g.fillRect((int)(width * ((float)(player.currentHP) / player.maxHP)), 0, width * (1 - player.currentHP / player.maxHP), height);
        //g.fillRect(50, 0, width - 50, height);
        super.paintComponent(g);
    }
}
