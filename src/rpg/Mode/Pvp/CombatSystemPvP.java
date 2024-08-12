package rpg.Mode.Pvp;

import rpg.Character.Classes.Attributes;
import rpg.Character.Classes.Healer;
import rpg.Character.Classes.Necromancer;
import rpg.Character.Classes.Warrior;
import rpg.Utils.CombatUtils;
import rpg.Utils.SlowConsole;
import rpg.itens.Item;
import rpg.itens.Specials.Imp;

import java.util.Random;
import java.util.Scanner;

public class CombatSystemPvP {
    private final Random random = new Random();
    private final SlowConsole slowConsole = new SlowConsole();

    public void startCombat(Scanner scanner, Attributes player1, Attributes player2) {
        slowConsole.imprimirDevagar("O combate entre " + player1.getName() + " e " + player2.getName() + " começou!");

        while (player1.getHealthbar() > 0 && player2.getHealthbar() > 0) {
            if (playerTurn(scanner, player1, player2)) break;
            if (playerTurn(scanner, player2, player1)) break;
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
        CombatUtils.printPlayerActions(player);

        int action = getPlayerAction(scanner);
        if (action == -1) return false;

        switch (action) {
            case 1:
                if (opponent instanceof Necromancer && !((Necromancer) opponent).getImps().isEmpty()) {
                    attackTarget(scanner, player, (Necromancer) opponent);
                } else {
                    player.attack(opponent);
                }
                break;
            case 2:
                player.attackWithSpecial(opponent);
                break;
            case 3:
                handleEscape(player);
                return true;
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
                return false;
        }

        return opponent.getHealthbar() <= 0;
    }

    private void attackTarget(Scanner scanner, Attributes player, Necromancer necromancer) {
        slowConsole.imprimirDevagar("Escolha o alvo do ataque:");
        slowConsole.imprimirDevagar("1 - " + necromancer.getName());
        int index = 2;
        for (Imp imp : necromancer.getImps()) {
            slowConsole.imprimirDevagar(index + " - " + imp.getName());
            index++;
        }

        int targetChoice = getPlayerAction(scanner);
        if (targetChoice == 1) {
            player.attack(necromancer);
        } else if (targetChoice > 1 && targetChoice <= necromancer.getImps().size() + 1) {
            player.attack(necromancer.getImps().get(targetChoice - 2));
        } else {
            slowConsole.imprimirDevagar("Escolha inválida. Você perdeu a vez.");
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

    private void handleEscape(Attributes player) {
        double escapeChance = 25.00;
        double randomSuccess = random.nextDouble() * 100.0;
        if (randomSuccess <= escapeChance) {
            slowConsole.imprimirDevagar(player.getName() + " fugiu com sucesso!");
        } else {
            slowConsole.imprimirDevagar(player.getName() + " tentou fugir, mas não conseguiu!");
        }
    }
}
