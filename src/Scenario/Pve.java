package Scenario;

import exer2.rpg.CharacterCreation.CreatePlayer;
import exer2.rpg.Classes.Attributes;
import exer2.rpg.Classes.Healer;
import exer2.rpg.Classes.Warrior;
import exer2.rpg.Monsters.Boss;

import java.util.Scanner;

public class Pve {

    public static void startBattle(Scanner scanner) {
        Boss boss = new Boss("Ghazkull", 1500, 35, 50, "HAHAHAHAHAHA");

        // Apresentação inicial
        System.out.println("Bem-vindo à batalha contra " + boss.getName() + "!");
        Attributes personagem = CreatePlayer.createPlayer(scanner);

        System.out.println("Personagem criado:");
        personagem.GetTecnicalInfo();
        System.out.println();

        // Loop de batalha
        while (personagem.getHealthbar() > 0 && boss.getHealthbar() > 0) {
            System.out.println("Sua vez de agir:");
            System.out.println("Escolha sua ação:");
            System.out.println("1 - Atacar");
            System.out.println("2 - Ataque Especial");

            // Opções adicionais para Warrior e Healer
            if (personagem instanceof Warrior) {
                System.out.println("3 - Defender");
            } else if (personagem instanceof Healer) {
                System.out.println("3 - Curar");
                System.out.println("4 - Ressurreição");
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
                    personagem.attack(boss);
                    break;
                case 2:
                    personagem.attackWithSpecial(boss);
                    break;
                case 3:
                    if (personagem instanceof Warrior) {
                        ((Warrior) personagem).defend();
                    } else if (personagem instanceof Healer) {
                        ((Healer) personagem).heal(personagem); // Pode ajustar para escolher outro aliado se houver
                    }
                    break;
                case 4:
                    if (personagem instanceof Healer) {
                        ((Healer) personagem).ressurection(personagem); // Pode ajustar para escolher um aliado a ser ressuscitado
                    }
                    break;
                default:
                    System.out.println("Ação inválida. Você perdeu a vez.");
            }

            // Verifica se o boss foi derrotado após a ação do jogador
            if (boss.getHealthbar() <= 0) {
                System.out.println("Parabéns! Você derrotou " + boss.getName() + "!");
                break;
            }

            // Turno do boss
            System.out.println("\nTurno de " + boss.getName() + ":");
            boss.attackWithSpecial(personagem);
            System.out.println();

            // Verifica se o jogador foi derrotado após a ação do boss
            if (personagem.getHealthbar() <= 0) {
                System.out.println("Você foi derrotado por " + boss.getName() + ". Game Over!");
                break;
            }
        }
    }
}
