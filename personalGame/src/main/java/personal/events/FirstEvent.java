package personal.events;

import personal.UI.MainScreen;
// GameEngine.currentData

public class FirstEvent extends Event {

    static String[] choiceStrings = new String[]{"Strong with fights", "Smart with studies", "Clever with words"};
    
    //super.event = "The Beginning";
    //super.baseString = Event.parseEvent( 100, "You awaken in a sterile white room, disoriented and without any memory of how you arrived. Nothing is visible in the stark surroundings, and you struggle to recall even basic details about yourself. The silence is deafening as you try to make sense of your situation. Your mind is a haze, but you begin to piece together fragments of your past. You remember a childhood, a family, a life before this empty room. When you were young, you were....");


    public FirstEvent() {
        super("You awaken in a sterile white room, disoriented and without any memory of how you arrived. Nothing is visible in the stark surroundings, and you struggle to recall even basic details about yourself. The silence is deafening as you try to make sense of your situation. Your mind is a haze, but you begin to piece together fragments of your past. You remember a childhood, a family, a life before this empty room. When you were young, you were....", "<html><h1>What did I do... I'm struggling to remember...</h1></html>", "The Beginning", 3, choiceStrings, 1);
    }
    @Override
    public void choiceAction(int choice) {
        if (choice == 0) {
            MainScreen.nextEventId = 2;
            MainScreen.pData.str += 4;
            MainScreen.pData.con += 2;
            
            getEvent(2).changeString("As you try to remember more, you realize that you had a reputation for being tough and never backing down from a challenge. You were often involved in fights. But, why? What was the reason behind all those fights and training?");
            MainScreen.screen.refreshStatPanel();
        } else if (choice == 1) {
            MainScreen.nextEventId = 2;
            MainScreen.pData.intel += 4;
            MainScreen.pData.wis += 2;            
            getEvent(2).changeString("As you continue to piece together your memories, you remember that you were always passionate about learning and exploring new ideas, excelling in your studies. But, why? What made you passionate about your studies?");
            MainScreen.screen.refreshStatPanel();
        } else if (choice == 2) {
            MainScreen.nextEventId = 2;
            MainScreen.pData.cha += 4;
            MainScreen.pData.wis += 2;            
            getEvent(2).changeString("As you continue to reflect on your past, memories flood back to you. You were always fascinated by language and the power of words, and was able to use them to achieve goals. But why? You remember that the reason you practiced the art of speaking was for...");
            MainScreen.screen.refreshStatPanel();
        }
        MainScreen.screen.nextEvent();
    }   

    @Override
    public String getBaseString() {
        return this.parseEvent(100);
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

    @Override
    public void eventEncounterEvoke() {

    }
}
