package rpg.Mode.Campaign.Events;

import rpg.Character.Classes.*;
import rpg.Utils.SlowConsole;

import java.util.Random;

public class OldRune implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();
    private final Random random = new Random();

    @Override
    public void executeEvent(Attributes personagem) {
        int manaRecovered = random.nextInt(10) + 10;
        if (personagem instanceof Mage) {
            ((Mage) personagem).setMana(((Mage) personagem).getMana() + 5);
            personagem.setSpecial(personagem.getSpecial() + 5);
            slowConsole.imprimirDevagar("Você encontrou uma runa antiga e enigmática, coberta por inscrições de outro mundo... Ganhou " + manaRecovered + " de mana e 5 de ataque especial.");
        } else if (personagem instanceof Healer) {
            ((Healer) personagem).setMana(((Healer) personagem).getMana() + 5);
            personagem.setSpecial(personagem.getSpecial() + 5);
            slowConsole.imprimirDevagar("Você encontrou uma runa antiga, suas inscrições emitem uma luz sinistra... Ganhou " + manaRecovered + " de mana e 5 de ataque especial.");
        } else if (personagem instanceof Necromancer) {
            ((Necromancer) personagem).setMana(((Necromancer) personagem).getMana() + 5);
            slowConsole.imprimirDevagar("Você encontrou uma runa antiga, suas inscrições emitem uma luz sinistra... Ganhou " + manaRecovered + " de mana e 5 de ataque especial.");
        } else if (personagem instanceof Paladin) {
            ((Paladin) personagem).setMana(((Paladin) personagem).getMana() + 5);
            slowConsole.imprimirDevagar("Você encontrou uma runa antiga, suas inscrições emitem uma luz sinistra... Ganhou " + manaRecovered + " de mana e 5 de ataque especial.");
        }
    }
}
