package personal;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.*;

public class Background extends JPanel {
    private Image img;
    //private BufferedImage bmg;
    public Background() {
        this.setLayout(new GridBagLayout());
        this.setBounds(0, 0,1200, 1000);
        try {
            img = ImageIO.read(getClass().getResource("board2.png")).getScaledInstance(GameEngine.engine.frame.getWidth(), GameEngine.engine.frame.getHeight() - 30, Image.SCALE_DEFAULT);
            //bmg = ImageIO.read(getClass().getResource("board2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img,0, 0, this);

    }
    
}
