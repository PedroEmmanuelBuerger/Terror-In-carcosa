package rpg.Mode.Campaign.Events;

import rpg.Character.Classes.*;
import rpg.itens.Item;
import rpg.Utils.SlowConsole;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Pause implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void executeEvent(Attributes personagem) {
        boolean running = true;
        while (running) {
            slowConsole.imprimirDevagar("=== Você encontra um lugar seguro para uma pausa... ===");
            slowConsole.imprimirDevagar("=== Menu de Pausa ===");
            slowConsole.imprimirDevagar("1. Visualizar Status");
            slowConsole.imprimirDevagar("2. Usar Item");
            slowConsole.imprimirDevagar("3. Voltar ao Jogo");

            if (personagem instanceof Necromancer) {
                slowConsole.imprimirDevagar("4. Invocar Esqueleto");
            }
            if (personagem instanceof Warrior || personagem instanceof Paladin) {
                slowConsole.imprimirDevagar("4. Defender");
            }
            if (personagem instanceof Healer) {
                slowConsole.imprimirDevagar("4. Curar");
            }
            if (personagem instanceof Paladin) {
                slowConsole.imprimirDevagar("5. Curar");
            }

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    visualizarStatus(personagem);
                    break;
                case 2:
                    usarItem(personagem);
                    break;
                case 3:
                    running = false;
                    break;
                case 4:
                    if (personagem instanceof Necromancer necromancer) {
                        necromancer.summonImp();
                        slowConsole.imprimirDevagar("Você invocou um esqueleto para lhe auxiliar nas batalhas!");
                    } else if (personagem instanceof Warrior warrior) {
                        warrior.setDefese(true);
                        slowConsole.imprimirDevagar("Você assume uma postura defensiva, preparado para os ataques inimigos.");
                    } else if (personagem instanceof Paladin paladin) {
                        paladin.setDefese(true);
                        slowConsole.imprimirDevagar("Você assume uma postura defensiva, preparado para os ataques inimigos.");
                    } else if (personagem instanceof Healer healer) {
                        healer.heal(personagem);
                        slowConsole.imprimirDevagar("Você realiza um ritual de cura, restaurando a saúde de todos os membros do grupo.");
                    }
                    break;
                case 5:
                    if (personagem instanceof Paladin paladin) {
                        paladin.heal(personagem);
                        slowConsole.imprimirDevagar("Você realiza um ritual de cura, restaurando a saúde de todos os membros do grupo.");
                    }
                    break;
                default:
                    slowConsole.imprimirDevagar("Opção inválida. Tente novamente.");
            }
        }
    }

    private int getUserChoice() {
        int choice = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                validInput = true;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // clear the invalid input
                slowConsole.imprimirDevagar("Entrada inválida. Por favor, digite um número.");
            }
        }
        return choice;
    }

    private void visualizarStatus(Attributes personagem) {
        slowConsole.imprimirDevagar("=== Status de " + personagem.getName() + " ===");
        personagem.getTechnicalInfo();
        slowConsole.imprimirDevagar("Pressione Enter para voltar ao menu.");
        scanner.nextLine();
    }

    private void usarItem(Attributes personagem) {
        slowConsole.imprimirDevagar("Escolha o item que deseja usar:");
        if (personagem.getAbyssalInventory().isEmpty()) {
            slowConsole.imprimirDevagar("Sua bag está vazia. Não há itens para usar.");
            return;
        }

        for (Item item : personagem.getAbyssalInventory()) {
            slowConsole.imprimirDevagar("- " + item.getName());
        }

        slowConsole.imprimirDevagar("Digite o nome do item:");
        String itemName = scanner.nextLine();

        Item itemToUse = null;
        for (Item item : personagem.getAbyssalInventory()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemToUse = item;
                break;
            }
        }

        if (itemToUse != null) {
            personagem.useItem(itemToUse);
            slowConsole.imprimirDevagar("Você usou " + itemToUse.getName() + ".");
        } else {
            slowConsole.imprimirDevagar("Item não encontrado na bag.");
        }
    }
}
