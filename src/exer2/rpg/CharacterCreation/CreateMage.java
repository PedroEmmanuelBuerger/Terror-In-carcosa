package exer2.rpg.CharacterCreation;

import exer2.rpg.Classes.Mage;

import java.util.Scanner;

public class CreateMage {
    public static Mage createMage(Scanner scanner) {
        String nome;
        int vida = 0;
        int ataque = 0;
        int especial = 0;
        int mana = 0;
        String frase = "";

        // Loop para garantir entradas válidas
        boolean entradaValida = false;
        do {
            System.out.println("Digite o nome do Mago:");
            nome = scanner.nextLine().trim(); // Remove espaços em branco extras

            // Verifica se o nome contém números
            if (nome.matches(".*\\d.*")) {
                System.out.println("O nome não pode conter números. Digite novamente.");
                continue;
            }

            System.out.println("Digite o valor da vida inicial do Mago:");
            if (scanner.hasNextInt()) {
                vida = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } else {
                System.out.println("Entrada inválida. Digite um número para a vida.");
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            System.out.println("Digite o valor do ataque do Mago:");
            if (scanner.hasNextInt()) {
                ataque = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } else {
                System.out.println("Entrada inválida. Digite um número para o ataque.");
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            System.out.println("Digite o valor do ataque especial do Mago:");
            if (scanner.hasNextInt()) {
                especial = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } else {
                System.out.println("Entrada inválida. Digite um número para o ataque especial.");
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            System.out.println("Digite o valor da mana do Mago:");
            if (scanner.hasNextInt()) {
                mana = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } else {
                System.out.println("Entrada inválida. Digite um número para a mana.");
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            System.out.println("Digite a frase de efeito do Mago:");
            frase = scanner.nextLine().trim(); // Remove espaços em branco extras

            // Verifica se a frase contém números
            if (frase.matches(".*\\d.*")) {
                System.out.println("A frase não pode conter números. Digite novamente.");
                continue;
            }

            // Se todas as entradas forem válidas, sair do loop
            entradaValida = true;

        } while (!entradaValida);

        return new Mage(nome, vida, mana, ataque, especial, frase);
    }
}
