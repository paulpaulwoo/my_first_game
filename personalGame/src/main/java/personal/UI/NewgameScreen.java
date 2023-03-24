package personal.UI;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import personal.GameEngine;
import personal.player.PlayerData;
import java.awt.event.MouseListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.awt.Graphics2D;
import javax.swing.border.LineBorder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class NewgameScreen extends JPanel {
    public static NewgameScreen screen;
    BufferedImage img;
    BufferedImage emptyImage;
    Graphics2D grph;
    public static PlayerData[] saves;
    public static int currentData = 0;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create(); 
    JPanel currentPanel;
    public NewgameScreen() {
        screen = this;
        GameEngine.frameClear();
        saves = new PlayerData[4];
        //URL url = this.getClass().getClassLoader().getResource("personal/saves/save");
        File fileCheck = new File(this.getClass().getClassLoader().getResource("").getPath()+ "personal/saves/save");
        //URL url;

        try {
            //url = fileCheck.toURI().toURL();
            fileCheck.toURI().toURL();
        } catch (MalformedURLException e2) {
            //url = null;
            System.out.println("failed");
        }


        try {
            Reader reader = new FileReader(this.getClass().getClassLoader().getResource("").getPath()+ "personal/saves/save");
            PlayerData[] data = gson.fromJson(reader, PlayerData[].class);
            
            for (int i = 0; i < 4; i++) {
                
                saves[i] = data[i];

            }

        } catch (FileNotFoundException e) {
            for (int i = 0; i < 4; i++) {
                PlayerData data = new PlayerData();
                saves[i] = data;
            }

            try {

                String routePath = this.getClass().getClassLoader().getResource("").getPath()+ "personal/saves/save";
                System.out.println(routePath.substring(1));
                File file = new File(routePath.substring(1));
                file.createNewFile();

                //objectOut = new ObjectOutputStream(fileOut);
                //objectOut.writeObject(saves);
                FileWriter writer = new FileWriter(file);
                gson.toJson(saves, writer);
                //for (int i = 0; i < 4; i++) {
                //    gson.toJson(saves.get(i), writer);
                //}
                writer.flush();
                writer.close();
                //objectOut.close();

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            //e.printStackTrace();
        }


        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 6;
        c.gridheight = 4;
        c.gridx = 0;
        c.gridy = 0;        
        c.weightx = 0.6;
        c.weighty = 0.6;
        c.fill = GridBagConstraints.BOTH;
        currentPanel = makePanel(saves[currentData]);
        this.add(currentPanel, c);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridwidth = 1;
        c2.gridheight = 1;
        c2.gridx = 0;
        c2.gridy = 4;        
        c2.weightx = 0.2;
        c2.weighty = 0.2;
        c2.fill = GridBagConstraints.BOTH;
        for (int i = 0; i < saves.length; i++) {
            c2.gridx = i;
            this.add(makeButton(saves[i], i), c2);
        }
        c2.gridx = saves.length + 1;
        this.add(makeReturnButton(), c2);
        //for the top 2 / 3 of the screen, we need a panel to show what
        //this save file is all about

        //roguelike.... as player plays add on events

        

        //What events the player have achieved
        //Player level
        //Player stats
        //Player exp bar
        //Player karma?
        //Player HP



        //for the bottom half, we need four buttons supporting four files.
        //these should show level of character


        this.setVisible(true);
        //this.add(startButton);
        //this.add(loadButton);
        //this.add(exitButton);
        this.setBounds(0, 0, 1024,900);

        GameEngine.run = true;
        GameEngine.mainThread = new Thread(() -> {
            
            while (GameEngine.run) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                update();
            }
        });

    }
    //gets called every 20 secs
    public static void update() {

    }
    
    public static JPanel makePanel(PlayerData data) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 0;        
        c.weightx = 0.6;
        c.weighty = 0.6;
        c.fill = GridBagConstraints.HORIZONTAL;
        MouseListener ml = new MouseAdapter()
        {
        @Override
        public void mousePressed(MouseEvent e)
        {
            //JPanel panel = (JPanel)e.getSource();
            askNewGame();
            // do your processing on the panel
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            panel.setBorder(new LineBorder(Color.RED, 10));
        }
        @Override
        public void mouseExited(MouseEvent e) {
            panel.setBorder(new LineBorder(Color.BLUE, 10));
        }
        };
        panel.addMouseListener(ml);
        panel.setFocusable(false);
        if (data.isNew) {
            //System.out.print("haha");
            //TODO: remove currentData
            JLabel label = new JLabel("<html>File " + (currentData + 1) + "<br />New File</html>", SwingConstants.CENTER);
            label.setFont(new Font("Verdana",1,30));
            label.setBorder(new LineBorder(Color.BLUE, 10));
            //c.anchor = GridBagConstraints.CENTER;
            panel.add(label, c);
            label.setVisible(true);
            panel.setBorder(new LineBorder(Color.BLUE, 10));
            //panel.setSize(GameEngine.frameWidth, GameEngine.frameHeight * 2 / 3);
            panel.setVisible(true);
        } else {
            System.out.println("Need to implement old file");
        }

        return panel;
    }

    public static JButton makeButton(PlayerData data, int index) {
        JButton button;
        if (data.isNew) {
            button = new JButton("<html>Save " + (index + 1) + "<br />New Game</html>");
        } else {
            button = new JButton("<html>Save " + (index + 1) + "<br />lvl: " + data.level + "</html>");
        }
        button.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentData = index;
                screen.refreshPanel();
            }
        });;
        button.setVisible(true);
        button.setFocusable(false);
        button.setBorder(new LineBorder(Color.GRAY, 10));
        return button;
    }

    public static boolean askNewGame() {
        int x = JOptionPane.showOptionDialog(null, "Start New game on this file?",
                "Start?",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
    if (x == JOptionPane.YES_OPTION) {
        // start game
        //TODO: Implement start
        GameEngine.currentData = saves[currentData];
        GameEngine.engine.mainSequenceInit(
            saves[currentData]
            );
    }
        return false;
    }

    public static boolean askLoadGame(boolean isNew) {
        int x = JOptionPane.NO_OPTION;
        if (isNew) {
            JOptionPane.showMessageDialog(null, "Cannot load new file",
            "Error",
            JOptionPane.WARNING_MESSAGE, null);
        } else {
            x = JOptionPane.showOptionDialog(null, "Load this file?",
                "Load",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
        }
        if (x == JOptionPane.YES_OPTION) {
            //TODO: Implement Load Game
            
        }

        return false;
    }

    public static JButton makeReturnButton() {
        JButton button = new JButton("To Title Screen");
        button.setVisible(true);
        button.setFocusable(false);
        button.setBorder(new LineBorder(Color.GRAY, 10));
        button.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameEngine.engine.titleSequenceInit();
            }
        });;
        return button;
    }

    public void refreshPanel() {
        this.remove(currentPanel);
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 6;
        c.gridheight = 4;
        c.gridx = 0;
        c.gridy = 0;        
        c.weightx = 0.6;
        c.weighty = 0.6;
        c.fill = GridBagConstraints.BOTH;
        currentPanel = makePanel(saves[currentData]);
        this.add(currentPanel, c);
        revalidate();
        repaint();
    }
/* 
    public static void main(String[] args) {
        LoadScreen load =new LoadScreen();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create(); 


        for (int i = 0; i < load.saves.length; i++) {

            System.out.println(gson.toJson(load.saves[i]));
        }
    }
*/    
}
