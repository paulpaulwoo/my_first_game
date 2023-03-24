package personal.events;

import java.util.HashMap;
import java.util.LinkedList;

import personal.GameEngine;

// GameEngine.currentData
public class Event {
    public static int totalEvents = 1;
    public static HashMap<Integer, Event> events = new HashMap<>();
    public static LinkedList<Integer> eventQueue;
    public static int nextEventId;
    public int id;
    public String baseString;
    public int numOfChoices;
    public String[] choiceStrings;
    public String baseChoiceString;
    public String eventName;

    
    public static void Init() {
        events.put(1, new FirstEvent());
        events.put(2, new SecondEvent());
    }

    public Event(String baseString, String baseChoiceString, String eventName, int numOfChoices, String[] choiceStrings, int id) {
        this.baseString = baseString;
        this.baseChoiceString = baseChoiceString;
        this.eventName = eventName;
        this.numOfChoices = numOfChoices;
        this.choiceStrings = choiceStrings;
        this.id = id;
    
    }

    public String getChoiceStringDialogue(int choice) {
        //for Override
        return null;
    }   

    public void choiceAction(int choice) {
        //for Override
    }

    public static Event getEvent(int id) {
        System.out.println("EventId : " + id);
        return events.get(id);
    } 

    public String getBaseString() {
        return this.parseEvent(100);
    }

    public void changeString(String updateString) {
        this.baseString = updateString;
    }

    //for override
    public void eventEncounterEvoke() {

    }

    public String parseEvent(int fontSize) {
        StringBuilder returnString = new StringBuilder("<html><head><style>h1 {color: black;font-family:'Courier New';font-size: 400%;padding-bottom:300%;}body {color: black;font-family:'Courier New';font-size: ");
        returnString.append(fontSize);
        returnString.append("%;}</style></head><body><h1><br>");
        returnString.append(eventName);
        returnString.append("<br><br></h1><h2>");
        
        String[] splitString = baseString.split(" ");
        int currentLength = 0;
        for (int i = 0; i < splitString.length; i++) {
            if (currentLength + splitString[i].length() > 55) {
                returnString.append("<br>");
                currentLength = 0;
            }
            returnString.append(splitString[i]);
            returnString.append(" ");
            currentLength = currentLength + splitString[i].length() + 1;
        }
        returnString.append("</h2></body></html>");
        return returnString.toString();
    }


    //TEST
    
}
    
