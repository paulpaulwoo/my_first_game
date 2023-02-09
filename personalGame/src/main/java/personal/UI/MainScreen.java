package personal.UI;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import personal.GameEngine;
import personal.player.PlayerData;
import personal.events.Event;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import personal.UI.ChoiceButton;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.awt.Graphics2D;
import java.awt.Image;

public class MainScreen extends JPanel {

    public JPanel textPanel;
    public JLabel textLabel;
    public JPanel eventPanel;
    public JPanel eventTextPanel;
    public JLabel eventTextLabel;
    public JPanel eventChoicePanel;
    Event currentEvent;
    public static PlayerData pData;
    public MainScreen(PlayerData data) {
        pData = data;
        JFrame frame = GameEngine.frameClear();
        this.setBounds(0, 0, 1024,900);
        currentEvent = Event.getEvent(pData.nextEventId);
    
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 3;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.BOTH;
        textPanel = makeTextPanel(currentEvent.baseString);
        this.add(textPanel, c);
        c.gridwidth = 3;
        c.gridheight = 3;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;        
        //c.weightx = ;
        //c.weighty = 0.1;
        eventPanel = makeEventPanel(currentEvent);
        this.add(eventPanel, c);
        
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }


    public JPanel makeTextPanel(String text) {
        textPanel = new JPanel();
        //panel.setLayout(new GridBagLayout());
        textPanel.setBackground(Color.BLACK);
        textPanel.setBorder(new LineBorder(Color.WHITE, 10));
        
        textLabel = new JLabel(text);
        textLabel.setFont(new Font("Verdana",1,30));
        textLabel.setForeground(Color.WHITE);
        textLabel.setVisible(true);
        textPanel.add(textLabel);
        textPanel.setVisible(true);
        return textPanel;
    }

    public JPanel makeEventPanel(Event event) {
        eventPanel = new JPanel();
        eventPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        eventPanel.setBackground(Color.WHITE);
        eventPanel.setBorder(new LineBorder(Color.BLACK, 5));
        eventTextPanel = new JPanel();        
        eventTextPanel.setLayout(new GridBagLayout());
        eventTextPanel.add(Box.createVerticalStrut(50));
        eventTextPanel.add(Box.createHorizontalStrut(50));
        eventTextLabel = new JLabel(event.baseChoiceString);
        eventTextPanel.add(eventTextLabel);
        c.ipadx = 50;
        c.ipady = 25;
        c.gridwidth = 3;
        c.gridheight = 4;
        c.weightx = 0.7;
        c.weighty = 1;
        eventPanel.add(eventTextPanel, c);

        c.ipadx = 50;
        c.ipady = 25;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.3;
        c.weighty = 0.3;
        
        eventChoicePanel = new JPanel();
        eventChoicePanel.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        c2.ipadx = 50;
        c2.ipady = 25;
        c2.gridwidth = 1;
        c2.gridheight = 1;
        c2.weightx = 0.3;
        c2.weighty = 0.3;
        c2.gridx = 0;
        
        for (int i = 0; i < event.numOfChoices; i++) {
            //JButton choiceButton = new JButton(event.choiceStrings[i]);
            ChoiceButton choiceButton = new ChoiceButton(this, currentEvent, i);
            /*
            /* 
            final int finI = i;
            choiceButton.addActionListener((ActionListener) new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    event.choiceAction(finI);
                }
            });;
            
            MouseListener ml = new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    //panel.setBorder(new LineBorder(Color.RED, 10));


                }
                @Override
                public void mouseExited(MouseEvent e) {
                    //panel.setBorder(new LineBorder(Color.BLUE, 10));
                }
            };
            */
            
            

            c2.gridy = i;
            eventChoicePanel.add(choiceButton, c2);
        }
        eventPanel.add(eventChoicePanel, c);
        this.setVisible(true);
        this.setBounds(0, 0, 1024,900);


        return eventPanel;
    }

    public void choiceMade(int choiceId) {
        currentEvent.choiceAction(choiceId);
    }

    public void updateTextBox(String text) {
        textLabel.setText(text);
    }


    
/* 
    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create(); 
        Reader reader;
        try {
            reader = new FileReader(MainScreen.class.getClassLoader().getResource("").getPath()+ "personal/saves/save");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        PlayerData[] data = gson.fromJson(reader, PlayerData[].class);
        JFrame frameA = new JFrame();
        frameA.add(new MainScreen(pData));
        frameA.setVisible(true);
        Thread t = new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                frameA.repaint();
            }
        });
        t.start();
    }
    */
}
 