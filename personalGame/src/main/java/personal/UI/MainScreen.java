package personal.UI;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import personal.GameEngine;
import personal.player.PlayerData;
import personal.events.Event;
import java.awt.Color;
import java.awt.Dimension;

public class MainScreen extends JPanel {

    public JPanel textPanel;
    public JLabel textLabel;
    public JPanel eventPanel;
    public JPanel eventTextPanel;
    public JLabel eventTextLabel;
    public JPanel eventChoicePanel;
    public JPanel statpanel;
    public JPanel hpPanel;
    public JLabel str;
    public JLabel intel;
    public JLabel con;
    public JLabel wis;
    public JLabel cha;


    Event currentEvent;
    public static PlayerData pData;
    private static final String htmlEnding = "</h1><html>";
    private static final String htmlHeading = "<html><h1>";
    public static MainScreen screen;
    public MainScreen(PlayerData data) {
        pData = data;
        GameEngine.frameClear();
        this.setBounds(0, 0, 1024,900);
        currentEvent = Event.getEvent(pData.nextEventId);


        statpanel = makeStatPanel(data);

        

        eventPanel = makeEventPanel(currentEvent);


        textPanel = makeTextPanel(currentEvent.baseString);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Dimension d0 = new Dimension(Integer.MAX_VALUE, (int) (this.getHeight() * 0.05));
        Dimension d1 = new Dimension(Integer.MAX_VALUE, (int) (this.getHeight() * 0.65));
        Dimension d2 = new Dimension(Integer.MAX_VALUE, (int) (this.getHeight() * 0.30));

        statpanel.setPreferredSize(d0);
        statpanel.setMaximumSize(d0);
        statpanel.setMinimumSize(d0);

        eventPanel.setPreferredSize(d1);
        eventPanel.setMaximumSize(d1);
        eventPanel.setMinimumSize(d1);

        textPanel.setPreferredSize(d2);
        textPanel.setMaximumSize(d2);
        textPanel.setMinimumSize(d2);

        this.add(statpanel);
        this.add(Box.createVerticalGlue());
        this.add(eventPanel);
        this.add(Box.createVerticalGlue());
        this.add(textPanel);
        this.add(Box.createVerticalGlue());

        screen = this;
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    public void refreshStatPanel() {

        str.setText(String.join("", htmlHeading, "Str: ", Integer.toString(pData.str), htmlEnding));
        intel.setText(String.join("", htmlHeading, "Int: ", Integer.toString(pData.intel), htmlEnding));
        con.setText(String.join("", htmlHeading, "Con: ", Integer.toString(pData.con), htmlEnding));
        wis.setText(String.join("", htmlHeading, "Wis: ", Integer.toString(pData.wis), htmlEnding));
        cha.setText(String.join("", htmlHeading, "Cha: ", Integer.toString(pData.cha), htmlEnding));

        statpanel.repaint();
        this.revalidate();
    }



    public JPanel makeStatPanel(PlayerData pdata) {
        statpanel = new JPanel();
        statpanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.weightx = 0.05;
        JLabel hpLabel = new JLabel("<html><style>h1 {text-align: center;}</style><h1>HP: </h1></html>");
        hpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hpLabel.setVerticalAlignment(SwingConstants.CENTER);

        statpanel.add(hpLabel, c);
        

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.48;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 0;
        statpanel.add(new MainHpBar(pdata), c);
        
        c.gridx = 2;
        c.weightx = 0.1;
        str = new JLabel(String.join("", htmlHeading, "Str: ", Integer.toString(pdata.str), htmlEnding));
        str.setBorder(new LineBorder(Color.BLUE, 2));
        str.setHorizontalAlignment(SwingConstants.CENTER);
        
        intel = new JLabel(String.join("", htmlHeading, "Int: ", Integer.toString(pdata.intel), htmlEnding));
        intel.setBorder(new LineBorder(Color.BLUE, 2));
        intel.setHorizontalAlignment(SwingConstants.CENTER);
        
        con = new JLabel(String.join("", htmlHeading, "Con: ", Integer.toString(pdata.con), htmlEnding));
        con.setBorder(new LineBorder(Color.BLUE, 2));
        con.setHorizontalAlignment(SwingConstants.CENTER);
        
        wis = new JLabel(String.join("", htmlHeading, "Wis: ", Integer.toString(pdata.wis), htmlEnding));
        wis.setBorder(new LineBorder(Color.BLUE, 2));
        wis.setHorizontalAlignment(SwingConstants.CENTER);
        
        cha = new JLabel(String.join("", htmlHeading, "Cha: ", Integer.toString(pdata.cha), htmlEnding));
        cha.setBorder(new LineBorder(Color.BLUE, 2));
        cha.setHorizontalAlignment(SwingConstants.CENTER);
        statpanel.add(str,c);
        c.gridx++;
        statpanel.add(intel,c);
        c.gridx++;
        statpanel.add(con,c);
        c.gridx++;
        statpanel.add(wis,c);
        c.gridx++;
        statpanel.add(cha,c);
        c.gridx++;

        statpanel.setVisible(true);
        statpanel.setFocusable(false);
        return statpanel;
    }


    public JPanel makeTextPanel(String text) {
        textPanel = new JPanel();
        //panel.setLayout(new GridBagLayout());
        textPanel.setBackground(Color.BLACK);
        textPanel.setBorder(new LineBorder(Color.WHITE, 10));
        
        textLabel = new JLabel(text);
        //textLabel.setFont(new Font("Verdana",1,30));
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
        eventTextLabel = new JLabel(event.baseChoiceString);
        eventTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        eventTextLabel.setVerticalAlignment(SwingConstants.NORTH);
        GridBagConstraints c3 = new GridBagConstraints();
        c3.fill = GridBagConstraints.BOTH;
        c3.anchor = GridBagConstraints.FIRST_LINE_START;
        c3.weightx = 1;
        c3.weighty = 1;
        eventTextPanel.add(eventTextLabel, c3);
        eventTextPanel.setBackground(Color.white);
        c.fill = GridBagConstraints.BOTH;
        //c.ipadx = 50;
        //c.ipady = 25;
        c.gridwidth = 3;
        c.gridheight = 3;
        c.weightx = 0.7;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        eventPanel.add(eventTextPanel, c);

        c.gridx = 3;
        c.gridy = 0;
        //c.ipadx = 50;
        //c.ipady = 25;
        c.gridwidth = 1;
        c.gridheight = 3;
        c.weightx = 0.3;
        c.weighty = 1;
        
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


    
}
 