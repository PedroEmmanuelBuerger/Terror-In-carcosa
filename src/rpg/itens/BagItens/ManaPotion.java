package rpg.itens.BagItens;

import rpg.Character.Classes.Attributes;
import rpg.Character.Classes.Healer;
import rpg.Character.Classes.Mage;
import rpg.Character.Classes.Necromancer;
import rpg.Utils.SlowConsole;
import rpg.itens.Item;

public class ManaPotion extends Item {
    private SlowConsole slowConsole = new SlowConsole();
    private int manaAmount;

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

        if (player instanceof Necromancer) {
            Necromancer necromancer = (Necromancer) player;
            currentMana = necromancer.getMana();
            maxMana = necromancer.getMaxMana();
            newMana = currentMana + manaAmount;
            if (newMana > maxMana) {
                newMana = maxMana;
            }
            necromancer.setMana(newMana);
            slowConsole.imprimirDevagar("Nova mana após recuperação: " + newMana);

        } else if (player instanceof Mage) {
            Mage mage = (Mage) player;
            currentMana = mage.getMana();
            maxMana = mage.getMaxMana();
            newMana = currentMana + manaAmount;
            if (newMana > maxMana) {
                newMana = maxMana;
            }
            mage.setMana(newMana);
            slowConsole.imprimirDevagar("Nova mana após recuperação: " + newMana);

        } else if (player instanceof Healer) {
            Healer healer = (Healer) player;
            currentMana = healer.getMana();
            maxMana = healer.getMaxMana();
            newMana = currentMana + manaAmount;
            if (newMana > maxMana) {
                newMana = maxMana;
            }
            healer.setMana(newMana);
            slowConsole.imprimirDevagar("Nova mana após recuperação: " + newMana);

        }
    }
}
