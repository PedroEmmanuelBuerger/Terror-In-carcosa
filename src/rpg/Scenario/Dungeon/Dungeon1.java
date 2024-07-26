package rpg.Scenario.Dungeon;
import rpg.Utils.SlowConsole;
import rpg.Classes.Attributes;
import rpg.Classes.Healer;
import rpg.Classes.Mage;
import rpg.Classes.Warrior;
import rpg.Monsters.Boss;
import rpg.Monsters.Goblin;
import rpg.Monsters.Zombie;

import java.util.Random;
import java.util.Scanner;

public class Dungeon1 implements Dungeon {
    private Random random = new Random();
    private SlowConsole slowConsole = new SlowConsole();

    public void startCombat(Scanner scanner, Attributes personagem) {
        Random random = new Random();
        int escape = 0;
        SlowConsole slowConsole = new SlowConsole();
        Attributes enemy;

        // Verificar se o personagem está no nível necessário para enfrentar o Boss
        if (personagem.getLevel() >= 5) {
            enemy = new Boss("Ghazkull", 350, 20, 30, "HAHAHAHAHAHA");
        } else {
            // Caso o jogador não esteja no nível adequado, enfrentará um Goblin ou Zombie
            int randomMonster = random.nextInt(2);

            switch (randomMonster) {
                case 0:
                    enemy = new Goblin("Goblin", 35, 5, 17, "Grrrr!");
                    break;
                case 1:
                    enemy = new Zombie("Zombie", 50, 12, 14, "Braaaaains...");
                    break;
                default:
                    enemy = new Goblin("Goblin", 35, 5, 17, "Grrrr!");
                    break;
            }
        }

        // Apresentação inicial do monstro
        slowConsole.imprimirDevagar("Você encontrou um " + enemy.getName() + "!");
        slowConsole.imprimirDevagar(enemy.getName() + ": " + enemy.getQuote());

        // Loop de batalha
        while (personagem.getHealthbar() > 0 && enemy.getHealthbar() > 0) {
            slowConsole.imprimirDevagar("Sua vez de agir:");
            slowConsole.imprimirDevagar("Escolha sua ação:");
            slowConsole.imprimirDevagar("1 - Atacar");
            if (personagem instanceof Mage) {
                slowConsole.imprimirDevagar("2 - Livro de magias");
            } else {
                slowConsole.imprimirDevagar("2 - Ataque Especial");
            }
            slowConsole.imprimirDevagar("3 - Fugir");
            slowConsole.imprimirDevagar("4 - Status");
            if (personagem instanceof Warrior) {
                slowConsole.imprimirDevagar("5 - Defender");
            } else if (personagem instanceof Healer) {
                slowConsole.imprimirDevagar("5 - Curar");
                slowConsole.imprimirDevagar("6 - Ressurreição");
            }

            int acaoJogador;
            if (scanner.hasNextInt()) {
                acaoJogador = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } else {
                slowConsole.imprimirDevagar("Entrada inválida. Você perdeu a vez.");
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            switch (acaoJogador) {
                case 1:
                    personagem.attack(enemy);
                    break;
                case 2:
                    personagem.attackWithSpecial(enemy);
                    break;
                case 3:
                    double escapeChance = 25.00;
                    Double randomSucess = random.nextDouble() * 100.0;
                    if (randomSucess <= escapeChance && !enemy.getName().equals("Ghazkull")) {
                        slowConsole.imprimirDevagar("Você fugiu!");
                        escape = 1;
                    } else {
                        slowConsole.imprimirDevagar("Você tentou fugir, mas não conseguiu!");
                    }
                    break;
                case 4:
                    personagem.getTechnicalInfo();
                    break;
                case 5:
                    if (personagem instanceof Warrior) {
                        ((Warrior) personagem).defend();
                    } else if (personagem instanceof Healer) {
                        ((Healer) personagem).heal(personagem);
                    }
                    break;
                case 6:
                    if (personagem instanceof Healer) {
                        ((Healer) personagem).ressurection(personagem);
                    }
                    break;
                default:
                    slowConsole.imprimirDevagar("Ação inválida. Você perdeu a vez.");
            }

            if (acaoJogador == 3 && escape == 1) {
                escape = 0;
                break;
            }

            if (enemy.getHealthbar() <= 0) {
                if (enemy.getName().equals("Ghazkull")) {
                    slowConsole.imprimirDevagar("Você derrotou o chefão da dungeon! Indo para o próximo nivel...");
                    personagem.setLevelDungeon(personagem.getLevelDungeon() + 1);
                }
                break;
            }

            // Turno do inimigo
            slowConsole.imprimirDevagar("\nTurno de " + enemy.getName() + ":");

            int acaoInimigo = random.nextInt(3);

            switch (acaoInimigo) {
                case 0:
                    enemy.attack(personagem);
                    break;
                case 1:
                    enemy.attackWithSpecial(personagem);
                    break;
                default:
                    slowConsole.imprimirDevagar(enemy.getName() + " está preparando seu ataque!");
                    slowConsole.imprimirDevagar(enemy.getName() + ": " + enemy.getQuote());
            }

            System.out.println();

            if (personagem.getHealthbar() <= 0) {
                slowConsole.imprimirDevagar("Você foi derrotado por " + enemy.getName() + ". Game Over!");
                return;
            }
        }
    }
}
