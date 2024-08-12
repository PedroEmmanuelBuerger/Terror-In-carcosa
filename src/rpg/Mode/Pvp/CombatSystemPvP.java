package rpg.Mode.Pvp;

import rpg.Character.Classes.*;
import rpg.Utils.CombatUtilsPvp;
import rpg.Utils.SlowConsole;
import rpg.itens.Item;
import rpg.itens.Specials.Imp;

import java.util.Iterator;
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
    }

    private boolean playerTurn(Scanner scanner, Attributes player, Attributes opponent) {
        slowConsole.imprimirDevagar("É a vez de " + player.getName() + ":");
        CombatUtilsPvp.printPlayerActions(player);
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
                if (player instanceof Mage) {
                    player.attackWithSpecial(opponent);
                } else if (player instanceof Necromancer) {
                    ((Necromancer) player).summonImp();
                } else {
                    player.attackWithSpecial(opponent);
                }
                break;
            case 3:
                if (player instanceof Necromancer) {
                    ((Necromancer) player).selectSpell(opponent);
                } else {
                    player.getTechnicalInfo();
                }
                break;
            case 4:
                if (player instanceof Necromancer) {
                    player.getTechnicalInfo();
                } else {
                    viewOrUseItems(scanner, player);
                }
                break;
            case 5:
                if (player instanceof Warrior) {
                    ((Warrior) player).defend();
                } else if (player instanceof Healer) {
                    ((Healer) player).ressurection(player);
                } else {
                    viewOrUseItems(scanner, player);
                }
                break;
            case 6:
                if (player instanceof Healer) {
                    ((Healer) player).heal(player);
                } else {
                    slowConsole.imprimirDevagar("Ação inválida. Você perdeu a vez.");
                    return false;
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
        Iterator<Imp> impIterator = necromancer.getImps().iterator();
        while (impIterator.hasNext()) {
            Imp imp = impIterator.next();
            if (imp.getHealthbar() > 0) {
                slowConsole.imprimirDevagar(index + " - " + imp.getName());
                index++;
            } else {
                impIterator.remove(); // Remover o Imp morto da lista
            }
        }

        int targetChoice = getPlayerAction(scanner);
        if (targetChoice == 1) {
            player.attack(necromancer);
        } else if (targetChoice > 1 && targetChoice < index) {
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
}
