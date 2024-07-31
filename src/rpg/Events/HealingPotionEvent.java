package rpg.Events;

import rpg.Utils.SlowConsole;
import rpg.Classes.Attributes;

public class HealingPotionEvent implements NonCombatEvent {
    private SlowConsole slowConsole = new SlowConsole();

    @Override
    public void executeEvent(Attributes personagem) {
        if (personagem.getHealthbar() < personagem.getMaxHealthInitial()) { // Verifica se não está com saúde máxima
            slowConsole.imprimirDevagar("Você encontrou uma fonte de cura! Recuperou +20 de vida.");
            int maxHealth = personagem.getMaxHealthInitial();
            if (personagem.getHealthbar() + 20 <= maxHealth) {
                personagem.setHealthbar(personagem.getHealthbar() + 20);
            } else {
                personagem.setHealthbar(maxHealth);
            }
        } else {
            slowConsole.imprimirDevagar("Você encontrou uma fonte de cura... Mas está com vida cheia, então deixou para trás.");
        }
    }
}
