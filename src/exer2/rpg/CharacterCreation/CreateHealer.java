package exer2.rpg.CharacterCreation;

import exer2.rpg.Classes.Healer;

import java.util.Scanner;

public class CreateHealer {
    public static Healer createHealer(Scanner scanner) {
        System.out.println("Digite o nome do Curandeiro:");
        String nome = scanner.nextLine();

        System.out.println("Digite o valor da vida inicial do Curandeiro:");
        int vida = scanner.nextInt();

        System.out.println("Digite o valor do ataque do Curandeiro:");
        int ataque = scanner.nextInt();

        System.out.println("Digite o valor do ataque especial do Curandeiro:");
        int especial = scanner.nextInt();

        System.out.println("Digite o valor da mana do Curandeiro:");
        int mana = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        System.out.println("Digite a frase de efeito do Curandeiro:");
        String frase = scanner.nextLine();

        return new Healer(nome, vida, mana, ataque, especial, frase);
    }
}
