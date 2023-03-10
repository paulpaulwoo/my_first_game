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

    public boolean ingame;
    
    public int nextEventId;

    public PlayerData() {
        isNew = true;
        items = new ArrayList<>();
        nextEventId = 1;
        maxHP = 1;
        HP = 1;
        str = 7;
        intel = 7;
        con = 7;
        wis = 7;
        cha = 7;
        gold = 0;
        karma = 0;
    }
    
}