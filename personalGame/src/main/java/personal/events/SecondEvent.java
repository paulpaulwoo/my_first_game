package personal.events;

import personal.GameEngine;
import personal.UI.MainScreen;
// GameEngine.currentData

public class SecondEvent extends Event {

    static String[] choiceStrings = new String[]{"Personal gain", "The common good"};
    
    //static final String baseString = Event.parseEvent("The Beginning", 100, "You remeber this and this and that... blah blah blah....... random ass characters.........");

    public SecondEvent() {
        super("Error", "<html><h1>This is my Second Event...</h1></html>", "Second event test", 2, choiceStrings, 1);
    }


    @Override
    public void choiceAction(int choice) {
        if (choice == 0) {
            MainScreen.nextEventId = 3;
            MainScreen.pData.karma += 5;
            MainScreen.screen.refreshStatPanel();
        } else if (choice == 1) {
            GameEngine.engine.combatSequenceInit(null);
            MainScreen.nextEventId = 3;
            MainScreen.pData.karma -=5;
            //MainScreen.screen.refreshStatPanel();
            
        }
    }  

    

    @Override
    public String getChoiceStringDialogue(int choice) {
        if (choice == 0) {
            return "<html><h1>It was mostly for personal gain. I am ambitious, <br>and I do anything to put myself in an advantagous situation.</h1></html>";
        } else if (choice == 1) {
            return "<html><h1>It was mostly for the common good. I want to make the world a better place, <br>and I do antything to bring good to myself and others. COMBAT TEST</h1></html>";
        }
        return "Not ready yet!";
    }
}
