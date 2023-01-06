package personal.items;

import java.io.Serializable;

import personal.enemy.Amg;
import personal.player.Player;
import personal.player.PlayerData;

public class Item implements Serializable {
    
    public int id;
    public String imageName;
    public String Name;
    public String description;
    public String vaugeDescription;
    
    public int quality;
    
    public boolean isRevealed;
    public boolean isEquipment;
    public boolean isConsumable;
    public boolean isStackable;

    public int stackLimit;
    public int equipmentType;

    public Item (int id, String imageName, String description, String vagueDescription, int quality, 
    boolean isRevealed, boolean isEquipment, boolean isConsumable, boolean isStackable,
    int stackLimit, int equipmentType) {

        this.id = id;
        this.imageName = imageName;
        this.description = description;
        this.vaugeDescription = vagueDescription;
        this.quality = quality;
        this.isRevealed = isRevealed;
        this.isConsumable = isConsumable;
        this.isStackable = isStackable;
        this.stackLimit = stackLimit;
        this.equipmentType = equipmentType;
    }

    public void equip(PlayerData player) {
        System.out.println("WARNING: EQUIP NOT OVERRIDDEN");
        System.out.println("Item id " + id + " equipped!");
    }

    public void unEquip(PlayerData player) {
        System.out.println("WARNING: UNEQUIP NOT OVERRIDDEN");
        System.out.println("Item id " + id + " unequipped!");
    }

    public void onHit(Player source, Amg enemy) {
        System.out.println("WARNING: ONHIT NOT OVERRIDDEN");
        System.out.println("Item id " + id + " onhit evoked!");
    }

    public void getHit(Player source, Amg enemy) {
        System.out.println("WARNING: GETHIT NOT OVERRIDDEN");
        System.out.println("Item id " + id + " gethit evoked!");
    }

    public void death() {
        System.out.println("WARNING: DEATH NOT OVERRIDDEN");
        System.out.println("Item id " + id + " death evoked!");
    }
}
