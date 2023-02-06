package personal.UI;

import javax.swing.JPanel;
import personal.GameEngine;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Image;


public class TitleScreen extends JPanel {
    BufferedImage img;
    BufferedImage emptyImage;
    Graphics2D grph;
    public TitleScreen() {
        //JFrame frame = GameEngine.engine.frame;
        JFrame frame = GameEngine.frameClear();
        //frame.getLayeredPane().invalidate();
       //frame.getLayeredPane().removeAll();
        //frame.getLayeredPane().validate();
        //frame.getLayeredPane().repaint();

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.add(Box.createVerticalStrut(300));
        c.ipadx = 50;
        c.ipady = 25;
        c.gridwidth = 1;
        c.gridheight = 4;
        c.weightx = 1.0;
        c.weighty = 0.5;
        try {
            img = ImageIO.read(getClass().getResource("../title.jpg"));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Image newimg = img.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT);
        BufferedImage newBufferedImg = new BufferedImage(newimg.getWidth(null), newimg.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newBufferedImg.createGraphics();
        g.drawImage(newimg, 0, 0, null);
        g.dispose();
        img = newBufferedImg;
        JButton startButton = new JButton("New Game");
        startButton.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameEngine.engine.newGameSequenceInit();;
            }
        });;
        
        startButton.setVisible(true);
        startButton.setFocusable(false);
        c.gridx = 0;
        c.gridy = 2;
        c.ipadx = 50;
        this.add(startButton, c);
        
        JButton loadButton = new JButton("Load Game");
        loadButton.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameEngine.engine.loadSequenceInit();
            }
        });;
        loadButton.setVisible(true);
        loadButton.setFocusable(false);
        //loadButton.setSize(100, 50);
        c.gridx = 1;
        c.gridy = 2;
        this.add(loadButton, c);


        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });;
        exitButton.setVisible(true);
        exitButton.setFocusable(false);
        //exitButton.setSize(100, 50);
        c.gridx = 2;
        c.gridy = 2;
        this.add(exitButton, c);
        
        this.setVisible(true);
        //this.add(startButton);
        //this.add(loadButton);
        //this.add(exitButton);
        this.setBounds(0, 0, 1024,900);

    }
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }
}
