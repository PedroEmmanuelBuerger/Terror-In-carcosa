package rpg.Events;

import rpg.Classes.Attributes;
import rpg.Utils.SlowConsole;

public class MagicItem implements NonCombatEvent {
    private SlowConsole slowConsole = new SlowConsole();

    public void executeEvent(Attributes personagem) {
        slowConsole.imprimirDevagar("Você encontrou um item mágico! Ganhou +5 de ataque especial.");
        personagem.setSpecial(personagem.getSpecial() + 5);
    }
}