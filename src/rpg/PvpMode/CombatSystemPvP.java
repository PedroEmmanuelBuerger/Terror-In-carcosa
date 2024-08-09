package rpg.Utils;

import rpg.Character.Classes.Attributes;
import rpg.Character.Classes.Healer;
import rpg.Character.Classes.Mage;
import rpg.Character.Classes.Warrior;
import rpg.Utils.SlowConsole;
import rpg.itens.Item;

import java.util.Random;
import java.util.Scanner;

public class CombatSystemPvP {
    private Random random = new Random();
    private SlowConsole slowConsole = new SlowConsole();

    public void startCombat(Scanner scanner, Attributes player1, Attributes player2) {
        slowConsole.imprimirDevagar("O combate entre " + player1.getName() + " e " + player2.getName() + " começou!");

        while (player1.getHealthbar() > 0 && player2.getHealthbar() > 0) {
            if (!playerTurn(scanner, player1, player2)) break;
            if (!playerTurn(scanner, player2, player1)) break;
        }

        if (player1.getHealthbar() <= 0) {
            slowConsole.imprimirDevagar(player1.getName() + " foi derrotado!");
        } else if (player2.getHealthbar() <= 0) {
            slowConsole.imprimirDevagar(player2.getName() + " foi derrotado!");
        }
    }

    private boolean playerTurn(Scanner scanner, Attributes player, Attributes opponent) {
        slowConsole.imprimirDevagar("É a vez de " + player.getName() + ":");
        slowConsole.imprimirDevagar("Escolha sua ação:");
        printPlayerActions(player);

        int action = getPlayerAction(scanner);
        if (action == -1) return true;

        switch (action) {
            case 1:
                player.attack(opponent);
                break;
            case 2:
                player.attackWithSpecial(opponent);
                break;
            case 3:
                handleEscape(player, opponent);
                return false;
            case 4:
                player.getTechnicalInfo();
                break;
            case 5:
                viewOrUseItems(scanner, player);
                break;
            case 6:
                if (player instanceof Warrior) {
                    ((Warrior) player).defend();
                } else if (player instanceof Healer) {
                    ((Healer) player).heal(player);
                }
                break;
            default:
                slowConsole.imprimirDevagar("Ação inválida. Você perdeu a vez.");
                return true;
        }

        return opponent.getHealthbar() > 0;
    }

    private void printPlayerActions(Attributes player) {
        slowConsole.imprimirDevagar("1 - Atacar");
        if (player instanceof Mage) {
            slowConsole.imprimirDevagar("2 - Livro de magias");
        } else {
            slowConsole.imprimirDevagar("2 - Ataque Especial");
        }
        slowConsole.imprimirDevagar("3 - Fugir");
        slowConsole.imprimirDevagar("4 - Status");
        slowConsole.imprimirDevagar("5 - Usar Item");
        if (player instanceof Warrior) {
            slowConsole.imprimirDevagar("6 - Defender");
        } else if (player instanceof Healer) {
            slowConsole.imprimirDevagar("6 - Curar");
        }
    }

    private int getPlayerAction(Scanner scanner) {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            slowConsole.imprimirDevagar("Entrada inválida. Você perdeu a vez.");
            scanner.nextLine();
            return -1;
        }
    }

    private void viewOrUseItems(Scanner scanner, Attributes player) {
        slowConsole.imprimirDevagar("Escolha uma ação:");
        slowConsole.imprimirDevagar("1 - Visualizar Itens na Bag");
        slowConsole.imprimirDevagar("2 - Usar Item");

        int choice = getPlayerAction(scanner);
        scanner.nextLine();

        if (choice == 1) {
            for (Item item : player.getAbyssalInventory()) {
                slowConsole.imprimirDevagar("- " + item.getName());
            }
        } else if (choice == 2) {
            useItem(scanner, player);
        } else {
            slowConsole.imprimirDevagar("Opção inválida. Você perdeu a vez.");
        }
    }

    private void useItem(Scanner scanner, Attributes player) {
        slowConsole.imprimirDevagar("Escolha o item para usar:");
        for (Item item : player.getAbyssalInventory()) {
            slowConsole.imprimirDevagar("- " + item.getName());
        }

        slowConsole.imprimirDevagar("Digite o nome do item:");
        String itemName = scanner.nextLine().trim();

        Item itemToUse = null;
        for (Item item : player.getAbyssalInventory()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemToUse = item;
                break;
            }
        }

        if (itemToUse != null) {
            player.useItem(itemToUse);
        } else {
            slowConsole.imprimirDevagar("Item não encontrado na bag.");
        }
    }

    private void handleEscape(Attributes player, Attributes opponent) {
        double escapeChance = 25.00;
        double randomSuccess = random.nextDouble() * 100.0;
        if (randomSuccess <= escapeChance) {
            slowConsole.imprimirDevagar(player.getName() + " fugiu com sucesso!");
        } else {
            slowConsole.imprimirDevagar(player.getName() + " tentou fugir, mas não conseguiu!");
        }
    }
}
