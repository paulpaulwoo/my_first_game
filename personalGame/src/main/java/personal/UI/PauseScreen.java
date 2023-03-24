package personal.UI;

import java.awt.Color;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Graphics;
import personal.Background;
import personal.GameEngine;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.AlphaComposite;
public class PauseScreen extends JPanel {
    public PauseScreen() {
        //super();
        this.setBounds(0, 0, 1024,900);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.setBackground(new Color(128, 128, 128, 20));
        //this.setOpaque(false);
        PauseButton firstButton = new PauseButton("Resume") {
            @Override
            public void toExecute() {
                GameEngine.endPause();
            };
        };
        PauseButton secondButton = new PauseButton("Exit to main menu") {
            @Override
            public void toExecute() {
                GameEngine.combatSequenceEnd(0);

            };
        };

        Dimension d0 = new Dimension(Integer.MAX_VALUE, (int) (this.getHeight() * 0.1));
        Dimension d1 = new Dimension(Integer.MAX_VALUE, (int) (this.getHeight() * 0.1));
        

        firstButton.setPreferredSize(d0);
        firstButton.setMaximumSize(d0);
        firstButton.setMinimumSize(d0);

        secondButton.setPreferredSize(d1);
        secondButton.setMaximumSize(d1);
        secondButton.setMinimumSize(d1);
        //this.setOpaque(false);
        this.add(Box.createVerticalGlue());
        this.add(firstButton);
        this.add(Box.createVerticalGlue());
        this.add(secondButton);
        this.add(Box.createVerticalGlue());
        //this.setVisible(true);
    }


}
