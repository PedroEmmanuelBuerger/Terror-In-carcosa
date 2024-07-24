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
        int control = 0;// Criar o personagem uma vez

        while (personagem.getHealthbar() > 0) { // Enquanto o personagem estiver vivo
            // Escolher aleatoriamente um monstro para o próximo encontro
            Attributes enemy;
            int randomMonster = random.nextInt(3); // 0: Boss, 1: Goblin, 2: Zombie

            switch (randomMonster) {
                case 0:
                    enemy = new Boss("Ghazkull", 1500, 35, 50, "HAHAHAHAHAHA");
                    break;
                case 1:
                    enemy = new Goblin("Goblin", 200, 20, 30, "Grrrr!");
                    break;
                case 2:
                    enemy = new Zombie("Zombie", 300, 25, 40, "Braaaaains...");
                    break;
                default:
                    enemy = new Goblin("Goblin", 200, 20, 30, "Grrrr!");
                    break;
            }

            // Apresentação inicial do monstro
            if (control == 0) {
                System.out.println("Personagem atual:");
                personagem.getTechnicalInfo();
                System.out.println();
                control = 1;
            }
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
                        System.out.println("Você fugiu para o próximo monstro!");
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

                // Verifica se o jogador escolheu fugir (ação 5)
                if (acaoJogador == 3) {
                    break; // Sai do loop de batalha atual
                }

                // Verifica se o inimigo foi derrotado após a ação do jogador
                if (enemy.getHealthbar() <= 0) {
                    break; // Sai do loop de batalha atual
                }

                // Turno do inimigo
                System.out.println("\nTurno de " + enemy.getName() + ":");
                enemy.attackWithSpecial(personagem);
                System.out.println();

                // Verifica se o jogador foi derrotado após a ação do inimigo
                if (personagem.getHealthbar() <= 0) {
                    System.out.println("Você foi derrotado por " + enemy.getName() + ". Game Over!");
                    return; // Encerra o método startBattle, pois o jogador foi derrotado
                }
            }
        }
    }
}
