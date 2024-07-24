package exer2.rpg.CharacterCreation;

import exer2.rpg.Classes.Rogue;

import java.util.Scanner;

public class CreateRogue {
    public static Rogue createRogue(Scanner scanner) {
        System.out.println("Digite o nome do Ladrão:");
        String nome = scanner.nextLine();

        System.out.println("Digite o valor da vida inicial do Ladrão:");
        int vida = scanner.nextInt();

        System.out.println("Digite o valor do ataque do Ladrão:");
        int ataque = scanner.nextInt();

        System.out.println("Digite o valor do ataque especial do Ladrão:");
        int especial = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        System.out.println("Digite a frase de efeito do Ladrão:");
        String frase = scanner.nextLine();

        return new Rogue(nome, vida, ataque, especial, frase);
    }
}
