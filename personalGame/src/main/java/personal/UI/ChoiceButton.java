package personal.UI;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Component;
import java.awt.Graphics;
import personal.events.Event;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChoiceButton extends JPanel {
    Event event;
    int choiceId;
    MainScreen mainScreen;
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

    public ChoiceButton(MainScreen mainScreen, Event event, int choiceId) {
        //super("abc");
        this.mainScreen = mainScreen;
        setBorder(new LineBorder(defaultBorderColor, 10));
        setBackground(defaultBackgroundColor);
        setForeground(Color.BLACK);
        //setText(event.choiceStrings[choiceId]);
        this.add(new JLabel(event.choiceStrings[choiceId]));
        this.addMouseListener( new MouseListener() {
            @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        mainScreen.choiceMade(choiceId);
        System.out.println("Clicked");
        // execute function
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        // click animation
        isClicked = true; 
        setBorder(new LineBorder(pressedBorderColor, 10));
        setBackground(pressedBackgroundColor);
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isClicked = false;
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        isInside = true;
        if (!isClicked) {
            setBorder(new LineBorder(hoverBorderColor, 10));
            setBackground(hoverBackgroundColor);
            repaint();
        }
        mainScreen.updateTextBox(event.getChoiceStringDialogue(choiceId));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        isInside = false;
        // remove mouse entered effects
        if (!isClicked) {
            setBorder(new LineBorder(defaultBorderColor, 10));
            setBackground(defaultBackgroundColor);
            repaint();
        }
        // exit clicking mode
        mainScreen.updateTextBox(event.baseString);
    }
        });
        //System.out.println(getText());
        setFocusable(false);
        setOpaque(true);
        setVisible(true);
    }




}
