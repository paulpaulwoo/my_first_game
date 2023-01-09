package personal.UI;

import javax.swing.JPanel;
import personal.GameEngine;
import personal.player.PlayerData;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

public class LoadScreen extends JPanel {
    BufferedImage img;
    BufferedImage emptyImage;
    Graphics2D grph;
    ArrayList<PlayerData> saves;
    int currentData = 0;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create(); 
    public LoadScreen() {
        //JFrame frame = GameEngine.frameClear();
        saves = new ArrayList<>();
        URL url = getClass().getResource("../saves/save");
        
        System.out.println(url);
        if (url == null) {
            for (int i = 0; i < 4; i++) {
                PlayerData data = new PlayerData();
                saves.add(data);
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
                writer.flush();
                writer.close();
                //objectOut.close();

            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        
        

        FileInputStream fileIn;
        ObjectInputStream objectIn;
        try {
            //saves = (ArrayList<PlayerData>) objectIn.readObject();
            
            saves = gson.fromJson(new FileReader(this.getClass().getClassLoader().getResource("").getPath()+ "personal/saves/save"), saves.getClass());
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        //this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.gridheight = 4;
        c.weightx = 1.0;
        c.weighty = 0.5;
        //frame.add(makePanel(saves.get(currentData)));
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

        GameEngine.engine.run = true;
        GameEngine.engine.mainThread = new Thread(() -> {
            
            while (GameEngine.engine.run) {
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
        
        if (data.isNew) {
            JLabel label = new JLabel("New File");
            label.setFont(new Font("Verdana",1,30));
            
            panel.add(label);
            panel.setBorder(new LineBorder(Color.BLUE, 20));
            panel.setSize(GameEngine.frameWidth, GameEngine.frameHeight * 2 / 3);

        }

        return panel;
    }

    public static void main(String[] args) {
        LoadScreen load =new LoadScreen();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create(); 

        for (int i = 0; i < load.saves.size(); i++) {

            System.out.println(gson.toJson(load.saves.get(i)));
        }
    }
}
