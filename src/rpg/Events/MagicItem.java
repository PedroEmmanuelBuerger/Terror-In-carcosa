package rpg.Events;

import rpg.Character.Classes.Attributes;
import rpg.Utils.SlowConsole;

public class MagicItem implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();

    public void executeEvent(Attributes personagem) {
        slowConsole.imprimirDevagar("Você encontrou um item mágico! Ganhou +5 de ataque especial.");
        personagem.setSpecial(personagem.getSpecial() + 5);
    }
}
