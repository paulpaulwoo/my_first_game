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
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.border.Border;
import java.io.Serializable;

public class LoadScreen extends JPanel {
    BufferedImage img;
    BufferedImage emptyImage;
    Graphics2D grph;
    ArrayList<PlayerData> saves;
    public LoadScreen() {
        //JFrame frame = GameEngine.engine.frame;
        //frame.getLayeredPane().invalidate();
        //frame.getLayeredPane().removeAll();
        //frame.getLayeredPane().validate();
        //frame.getLayeredPane().repaint();
        saves = new ArrayList<>();
        URL url = getClass().getResource("../saves/save");
        
        System.out.println(url);
        if (url == null) {
            for (int i = 0; i < 4; i++) {
                PlayerData data = new PlayerData();
                saves.add(data);
            }
            FileOutputStream fileOut;
            ObjectOutputStream objectOut; 
            try {

                String routePath = this.getClass().getClassLoader().getResource("").getPath()+ "personal/saves/save";
                System.out.println(routePath.substring(1));
                File file = new File(routePath.substring(1));
                file.createNewFile();
                fileOut = new FileOutputStream(file);
                objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(saves);
                objectOut.close();
                fileOut.close();
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
            fileIn = new FileInputStream(this.getClass().getClassLoader().getResource("").getPath()+ "personal/saves/save");
            objectIn = new ObjectInputStream(fileIn);
            saves = (ArrayList<PlayerData>) objectIn.readObject();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("File Corruption");
            e.printStackTrace();
        }


        //this.setLayout(new GridBagLayout());
        //GridBagConstraints c = new GridBagConstraints();

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


        //this.setVisible(true);
        //this.add(startButton);
        //this.add(loadButton);
        //this.add(exitButton);
        //this.setBounds(0, 0, 1024,900);

    }
    
    public static void main(String[] args) {
        LoadScreen load =new LoadScreen();
        for (int i = 0; i < load.saves.size(); i++) {
            System.out.println(load.saves.get(i).isNew);
        }
    }
}
