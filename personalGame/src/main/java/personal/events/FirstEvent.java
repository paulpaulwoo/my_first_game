package personal.events;

import personal.player.PlayerData;
import personal.GameEngine;

// GameEngine.currentData

public class FirstEvent extends Event {
    static String[] choiceStrings = new String[] {"First Option", "Second Option"};
    //static final String baseString = "<html><head><style>body {background-color: linen;}h1 {color: maroon;margin-left: 40px;}</style></head><body><h1>This is a heading</h1><p>This is a paragraph.</p></body></html>"; 
    static final String baseString = String.join("",
    "<html>",
    "<head>",
    "<style>",
    "h1 {",
       "color: black;",
    "}",
     "</style>",
     "</head>",
     "<body>",
     
     "<h1>This is a heading</h1>",
     "<h2>This is a paragraph. I was walking down a road... blah blah blah...",
     "<br> And also blah blah blahh....... I am writing anything...... chatgpt",
     "<br> Help me...... Why am I typing this all<br> on my own..... now I will use copy paste",
     "<br> Help me...... Why am I typing this all<br> on my own..... now I will use copy paste",
     "<br> Help me...... Why am I typing this all<br> on my own..... now I will use copy paste",
     //"<br> Help me...... Why am I typing this all<br> on my own..... now I will use copy paste",
     //"<br> Help me...... Why am I typing this all<br> on my own..... now I will use copy paste",
     
     
    "</h2>",
     
     "</body>",
     "</html>"
    );
    public FirstEvent() {
        //super("This is my first Event...", "Something Happened....", 2, choiceStrings, 1);
        super("This is my first Event...", baseString, 2, choiceStrings, 1);
    }
    @Override
    public void choiceAction(int choice) {
        if (choice == 0) {
            //nextEventId = 1;//
            /*for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
                System.out.println(ste + "\n");
            } */
            nextEventId = 2;
            GameEngine.engine.combatSequenceInit(null, null);
        } else if (choice == 1) {
            System.out.println("need more Events......");
        }
    }

    @Override
    public String getChoiceStringDialogue(int choice) {
        if (choice == 0) {
            return "Let's pick this....";
        } else if (choice == 1) {
            return "Not ready yet!";
        }
        return "Not ready yet!";
    }
}
