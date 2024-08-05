package rpg.Events;

import rpg.Character.Classes.Attributes;
import rpg.Character.Classes.Necromancer;
import rpg.Character.Classes.Rogue;
import rpg.itens.Item;
import rpg.Utils.SlowConsole;

import java.util.Scanner;

public class Pause implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void executeEvent(Attributes personagem) {
        boolean running = true;
        while (running) {
            slowConsole.imprimirDevagar("=== Você decide dar uma pausa... ===");
            slowConsole.imprimirDevagar("=== Menu de Pausa ===");
            slowConsole.imprimirDevagar("1. Visualizar Status");
            slowConsole.imprimirDevagar("2. Usar Item");
            slowConsole.imprimirDevagar("3. Voltar ao Jogo");
            if (personagem instanceof Necromancer) {
                slowConsole.imprimirDevagar("4. Invocar Esqueleto");
            }

            int choice = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer do scanner

            switch (choice) {
                case 1:
                    visualizarStatus(personagem);
                    break;
                case 2:
                    usarItem(personagem);
                    break;
                case 3:
                    running = false; // Sai do loop para voltar ao jogo
                    break;
                case 4:
                    if (personagem instanceof Necromancer necromancer) {
                        ((Necromancer) personagem).summonImp();
                        break;
                    }
                default:
                    slowConsole.imprimirDevagar("Opção inválida. Tente novamente.");
            }
        }
    }

    private void visualizarStatus(Attributes personagem) {
        slowConsole.imprimirDevagar("=== Status de " + personagem.getName() + " ===");
        personagem.getTechnicalInfo();
        slowConsole.imprimirDevagar("Pressione Enter para voltar ao menu.");
        scanner.nextLine(); // Espera o usuário pressionar Enter
    }

    private void usarItem(Attributes personagem) {
        slowConsole.imprimirDevagar("Escolha o item para usar:");
        // Exibe itens disponíveis na bag
        if (personagem.getBag().isEmpty()) {
            slowConsole.imprimirDevagar("Sua bag está vazia.");
            return;
        }

        for (Item item : personagem.getBag()) {
            slowConsole.imprimirDevagar("- " + item.getName());
        }

        slowConsole.imprimirDevagar("Digite o nome do item:");
        String itemName = scanner.nextLine();

        Item itemToUse = null;
        for (Item item : personagem.getBag()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemToUse = item;
                break;
            }
        }

        if (itemToUse != null) {
            personagem.useItem(itemToUse); // Usar o item utilizando o método useItem de Attributes
        } else {
            slowConsole.imprimirDevagar("Item não encontrado na bag.");
        }
    }
}
