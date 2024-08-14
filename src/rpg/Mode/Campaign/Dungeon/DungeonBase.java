package rpg.Mode.Campaign.Dungeon;

import rpg.Character.Classes.*;
import rpg.Mode.Campaign.Pve;
import rpg.Monsters.Bosses.Ghazkull;
import rpg.Monsters.Bosses.KingDragon;
import rpg.Monsters.Bosses.KnightOfFear;
import rpg.Utils.Messages.End;
import rpg.Utils.Messages.Start;
import rpg.Utils.SlowConsole;
import rpg.itens.Item;
import rpg.itens.Specials.Imp;

import java.util.Random;
import java.util.Scanner;

public abstract class DungeonBase implements Dungeon {
    protected Random random = new Random();
    protected SlowConsole slowConsole = new SlowConsole();

    protected abstract Attributes createEnemy(Attributes personagem);

    protected abstract void onBossDefeated(Attributes personagem);

    @Override
    public void startCombat(Scanner scanner, Attributes personagem) {
        Attributes enemy = createEnemy(personagem);
        if (enemy == null) return;

        slowConsole.imprimirDevagar("Você encontrou um " + enemy.getName() + "!");
        slowConsole.imprimirDevagar(enemy.getName() + ": " + enemy.getQuote());

        while (personagem.getHealthbar() > 0 && enemy.getHealthbar() > 0) {
            if (!playerTurn(scanner, personagem, enemy)) break;
            if (!enemyTurn(personagem, enemy)) break;
        }

        if (enemy.getHealthbar() <= 0 && (enemy instanceof Ghazkull || enemy instanceof KingDragon || enemy instanceof KnightOfFear)) {
            onBossDefeated(personagem);
        }
    }

    private boolean playerTurn(Scanner scanner, Attributes personagem, Attributes enemy) {
        slowConsole.imprimirDevagar("Sua vez de agir:");
        slowConsole.imprimirDevagar("Escolha sua ação:");
        printPlayerActions(personagem);

        int acaoJogador = getPlayerAction(scanner);
        if (acaoJogador == -1) return true;

        switch (acaoJogador) {
            case 1:
                personagem.attack(enemy);
                break;
            case 2:
                personagem.attackWithSpecial(enemy);
                break;
            case 3:
                if (personagem instanceof Necromancer) {
                    ((Necromancer) personagem).selectSpell(enemy);
                } else {
                    return handleEscape(enemy);
                }
                break;
            case 4:
                if (personagem instanceof Necromancer) {
                    return handleEscape(enemy);
                } else {
                    personagem.getTechnicalInfo();
                }
                break;
            case 5:
                if (personagem instanceof Necromancer) {
                    personagem.getTechnicalInfo();
                } else {
                    viewOrUseItems(scanner, personagem);
                }
                break;
            case 6:
                if (personagem instanceof Healer) {
                    handleRessurection(personagem);
                } else if (personagem instanceof Warrior) {
                    ((Warrior) personagem).defend();
                } else if (personagem instanceof Paladin) {
                    ((Paladin) personagem).defend();
                } else {
                    viewOrUseItems(scanner, personagem);
                }
                break;
            case 7:
                if (personagem instanceof Healer) {
                    ((Healer) personagem).heal(personagem);
                } else if (personagem instanceof Paladin) {
                    ((Paladin) personagem).heal(personagem);
                }
                break;
            default:
                slowConsole.imprimirDevagar("Ação inválida. Você perdeu a vez.");
                return true;
        }

        if (personagem instanceof Necromancer) {
            handleImps((Necromancer) personagem, enemy);
        }

        return enemy.getHealthbar() > 0;
    }

    private void viewOrUseItems(Scanner scanner, Attributes personagem) {
        slowConsole.imprimirDevagar("Escolha uma ação:");
        slowConsole.imprimirDevagar("1 - Visualizar Itens na Bag");
        slowConsole.imprimirDevagar("2 - Usar Item");

        int choice = getPlayerAction(scanner);
        scanner.nextLine();

        if (choice == 1) {
            for (Item item : personagem.getAbyssalInventory()) {
                slowConsole.imprimirDevagar("- " + item.getName());
            }
        } else if (choice == 2) {
            useItem(scanner, personagem);
        } else {
            slowConsole.imprimirDevagar("Opção inválida. Você perdeu a vez.");
        }
    }

