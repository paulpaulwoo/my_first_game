package personal.events;

import personal.UI.MainScreen;
// GameEngine.currentData

public class FirstEvent extends Event {

    static String[] choiceStrings = new String[]{"Strong with fights", "Smart with studies", "Clever with words"};
    
    static final String baseString = Event.parseEvent("The Beginning", 100, "You awaken in a sterile white room, disoriented and without any memory of how you arrived. Nothing is visible in the stark surroundings, and you struggle to recall even basic details about yourself. The silence is deafening as you try to make sense of your situation. Your mind is a haze, but you begin to piece together fragments of your past. You remember a childhood, a family, a life before this empty room. When you were young, you were....");

    public FirstEvent() {
        super("<html><h1>This is my first Event...</h1></html>", baseString, 3, choiceStrings, 1);
    }
    @Override
    public void choiceAction(int choice) {
        if (choice == 0) {
            nextEventId = 2;
            MainScreen.pData.str += 4;
            MainScreen.screen.refreshStatPanel();
        } else if (choice == 1) {
            nextEventId = 2;
            MainScreen.pData.intel += 4;
            MainScreen.screen.refreshStatPanel();
        } else if (choice == 2) {
            nextEventId = 2;
            MainScreen.pData.cha += 4;
            MainScreen.screen.refreshStatPanel();
        }
    }   

    @Override
    public String getChoiceStringDialogue(int choice) {
        if (choice == 0) {
            return "<html><h1>I was very strong. Sure, I lost some fights....<br>but I did make them pay.</h1></html>";
        } else if (choice == 1) {
            return "<html><h1>I excelled in my studies. I understood hard concepts in a breeze.</h1></html>";
        } else if (choice == 2) {
            return "<html><h1>I was pretty clever. Whenever I was in a pinch, I was able<br> to get myself out of it with my two inch tounge.</h1></html>";
        }
        return "Not ready yet!";
    }
}
