package Scenario;

import rpg.Classes.Attributes;
import rpg.Classes.Healer;
import rpg.Classes.Warrior;
import rpg.CharacterCreation.CreatePlayer;
import rpg.Monsters.Boss;
import rpg.Monsters.Goblin;
import rpg.Monsters.Zombie;

import java.util.Random;
import java.util.Scanner;

public class Pve {

    public static void startBattle(Scanner scanner) {
        Random random = new Random();
        Attributes personagem = CreatePlayer.createPlayer(scanner);
        personagem.getTechnicalInfo();

        while (personagem.getHealthbar() > 0) { // Enquanto o personagem estiver vivo
            // Escolher aleatoriamente entre combate ou evento não combativo
            int randomEvent = random.nextInt(2); // 0: Combate, 1: Evento não combativo

            if (randomEvent == 0) {
                // Encontro de combate
                startCombat(scanner, personagem);
            } else {
                // Evento não combativo
                nonCombatEvent(personagem);
            }
        }
    }

    private static void startCombat(Scanner scanner, Attributes personagem) {
        Random random = new Random();

        // Escolher aleatoriamente um monstro para o próximo encontro
        Attributes enemy;
        int randomMonster = random.nextInt(3); // 0: Boss, 1: Goblin, 2: Zombie

        switch (randomMonster) {
            case 0:
                enemy = new Boss("Ghazkull", 450, 25, 30, "HAHAHAHAHAHA");
                break;
            case 1:
                enemy = new Goblin("Goblin", 50, 5, 17, "Grrrr!");
                break;
            case 2:
                enemy = new Zombie("Zombie", 70, 12, 14, "Braaaaains...");
                break;
            default:
                enemy = new Goblin("Goblin", 50, 5, 17, "Grrrr!");
                break;
        }

        // Apresentação inicial do monstro
        System.out.println("Você encontrou um " + enemy.getName() + "!");

        // Loop de batalha
        while (personagem.getHealthbar() > 0 && enemy.getHealthbar() > 0) {
            System.out.println("Sua vez de agir:");
            System.out.println("Escolha sua ação:");
            System.out.println("1 - Atacar");
            System.out.println("2 - Ataque Especial");
            System.out.println("3 - Fugir");
            // Opções adicionais para Warrior e Healer
            if (personagem instanceof Warrior) {
                System.out.println("4 - Defender");
            } else if (personagem instanceof Healer) {
                System.out.println("4 - Curar");
                System.out.println("5 - Ressurreição");
            }

            int acaoJogador;
            if (scanner.hasNextInt()) {
                acaoJogador = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } else {
                System.out.println("Entrada inválida. Você perdeu a vez.");
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
                    System.out.println("Você fugiu!");
                    break;
                case 4:
                    if (personagem instanceof Warrior) {
                        ((Warrior) personagem).defend();
                    } else if (personagem instanceof Healer) {
                        ((Healer) personagem).heal(personagem); // Pode ajustar para escolher outro aliado se houver
                    }
                    break;
                case 5:
                    if (personagem instanceof Healer) {
                        ((Healer) personagem).ressurection(personagem); // Pode ajustar para escolher um aliado a ser ressuscitado
                    }
                    break;
                default:
                    System.out.println("Ação inválida. Você perdeu a vez.");
            }

            // Verifica se o jogador escolheu fugir (ação 3)
            if (acaoJogador == 3) {
                break; // Sai do loop de batalha atual
            }

            // Verifica se o inimigo foi derrotado após a ação do jogador
            if (enemy.getHealthbar() <= 0) {
                break; // Sai do loop de batalha atual
            }

            // Turno do inimigo
            System.out.println("\nTurno de " + enemy.getName() + ":");

            int acaoInimigo = random.nextInt(2); // 0 para attack, 1 para attackWithSpecial

            switch (acaoInimigo) {
                case 0:
                    enemy.attack(personagem); // Inimigo ataca o personagem
                    break;
                case 1:
                    enemy.attackWithSpecial(personagem); // Inimigo usa ataque especial contra o personagem
                    break;
                default:
                    System.out.println("Inimigo não fez nada neste turno.");
            }

            System.out.println();

            // Verifica se o jogador foi derrotado após a ação do inimigo
            if (personagem.getHealthbar() <= 0) {
                System.out.println("Você foi derrotado por " + enemy.getName() + ". Game Over!");
                return; // Encerra o método startBattle, pois o jogador foi derrotado
            }
        }
    }

    private static void nonCombatEvent(Attributes personagem) {
        Random random = new Random();
        int eventType = random.nextInt(3); // Escolhe aleatoriamente o tipo de evento não combativo

        switch (eventType) {
            case 0:
                System.out.println("Você encontrou um tesouro escondido! Ganhou 50 de ouro.");
                break;
            case 1:
                System.out.println("Você encontrou uma poção de cura! Ganhou +20 de vida.");
                personagem.setHealthbar(personagem.getHealthbar() + 20);
                break;
            case 2:
                System.out.println("Você encontrou um item raro! Ganhou +5 de ataque.");
                personagem.setAttack(personagem.getAttack() + 5);
                break;
            default:
                System.out.println("Você encontrou um evento não esperado... ganhou +10 de vida!");
                personagem.setHealthbar(personagem.getHealthbar() + 10);
                break;
        }
    }
}
