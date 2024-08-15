package rpg.Mode.Coops.Dungeons;

import rpg.Character.Classes.*;
import rpg.Utils.SlowConsole;
import rpg.itens.Item;
import rpg.itens.Specials.Imp;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CombatSystemCoop {
    private final Random random = new Random();
    private final SlowConsole slowConsole = new SlowConsole();

    public void startCombat(Scanner scanner, List<Attributes> jogadores, Attributes enemy) {
        slowConsole.imprimirDevagar("Você encontrou um " + enemy.getName() + "!");
        slowConsole.imprimirDevagar(enemy.getName() + ": " + enemy.getQuote());

        while (jogadores.stream().anyMatch(j -> j.getHealthbar() > 0) && enemy.getHealthbar() > 0) {
            for (Attributes jogador : jogadores) {
                if (jogador.getHealthbar() > 0) {
                    if (!playerTurn(scanner, jogador, jogadores, enemy)) break;
                }
            }
            if (enemy.getHealthbar() > 0) {
                enemyTurn(jogadores, enemy);
            }
        }

        if (enemy.getHealthbar() <= 0) {
            slowConsole.imprimirDevagar("O " + enemy.getName() + " foi derrotado!");
        } else {
            slowConsole.imprimirDevagar("Vocês foram derrotados!");
        }
    }

    private boolean playerTurn(Scanner scanner, Attributes jogador, List<Attributes> jogadores, Attributes enemy) {
        slowConsole.imprimirDevagar("É a vez de " + jogador.getName() + ":");
        slowConsole.imprimirDevagar("Escolha sua ação:");
        printPlayerActions(jogador);

        int acaoJogador = getPlayerAction(scanner);
        if (acaoJogador == -1) return true;

        switch (acaoJogador) {
            case 1:
                jogador.attack(enemy);
                break;
            case 2:
                jogador.attackWithSpecial(enemy);
                break;
            case 3:
                if (jogador instanceof Necromancer) {
                    ((Necromancer) jogador).selectSpell(enemy);
                } else {
                    return handleEscape(enemy);
                }
                break;
            case 4:
                if (jogador instanceof Necromancer) {
                    return handleEscape(enemy);
                } else {
                    jogador.getTechnicalInfo();
                }
                break;
            case 5:
                if (jogador instanceof Necromancer) {
                    jogador.getTechnicalInfo();
                } else {
                    viewOrUseItems(scanner, jogador);
                }
                break;
            case 6:
                switch (jogador) {
                    case Healer _ -> handleRessurection(jogador, jogadores, scanner);
                    case Warrior warrior -> warrior.defend();
                    case Paladin _ -> handleHealing(scanner, jogador, jogadores);
                    default -> viewOrUseItems(scanner, jogador);
                }
                break;
            case 7:
                if (jogador instanceof Healer) {
                    handleHealing(scanner, jogador, jogadores);
                }
                break;
            default:
                slowConsole.imprimirDevagar("Ação inválida. Você perdeu a vez.");
                return true;
        }

        if (jogador instanceof Necromancer) {
            handleImps((Necromancer) jogador, enemy);
        }

        return enemy.getHealthbar() > 0;
    }

    private void enemyTurn(List<Attributes> jogadores, Attributes enemy) {
        slowConsole.imprimirDevagar("\nTurno de " + enemy.getName() + ":");

        for (Attributes jogador : jogadores) {
            if (jogador.getHealthbar() > 0) {
                Attributes target = getEnemyTarget(jogadores);
                int acaoInimigo = random.nextInt(3);

                switch (acaoInimigo) {
                    case 0:
                        enemy.attack(target);
                        break;
                    case 1:
                        enemy.attackWithSpecial(target);
                        break;
                    default:
                        slowConsole.imprimirDevagar(enemy.getName() + " está preparando seu ataque!");
                        break;
                }

                if (jogadores.stream().allMatch(j -> j.getHealthbar() <= 0)) {
                    slowConsole.imprimirDevagar("Todos os jogadores foram derrotados!");
                    return;
                }
            }
        }
    }

    private void printPlayerActions(Attributes jogador) {
        slowConsole.imprimirDevagar("1 - Atacar");
        if (jogador instanceof Mage) {
            slowConsole.imprimirDevagar("2 - Livro de magias");
        } else if (jogador instanceof Necromancer) {
            slowConsole.imprimirDevagar("2 - Invocar Imp (Custo de mana: 15)");
        } else {
            slowConsole.imprimirDevagar("2 - Ataque Especial");
        }
        if (jogador instanceof Necromancer) {
            slowConsole.imprimirDevagar("3 - Necromancia");
            slowConsole.imprimirDevagar("4 - Fugir");
            slowConsole.imprimirDevagar("5 - Status");
            slowConsole.imprimirDevagar("6 - Usar Item");
        } else {
            slowConsole.imprimirDevagar("3 - Fugir");
            slowConsole.imprimirDevagar("4 - Status");
            slowConsole.imprimirDevagar("5 - Usar Item");
            if (jogador instanceof Warrior) {
                slowConsole.imprimirDevagar("6 - Defender");
            } else if (jogador instanceof Healer) {
                slowConsole.imprimirDevagar("6 - Ressurreição");
                slowConsole.imprimirDevagar("7 - Curar");
            } else if (jogador instanceof Paladin) {
                slowConsole.imprimirDevagar("6 - Curar");
            }
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

    private boolean handleEscape(Attributes enemy) {
        double escapeChance = 25.00;
        double randomSucess = random.nextDouble() * 100.0;
        if (randomSucess <= escapeChance && !enemy.getName().equals("Ghazkull") && !enemy.getName().equals("Lorde Rei Dragão")) {
            slowConsole.imprimirDevagar("Vocês fugiu!");
            return false;
        } else {
            slowConsole.imprimirDevagar("Vocês tentaram fugir, mas não conseguiu!");
            return true;
        }
    }

    private void handleRessurection(Attributes healer, List<Attributes> jogadores, Scanner scanner) {
        slowConsole.imprimirDevagar("Escolha o jogador para ressuscitar:");
        for (int i = 0; i < jogadores.size(); i++) {
            Attributes jogador = jogadores.get(i);
            slowConsole.imprimirDevagar((i + 1) + " - " + jogador.getName() + " (HP: " + jogador.getHealthbar() + ")");
        }
        int escolha = getPlayerAction(scanner) - 1;
        if (escolha >= 0 && escolha < jogadores.size()) {
            Attributes alvo = jogadores.get(escolha);
            if (alvo.getHealthbar() <= 0) {
                ((Healer) healer).ressurection(alvo);
            } else {
                slowConsole.imprimirDevagar(alvo.getName() + " já está vivo!");
            }
        } else {
            slowConsole.imprimirDevagar("Escolha inválida. Você perdeu a vez.");
        }
    }

    private void handleHealing(Scanner scanner, Attributes healer, List<Attributes> jogadores) {
        slowConsole.imprimirDevagar("Escolha o jogador para curar:");
        for (int i = 0; i < jogadores.size(); i++) {
            Attributes jogador = jogadores.get(i);
            slowConsole.imprimirDevagar((i + 1) + " - " + jogador.getName() + " (HP: " + jogador.getHealthbar() + ")");
        }
        int escolha = getPlayerAction(scanner) - 1;
        if (escolha >= 0 && escolha < jogadores.size()) {
            Attributes alvo = jogadores.get(escolha);
            if (healer instanceof Healer) {
                ((Healer) healer).heal(alvo);
            } else if (healer instanceof Paladin) {
                ((Paladin) healer).heal(alvo);
            }
        } else {
            slowConsole.imprimirDevagar("Escolha inválida. Você perdeu a vez.");
        }
    }

    private void handleImps(Necromancer necromancer, Attributes enemy) {
        for (Imp imp : necromancer.getImps()) {
            if (imp.getHealthbar() > 0 && enemy.getHealthbar() > 0) {
                slowConsole.imprimirDevagar("\nTurno do " + imp.getName() + ":");
                imp.impAttack(enemy, necromancer);
            }
        }
        necromancer.getImps().removeIf(imp -> imp.getHealthbar() <= 0);
    }

    private Attributes getEnemyTarget(List<Attributes> jogadores) {
        List<Attributes> alivePlayers = jogadores.stream()
                .filter(j -> j.getHealthbar() > 0)
                .toList();

        return alivePlayers.get(random.nextInt(alivePlayers.size()));
    }

    private void viewOrUseItems(Scanner scanner, Attributes jogador) {
        slowConsole.imprimirDevagar("Escolha uma ação:");
        slowConsole.imprimirDevagar("1 - Visualizar Itens na Bag");
        slowConsole.imprimirDevagar("2 - Usar Item");

        int choice = getPlayerAction(scanner);
        scanner.nextLine();

        if (choice == 1) {
            for (Item item : jogador.getAbyssalInventory()) {
                slowConsole.imprimirDevagar("- " + item.getName());
            }
        } else if (choice == 2) {
            useItem(scanner, jogador);
        } else {
            slowConsole.imprimirDevagar("Opção inválida. Você perdeu a vez.");
        }
    }

    private void useItem(Scanner scanner, Attributes jogador) {
        slowConsole.imprimirDevagar("Escolha o item para usar:");
        for (Item item : jogador.getAbyssalInventory()) {
            slowConsole.imprimirDevagar("- " + item.getName());
        }

        slowConsole.imprimirDevagar("Digite o nome do item:");
        String itemName = scanner.nextLine().trim();

        Item itemToUse = null;
        for (Item item : jogador.getAbyssalInventory()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemToUse = item;
                break;
            }
        }

        if (itemToUse != null) {
            jogador.useItem(itemToUse);
        } else {
            slowConsole.imprimirDevagar("Item não encontrado na bag.");
        }
    }
}
