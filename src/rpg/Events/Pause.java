package rpg.Events;

import rpg.Classes.Attributes;
import rpg.itens.Item;
import rpg.Utils.SlowConsole;

import java.util.Scanner;

public class Pause implements NonCombatEvent {
    private SlowConsole slowConsole = new SlowConsole();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void executeEvent(Attributes personagem) {
        boolean running = true;
        while (running) {
            slowConsole.imprimirDevagar("=== Você decide dar uma pausa... ===");
            slowConsole.imprimirDevagar("=== Menu de Pausa ===");
            slowConsole.imprimirDevagar("1. Visualizar Status");
            slowConsole.imprimirDevagar("2. Usar Item");
            slowConsole.imprimirDevagar("3. Voltar ao Jogo");

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
            // Implementar a lógica para usar o item aqui
            slowConsole.imprimirDevagar("Usou o item: " + itemToUse.getName());
            personagem.removeItemFromBag(itemToUse); // Remove o item após o uso
        } else {
            slowConsole.imprimirDevagar("Item não encontrado na bag.");
        }
    }
}
