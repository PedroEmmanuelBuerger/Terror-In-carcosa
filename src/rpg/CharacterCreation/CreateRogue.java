package rpg.CharacterCreation;

import rpg.Classes.Rogue;

import java.util.Scanner;

public class CreateRogue {
    public static Rogue createRogue(Scanner scanner) {
        String nome;
        int vida = 0;
        int ataque = 0;
        int especial = 0;
        String frase = "";

        // Loop para garantir entradas válidas
        boolean entradaValida = false;
        do {
            System.out.println("Digite o nome do Ladrão:");
            nome = scanner.nextLine().trim(); // Remove espaços em branco extras

            // Verifica se o nome contém números
            if (nome.matches(".*\\d.*")) {
                System.out.println("O nome não pode conter números. Digite novamente.");
                continue;
            }

            System.out.println("Digite o valor da vida inicial do Ladrão:");
            if (scanner.hasNextInt()) {
                vida = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } else {
                System.out.println("Entrada inválida. Digite um número para a vida.");
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            System.out.println("Digite o valor do ataque do Ladrão:");
            if (scanner.hasNextInt()) {
                ataque = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } else {
                System.out.println("Entrada inválida. Digite um número para o ataque.");
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            System.out.println("Digite o valor do ataque especial do Ladrão:");
            if (scanner.hasNextInt()) {
                especial = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } else {
                System.out.println("Entrada inválida. Digite um número para o ataque especial.");
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            System.out.println("Digite a frase de efeito do Ladrão (sem números):");
            frase = scanner.nextLine().trim(); // Remove espaços em branco extras

            // Verifica se a frase contém números
            if (frase.matches(".*\\d.*")) {
                System.out.println("A frase não pode conter números. Digite novamente.");
                continue;
            }

            // Se todas as entradas forem válidas, sair do loop
            entradaValida = true;

        } while (!entradaValida);

        return new Rogue(nome, vida, ataque, especial, frase);
    }
}
