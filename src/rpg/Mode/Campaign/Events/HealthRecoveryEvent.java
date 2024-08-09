package rpg.Mode.Campaign.Events;

import rpg.Utils.SlowConsole;
import rpg.Character.Classes.Attributes;

import java.util.Random;

public class HealthRecoveryEvent implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();
    private final Random random = new Random();

    @Override
    public void executeEvent(Attributes personagem) {
        int healthRecovered = random.nextInt(10) + 10; // Recupera entre 10 a 19 de vida
        int maxHealth = personagem.getMaxHealthInitial();
        if (personagem.getHealthbar() < maxHealth) {
            personagem.setHealthbar(Math.min(personagem.getHealthbar() + healthRecovered, maxHealth));
            slowConsole.imprimirDevagar("Você encontrou um pedaço de carne descomunal e envenenado... Recuperou +" + healthRecovered + " de vida, mas o gosto é terrível.");
        } else {
            slowConsole.imprimirDevagar("Você encontrou um pedaço de carne, mas está com a vida cheia. O cheiro é insuportável e você o deixa para trás.");
        }
    }
}
