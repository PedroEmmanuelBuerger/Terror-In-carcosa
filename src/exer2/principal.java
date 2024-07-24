package exer2;

import exer2.rpg.CharacterCreation.CreateHealer;
import exer2.rpg.CharacterCreation.CreateMage;
import exer2.rpg.CharacterCreation.CreateRogue;
import exer2.rpg.CharacterCreation.CreateWarrior;
import exer2.rpg.Classes.*;
import exer2.rpg.Monsters.Boss;

import java.util.Scanner;

public class principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Warrior Baratheon = new Warrior("Baratheon", 130, 15, 15, "GRAAAAAAAAAAA");
        Warrior ned = new Warrior("ned", 90, 20, 35, "HMP");
        Mage tyrion = new Mage("Tyrion", 60, 70, 12, 55, "Knoweldge is power!");
        Rogue sombraios = new Rogue("sombraios", 40, 25, 16, "Sneaking in the shadows...");
        Healer tetriz = new Healer("tetriz", 70, 55, 12, 30, "Heroes Never Die!");
        Boss ghazkull = new Boss("Ghazkull", 1500, 35, 50, "HAHAHAHAHAHA");
        System.out.println("Qual classe criar?");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Ladino");
        System.out.println("4 - Curandeiro");
        // Adicione mais opções conforme necessário

        int escolha = scanner.nextInt();
        scanner.nextLine();
        Attributes personagem = null;
        switch (escolha) {
            case 1:
                personagem = CreateWarrior.criarGuerreiro(scanner);
                break;
            case 2:
                personagem = CreateMage.createMage(scanner);
                break;
            case 3:
                personagem = CreateRogue.createRogue(scanner);
                break;
            case 4:
                personagem = CreateHealer.createHealer(scanner);
                break;
                default:
                System.out.println("Escolha inválida.");
                break;
        }
        System.out.println("Personagem criado:");
        System.out.println("Nome: " + personagem.getName());
        System.out.println("Classe: " + personagem.getClasses());
        System.out.println("Vida inicial: " + personagem.getHealthbar());
    }
}
