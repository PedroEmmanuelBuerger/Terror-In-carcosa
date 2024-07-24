package exer2.rpg.CharacterCreation;

import exer2.rpg.Classes.Warrior;

import java.util.Scanner;

public class CreateWarrior {
    public static Warrior criarGuerreiro(Scanner scanner) {
        System.out.println("Digite o nome do Guerreiro:");
        String nome = scanner.nextLine();

        System.out.println("Digite o valor da vida inicial do Guerreiro:");
        int vida = scanner.nextInt();

        System.out.println("Digite o valor do ataque do Guerreiro:");
        int ataque = scanner.nextInt();

        System.out.println("Digite o valor do ataque especial do Guerreiro:");
        int especial = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        System.out.println("Digite a frase de efeito do Guerreiro:");
        String frase = scanner.nextLine();

        return new Warrior(nome, vida, ataque, especial, frase);
    }
}
