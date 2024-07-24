package exer2.rpg.CharacterCreation;

import exer2.rpg.Classes.Mage;

import java.util.Scanner;

public class CreateMage {
    public static Mage createMage(Scanner scanner) {
        System.out.println("Digite o nome do Mago:");
        String nome = scanner.nextLine();

        System.out.println("Digite o valor da vida inicial do Mago:");
        int vida = scanner.nextInt();

        System.out.println("Digite o valor do ataque do Mago:");
        int ataque = scanner.nextInt();

        System.out.println("Digite o valor do ataque especial do Mago:");
        int especial = scanner.nextInt();

        System.out.println("Digite o valor da mana do Mago:");
        int mana = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        System.out.println("Digite a frase de efeito do Mago:");
        String frase = scanner.nextLine();

        return new Mage(nome, vida, mana, ataque, especial, frase);
    }
}
