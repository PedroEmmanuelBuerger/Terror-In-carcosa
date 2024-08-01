package rpg.Events;

import rpg.Character.Classes.Attributes;
import rpg.Utils.SlowConsole;

public class FindGoldEvent implements NonCombatEvent {
    private int goldAmount; // Quantidade de ouro a ser encontrada

    public FindGoldEvent(int goldAmount) {
        this.goldAmount = goldAmount;
    }

    @Override
    public void executeEvent(Attributes personagem) {
        SlowConsole slowConsole = new SlowConsole();

        // Adiciona o ouro ao personagem
        personagem.setGold(personagem.getGold() + goldAmount);

        // Mensagem para o jogador
        slowConsole.imprimirDevagar("Você encontrou " + goldAmount + " de ouro!");
        slowConsole.imprimirDevagar("Você agora tem um total de " + personagem.getGold() + " de ouro.");
    }
}
