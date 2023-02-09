package personal.events;

import personal.player.PlayerData;
import personal.GameEngine;

// GameEngine.currentData

public class FirstEvent extends Event {
    static String[] choiceStrings = new String[] {"First Option", "Second Option"};
    public FirstEvent() {
        super("This is my first Event...", "Something Happened....", 2, choiceStrings, 1);

    }
    @Override
    public void choiceAction(int choice) {
        if (choice == 0) {
            nextEventId = 1;
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
