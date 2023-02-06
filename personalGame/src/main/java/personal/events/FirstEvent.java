package personal.events;

import personal.player.PlayerData;

public class FirstEvent extends Event {
    static String[] choiceStrings = new String[] {"First Option", "Second Option"};
    public FirstEvent() {
        super("This is my first Event...", 2, choiceStrings, 1);

    }
    @Override
    public void choiceAction(int choice, PlayerData pData) {
        if (choice == 1) {
            nextEventId = 1;
        } else if (choice == 2) {
            System.out.println("need more Events......");
        }
    }

    @Override
    public String getChoiceStringDialogue(int choice, PlayerData pData) {
        if (choice == 1) {
            return "Let's pick this....";
        } else if (choice == 2) {
            return "Not ready yet!";
        }
        return "Not ready yet!";
    }
}
