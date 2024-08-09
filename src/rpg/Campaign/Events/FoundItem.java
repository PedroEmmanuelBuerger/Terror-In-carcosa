package rpg.Campaign.Events;

import rpg.Character.Classes.Attributes;
import rpg.itens.BagItens.Potion;
import rpg.Utils.SlowConsole;

import java.util.Random;
import java.util.Scanner;

public class FoundItem implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();

    @Override
    public void executeEvent(Attributes personagem) {
        Potion[] potions = getAvailablePotions();

        Random random = new Random();
        Potion foundPotion = potions[random.nextInt(potions.length)];

        slowConsole.imprimirDevagar("Você encontrou uma poção antiga e macabra: " + foundPotion.getName() + ". Ela pode curar " + foundPotion.getHealingAmount() + " pontos de vida.");

        slowConsole.imprimirDevagar("Deseja adicionar esta poção ao seu inventário sinistro? (s/n)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("s")) {
            personagem.addItemToAbyssalInventory(foundPotion);
            slowConsole.imprimirDevagar("Você adicionou a poção à sua bag, um item que pode ser útil nas trevas.");
        } else {
            slowConsole.imprimirDevagar("Você decidiu não adicionar a poção à sua bag, talvez para não lembrar dos horrores que ela representa.");
        }
    }

    private Potion[] getAvailablePotions() {
        return new Potion[] {
                new Potion("Poção de Vida Menor", 15, 50),
                new Potion("Poção de Vida Média", 25, 100),
                new Potion("Poção de Vida Maior", 50, 200)
        };
    }
}
