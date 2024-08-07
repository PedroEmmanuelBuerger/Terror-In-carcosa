package rpg.Events;

import rpg.Utils.SlowConsole;
import rpg.Character.Classes.Attributes;

public class RareItemEvent implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();

    @Override
    public void executeEvent(Attributes personagem) {
        slowConsole.imprimirDevagar("Em meio ao nevoeiro de Carcosa, você encontra um artefato antigo e perturbador. Sua presença incomoda, mas você sente um poder desconhecido fluindo para dentro de você. Ganhou +5 de ataque.");
        personagem.setAttack(personagem.getAttack() + 5);
    }
}
