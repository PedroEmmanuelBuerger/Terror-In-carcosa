package rpg.Events;

import rpg.Utils.SlowConsole;
import rpg.Classes.Attributes;
import rpg.Classes.Healer;
import rpg.Classes.Mage;

import java.util.Random;

public class ManaRecoveryEvent implements NonCombatEvent {
    private SlowConsole slowConsole = new SlowConsole();
    private Random random = new Random();

    public void executeEvent(Attributes personagem) {
        int manaRecovered = random.nextInt(10) + 10; // Recupera entre 10 a 19 de mana
        if (personagem instanceof Mage) {
            ((Mage) personagem).recoverMana(manaRecovered);
            slowConsole.imprimirDevagar("Você encontrou um cristal de mana! ganhou " + manaRecovered + " de mana.");
        } else if (personagem instanceof Healer) {
            ((Healer) personagem).recoverMana(manaRecovered);
            slowConsole.imprimirDevagar("Você encontrou um foco de cura! ganhou " + manaRecovered + " de mana.");
        }
    }
}
