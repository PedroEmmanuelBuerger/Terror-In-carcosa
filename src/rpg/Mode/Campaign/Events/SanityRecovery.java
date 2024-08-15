package rpg.Mode.Campaign.Events;

import rpg.Character.Classes.Attributes;
import rpg.Utils.SlowConsole;

public class SanityRecovery implements NonCombatEvent{
    private final SlowConsole slowConsole = new SlowConsole();
    @Override
    public void executeEvent(Attributes personagem) {
        slowConsole.imprimirDevagar("Você reflete sobre seu passado e suas motivações por um segundo... sua sanidade é aumentada em 10");
        personagem.setMind(personagem.getMind() + 10);
    }
}
