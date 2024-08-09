package rpg.Campaign.Events;

import rpg.Character.Classes.Attributes;
import rpg.Utils.SlowConsole;

public class FindGoldEvent implements NonCombatEvent {
    private final int goldAmount;

    public FindGoldEvent(int goldAmount) {
        this.goldAmount = goldAmount;
    }

    @Override
    public void executeEvent(Attributes personagem) {
        SlowConsole slowConsole = new SlowConsole();

        personagem.setGold(personagem.getGold() + goldAmount);

        slowConsole.imprimirDevagar("Você encontrou uma quantidade sombria de " + goldAmount + " de ouro escondida entre ossos e ruínas...");
        slowConsole.imprimirDevagar("Você agora tem um total de " + personagem.getGold() + " de ouro, um símbolo do seu progresso sombrio.");
    }
}
