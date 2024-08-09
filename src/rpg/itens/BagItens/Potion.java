package rpg.itens.BagItens;

import rpg.Character.Classes.Attributes;
import rpg.Utils.SlowConsole;
import rpg.itens.Item;

public class Potion extends Item {
    private final SlowConsole slowConsole = new SlowConsole();
    private final int healingAmount;

    public Potion(String name, int price, int healingAmount) {
        super(name, price);
        this.healingAmount = healingAmount;
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    @Override
    public void UseItem(Attributes player) {
        int currentHealth = player.getHealthbar();
        int healingAmount = this.getHealingAmount();
        int maxHealth = player.getMaxHealthInitial();

        int newHealth = currentHealth + healingAmount;
        if (newHealth > maxHealth) {
            newHealth = maxHealth;
        }

        player.setHealthbar(newHealth);
        slowConsole.imprimirDevagar("Ao beber a poção, um frio gélido percorre seu corpo, como se algo antigo e desconhecido estivesse cuidando de seus ferimentos. Nova vida: " + newHealth);
    }
}