    private void useItem(Scanner scanner, Attributes personagem) {
        slowConsole.imprimirDevagar("Escolha o item para usar:");
        for (Item item : personagem.getAbyssalInventory()) {
            slowConsole.imprimirDevagar("- " + item.getName());
        }

        slowConsole.imprimirDevagar("Digite o nome do item:");
        String itemName = scanner.nextLine().trim();

        Item itemToUse = null;
        for (Item item : personagem.getAbyssalInventory()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemToUse = item;
                break;
            }
        }

        if (itemToUse != null) {
            personagem.useItem(itemToUse);
        } else {
            slowConsole.imprimirDevagar("Item não encontrado na bag.");
        }
    }

    private boolean enemyTurn(Attributes personagem, Attributes enemy) {
        slowConsole.imprimirDevagar("\nTurno de " + enemy.getName() + ":");

        Attributes target = getEnemyTarget(personagem);
        int acaoInimigo = random.nextInt(3);

        switch (acaoInimigo) {
            case 0:
                enemy.attack(target);
                break;
            case 1:
                enemy.attackWithSpecial(target);
                break;
            default:
                if (enemy instanceof Ghazkull || enemy instanceof KingDragon) {
                    enemy.attack(target);
                    break;
                } else {
                    slowConsole.imprimirDevagar(enemy.getName() + " está preparando seu ataque!");
                }
                break;
        }

        if (personagem.getHealthbar() <= 0) {
            handleDefeat();
            return false;
        }

        if (personagem instanceof Necromancer) {
            ((Necromancer) personagem).getImps().removeIf(imp -> imp.getHealthbar() <= 0);
        }

        return true;
    }

    private void handleDefeat() {
        End.DefeatGenericMonster();

        slowConsole.imprimirDevagar("Deseja reiniciar o jogo? (s/n)");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("s")) {
            restartApplication();
        } else {
        }
    }

    public static void restartApplication() {
        System.out.println("Reiniciando o jogo...");
        Start.startApp();
        Pve.startBattle();
    }


    private void printPlayerActions(Attributes personagem) {
        slowConsole.imprimirDevagar("1 - Atacar");
        if (personagem instanceof Mage) {
            slowConsole.imprimirDevagar("2 - Livro de magias");
        } else if (personagem instanceof Necromancer) {
            slowConsole.imprimirDevagar("2 - Invocar Imp (Custo de mana: 15)");
        } else {
            slowConsole.imprimirDevagar("2 - Ataque Especial");
        }
        if (personagem instanceof Necromancer) {
            slowConsole.imprimirDevagar("3 - Necromancia");
            slowConsole.imprimirDevagar("4 - Fugir");
            slowConsole.imprimirDevagar("5 - Status");
            slowConsole.imprimirDevagar("6 - Usar Item");
        } else {
            slowConsole.imprimirDevagar("3 - Fugir");
            slowConsole.imprimirDevagar("4 - Status");
            slowConsole.imprimirDevagar("5 - Usar Item"); // Adicione esta linha
            if (personagem instanceof Warrior || personagem instanceof Paladin) {
                slowConsole.imprimirDevagar("6 - Defender");
            } else if (personagem instanceof Healer) {
                slowConsole.imprimirDevagar("6 - Ressurreição");
                slowConsole.imprimirDevagar("7 - Curar");
            }
            if (personagem instanceof Paladin) {
                slowConsole.imprimirDevagar("7 - Curar");
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
            slowConsole.imprimirDevagar("Você fugiu!");
            return false;
        } else {
            slowConsole.imprimirDevagar("Você tentou fugir, mas não conseguiu!");
            return true;
        }
    }

    private void handleRessurection(Attributes personagem) {
        if (personagem instanceof Healer) {
            ((Healer) personagem).ressurection(personagem);
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

    private Attributes getEnemyTarget(Attributes personagem) {
        if (personagem instanceof Necromancer && !((Necromancer) personagem).getImps().isEmpty()) {
            int targetChoice = random.nextInt(((Necromancer) personagem).getImps().size() + 1);
            if (targetChoice == 0) {
                return personagem;
            } else {
                return ((Necromancer) personagem).getImps().get(targetChoice - 1);
            }
        } else {
            return personagem;
        }
    }
}
