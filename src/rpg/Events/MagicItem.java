package rpg.Events;

import rpg.Character.Classes.Attributes;
import rpg.Utils.SlowConsole;

public class MagicItem implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();

    @Override
    public void executeEvent(Attributes personagem) {
        slowConsole.imprimirDevagar("Você encontrou um item mágico envolto em uma aura sinistra... A sensação é perturbadora, mas você ganhou +5 de ataque especial.");
        personagem.setSpecial(personagem.getSpecial() + 5);
    }
}
