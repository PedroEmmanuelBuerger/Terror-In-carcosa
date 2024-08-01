package rpg.itens.BagItens;

import rpg.Character.Classes.Attributes;
import rpg.Utils.SlowConsole;
import rpg.itens.Item;

public class Potion extends Item {
    private SlowConsole slowConsole = new SlowConsole();
    private int healingAmount;

    public Potion(String name,int price, int healingAmount) {
        super(name,price);
        this.healingAmount = healingAmount;
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    @Override
    public void UseItem(Attributes player) {
        // Obtém a quantidade de vida atual e a cura disponível
        int currentHealth = player.getHealthbar();
        int healingAmount = this.getHealingAmount();
        int maxHealth = player.getMaxHealthInitial();

        // Calcula a nova vida, garantindo que não exceda o máximo permitido
        int newHealth = currentHealth + healingAmount;
        if (newHealth > maxHealth) {
            newHealth = maxHealth; // A vida não pode exceder o máximo permitido
        }

        // Atualiza a vida do jogador
        player.setHealthbar(newHealth);
       slowConsole.imprimirDevagar("Nova vida após cura: " + newHealth);
    }
}
