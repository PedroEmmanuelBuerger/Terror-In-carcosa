// Arquivo: rpg/itens/BagItens/Potion.java
package rpg.itens.BagItens;

import rpg.Classes.Attributes;
import rpg.itens.Item;

public class Potion extends Item {
    private int healingAmount;

    public Potion(String name, int healingAmount) {
        super(name);
        this.healingAmount = healingAmount;
    }

    public int getHealingAmount() {
        return healingAmount;
    }
}
