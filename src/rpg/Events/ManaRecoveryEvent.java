package rpg.Events;

import rpg.Character.Classes.Necromancer;
import rpg.Utils.SlowConsole;
import rpg.Character.Classes.Attributes;
import rpg.Character.Classes.Healer;
import rpg.Character.Classes.Mage;

import java.util.Random;

public class ManaRecoveryEvent implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();
    private final Random random = new Random();

    @Override
    public void executeEvent(Attributes personagem) {
        int manaRecovered = random.nextInt(10) + 10; // Recupera entre 10 a 19 de mana
        if (personagem instanceof Mage) {
            ((Mage) personagem).recoverMana(manaRecovered);
            slowConsole.imprimirDevagar("Você encontrou um cristal de mana pulsante, emanando um brilho enigmático. Ganhou " + manaRecovered + " de mana.");
        } else if (personagem instanceof Healer) {
            ((Healer) personagem).recoverMana(manaRecovered);
            slowConsole.imprimirDevagar("Você encontrou um foco de cura irradiando uma luz fraca e perturbadora. Ganhou " + manaRecovered + " de mana.");
        } else if (personagem instanceof Necromancer) {
            ((Necromancer) personagem).recoverMana(manaRecovered);
            slowConsole.imprimirDevagar("Você encontrou um corpo profanado, uma fonte de energia negra. Ganhou " + manaRecovered + " de mana.");
        }
    }
}
