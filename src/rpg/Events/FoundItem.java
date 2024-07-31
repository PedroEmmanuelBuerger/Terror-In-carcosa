// Arquivo: rpg/Events/FoundItem.java
package rpg.Events;

import rpg.Classes.Attributes;
import rpg.itens.BagItens.Potion;
import rpg.Utils.SlowConsole;

import java.util.Random;
import java.util.Scanner;

public class FoundItem implements NonCombatEvent {
    private SlowConsole slowConsole = new SlowConsole();

    @Override
    public void executeEvent(Attributes personagem) {
        // Cria uma lista de potions disponíveis
        Potion[] potions = getAvailablePotions();

        // Seleciona uma potion aleatória da lista
        Random random = new Random();
        Potion foundPotion = potions[random.nextInt(potions.length)];

        // Exibe informações sobre a poção encontrada
        slowConsole.imprimirDevagar("Você encontrou uma poção: " + foundPotion.getName() + " que cura " + foundPotion.getHealingAmount() + " pontos de vida.");

        // Pergunta ao jogador se deseja adicionar a poção à bag
        slowConsole.imprimirDevagar("Deseja adicionar esta poção à sua bag? (s/n)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        // Atualiza a bag do personagem com base na escolha
        if (choice.equalsIgnoreCase("s")) {
            personagem.addItemToBag(foundPotion);
        } else {
            slowConsole.imprimirDevagar("Você decidiu não adicionar a poção à sua bag.");
        }
    }

    private Potion[] getAvailablePotions() {
        return new Potion[] {
                new Potion("Poção de Vida Menor",15, 50),
                new Potion("Poção de Vida Média",25, 100),
                new Potion("Poção de Vida Maior",50, 200)
        };
    }
}
