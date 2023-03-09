package personal.UI;



import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import personal.events.Event;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PauseButton extends JPanel {
    static Color defaultBorderColor = new Color(200, 200, 200);
    static Color pressedBorderColor = new Color(120, 120, 120);
    static Color hoverBorderColor = new Color(180, 180, 180);
    static Color defaultBackgroundColor = new Color(250, 250, 250);
    static Color pressedBackgroundColor = new Color(170, 170, 170);
    static Color hoverBackgroundColor = new Color(230, 230, 230);
    Color borderColor = defaultBorderColor;

    boolean isInside = false;
    boolean isClicked = false;

    //LineBorder defaultBorder = new LineBorder(defaultBorderColor, 10);
    //LineBorder pressedBorder = new LineBorder(pressedBorderColor, 10);
    //LineBorder hoverBorder = new LineBorder(hoverBorderColor, 10);

    //function for overRide
    public void toExecute() {
    }


    public PauseButton(String choiceString) {
        setBorder(new LineBorder(defaultBorderColor, 10));
        setBackground(defaultBackgroundColor);
        setForeground(Color.BLACK);
        //setText(event.choiceStrings[choiceId]);
        this.add(new JLabel("<html><body>" + choiceString + "</body></html>" ));
        this.addMouseListener( new MouseListener() {
            @Override
    public void mouseClicked(MouseEvent e) {
        toExecute();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // click animation
        isClicked = true; 
        setBorder(new LineBorder(pressedBorderColor, 10));
        setBackground(pressedBackgroundColor);
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isClicked = false;
        if (!isInside) {
            setBorder(new LineBorder(hoverBorderColor, 10));
            setBackground(hoverBackgroundColor);
            repaint();
        } else {
            setBorder(new LineBorder(defaultBorderColor, 10));
            setBackground(defaultBackgroundColor);
            repaint();
        }
        // exit clicking animation

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        isInside = true;
        if (!isClicked) {
            setBorder(new LineBorder(hoverBorderColor, 10));
            setBackground(hoverBackgroundColor);
            repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isInside = false;
        // remove mouse entered effects
        if (!isClicked) {
            setBorder(new LineBorder(defaultBorderColor, 10));
            setBackground(defaultBackgroundColor);
            repaint();
        }
        // exit clicking mode
    }
        });
        //System.out.println(getText());
        setFocusable(false);
        setOpaque(true);
        setVisible(true);
    }




}
