package personal.player;

import java.io.Serializable;
import java.util.ArrayList;

import personal.items.Item;

public class PlayerData implements Serializable{
    //items : arraylist, non equipped items
    public boolean isNew;
    public ArrayList<Item> items;

    //gear
    public Item head;
    public Item torso;
    public Item legs;
    public Item token;
    public Item weapon;
    public Item consumable;

    //stats
    public int maxHP;
    public int HP;
    public int str; // swing damage, strength check 
    public int intel; // magic
    public int con; // hp & armor
    public int wis; // helps player make right decision, item verify
    public int cha; // persuasion check
 
    
    public int level;

    public int exp;

    public int maxexp;

    public int gold;

    public int karma;

    public boolean[] achievements;

    public PlayerData() {
        isNew = true;
        items = new ArrayList<>();

    }
    
}