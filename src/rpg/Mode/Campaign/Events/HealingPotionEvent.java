package rpg.Mode.Campaign.Events;

import rpg.Utils.SlowConsole;
import rpg.Character.Classes.Attributes;

public class HealingPotionEvent implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();

    @Override
    public void executeEvent(Attributes personagem) {
        if (personagem.getHealthbar() < personagem.getMaxHealthInitial()) {
            slowConsole.imprimirDevagar("Você encontrou uma fonte de cura antiga e esquecida... Recuperou +20 de vida.");
            int maxHealth = personagem.getMaxHealthInitial();
            personagem.setHealthbar(Math.min(personagem.getHealthbar() + 20, maxHealth));
        } else {
            slowConsole.imprimirDevagar("Você encontrou uma fonte de cura, mas está com a vida cheia. O local parece amaldiçoado.");
        }
    }
}
