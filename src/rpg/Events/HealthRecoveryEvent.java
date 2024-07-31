package rpg.Events;

import rpg.Utils.SlowConsole;
import rpg.Classes.Attributes;

import java.util.Random;

public class HealthRecoveryEvent implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();
    private final Random random = new Random();

    public void executeEvent(Attributes personagem) {
        int healthRecovered = random.nextInt(10) + 10; // Recupera entre 10 a 19 de vida
        int maxHealth = personagem.getMaxHealthInitial();
        if (personagem.getHealthbar() < maxHealth) { // Verifica se não está com saúde máxima
            if (personagem.getHealthbar() + healthRecovered <= maxHealth) {
                personagem.setHealthbar(personagem.getHealthbar() + healthRecovered);
            } else {
                personagem.setHealthbar(maxHealth);
            }
            slowConsole.imprimirDevagar("Você encontrou um pedaço de carne... Recuperou +" + healthRecovered + " de vida!");
        } else {
            slowConsole.imprimirDevagar("Você encontrou um pedaço de carne... Mas está com vida cheia, então deixou para trás.");
        }
    }
}
