package rpg.Events;

import rpg.Character.Classes.Attributes;
import rpg.Character.Classes.Healer;
import rpg.Character.Classes.Mage;
import rpg.Utils.SlowConsole;

import java.util.Random;

public class OldRune implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();
    private final Random random = new Random();

    public void executeEvent(Attributes personagem) {
        int manaRecovered = random.nextInt(10) + 10; // Recupera entre 10 a 19 de mana
        if (personagem instanceof Mage) {
            ((Mage) personagem).setMana(((Mage) personagem).getMana() + 5);
            personagem.setSpecial(personagem.getSpecial() + 5);
            slowConsole.imprimirDevagar("Você encontrou um runa antiga...ganhou " + manaRecovered + " de mana e 5 de ataque especial.");
        } else if (personagem instanceof Healer) {
            ((Healer) personagem).setMana(((Healer) personagem).getMana() + 5);
            personagem.setSpecial(((Healer) personagem).getSpecial() + 5);
            slowConsole.imprimirDevagar("Você encontrou um runa antiga...ganhou " + manaRecovered + " de mana e 5 de ataque especial.");
        }
    }
}

