package personal.UI;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

import personal.Entity;
import personal.player.PlayerData;
import personal.GameEngine;
public class MainHpBar extends JPanel {
    PlayerData playerData;


    //hpbar width = screen width / 10
    //hpbar height = screen height / 20

    public MainHpBar(PlayerData playerData) {

        this.playerData = playerData;
        this.setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, (int)(this.getWidth() * ((float)(playerData.HP) / playerData.maxHP)), this.getHeight());
        //g.fillRect(0, 0, 50, height);
        g.setColor(Color.GRAY);
        g.fillRect((int)(getWidth() * ((float)(playerData.HP) / playerData.maxHP)), 0, getWidth() * (1 - playerData.HP / playerData.maxHP), getHeight());
        //g.fillRect(50, 0, width - 50, height);
        super.paintComponent(g);
    }
}
