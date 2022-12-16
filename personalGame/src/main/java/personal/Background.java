package personal;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Background extends JPanel {
    private BufferedImage img;
    public Background() {
        try {
            img = ImageIO.read(getClass().getResource("Crystal Ball.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setBounds(0, 0,1500, 1400);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img,0, 0, this);
    }
    
}
