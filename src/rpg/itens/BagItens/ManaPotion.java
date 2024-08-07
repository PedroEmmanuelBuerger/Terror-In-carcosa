package rpg.itens.BagItens;

import rpg.Character.Classes.Attributes;
import rpg.Character.Classes.Healer;
import rpg.Character.Classes.Mage;
import rpg.Character.Classes.Necromancer;
import rpg.Utils.SlowConsole;
import rpg.itens.Item;

public class ManaPotion extends Item {
    private final SlowConsole slowConsole = new SlowConsole();
    private final int manaAmount;

    public ManaPotion(String name, int price, int manaAmount) {
        super(name, price);
        this.manaAmount = manaAmount;
    }

    public int getManaAmount() {
        return manaAmount;
    }

    @Override
    public void UseItem(Attributes player) {
        int manaAmount = this.getManaAmount();
        int newMana;
        int maxMana;
        int currentMana;

        if (player instanceof Necromancer necromancer) {
            currentMana = necromancer.getMana();
            maxMana = necromancer.getMaxMana();
            newMana = currentMana + manaAmount;
            if (newMana > maxMana) {
                newMana = maxMana;
            }
            necromancer.setMana(newMana);
            slowConsole.imprimirDevagar("Após consumir a poção negra, sentes a mana fluir novamente em suas veias corrompidas. Nova mana: " + newMana);

        } else if (player instanceof Mage mage) {
            currentMana = mage.getMana();
            maxMana = mage.getMaxMana();
            newMana = currentMana + manaAmount;
            if (newMana > maxMana) {
                newMana = maxMana;
            }
            mage.setMana(newMana);
            slowConsole.imprimirDevagar("Ao beber a poção, visões de Carcosa invadem sua mente. Nova mana: " + newMana);

        } else if (player instanceof Healer healer) {
            currentMana = healer.getMana();
            maxMana = healer.getMaxMana();
            newMana = currentMana + manaAmount;
            if (newMana > maxMana) {
                newMana = maxMana;
            }
            healer.setMana(newMana);
            slowConsole.imprimirDevagar("A poção sagrada renova suas forças espirituais. Nova mana: " + newMana);

        }
    }
}
