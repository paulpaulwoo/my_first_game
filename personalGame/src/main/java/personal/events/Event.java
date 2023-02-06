package personal.events;

import java.util.HashMap;
import java.util.LinkedList;

import personal.player.PlayerData;

public class Event {
    public static int totalEvents = 1;
    public static HashMap<Integer, Event> events = new HashMap<>();
    public static LinkedList<Integer> eventQueue;
    public static int nextEventId;
    public int id;
    public String baseString;
    public int numOfChoices;
    public String[] choiceStrings;
    
    public static void Init() {
        Event event = new FirstEvent();
        events.put(1, event);
    }

    public Event(String baseString, int numOfChoices, String[] choiceStrings, int id) {
        this.baseString = baseString;
        this.numOfChoices = numOfChoices;
        this.choiceStrings = choiceStrings;
        this.id = id;
    }

    public String getChoiceStringDialogue(int choice, PlayerData pData) {
        //for Override
        return null;
    }   

    public void choiceAction(int choice, PlayerData pData) {
        //for Override
    }
    public static Event getEvent(int id) {
        return events.get(id);
    } 

    //TEST
    
}
    
