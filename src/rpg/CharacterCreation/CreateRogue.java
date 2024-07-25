package rpg.CharacterCreation;

import Utils.SlowConsole;
import rpg.Classes.Rogue;

import java.util.Scanner;

public class CreateRogue {
    public static Rogue createRogue(Scanner scanner) {
        SlowConsole slowConsole = new SlowConsole();
        String nome;
        int vida = 0;
        int ataque = 0;
        int especial = 0;
        String frase = "";
        int maxPoints = 99; // Total máximo de pontos disponíveis

        // Loop para garantir entradas válidas
        boolean entradaValida = false;
        do {
            slowConsole.imprimirDevagar("Digite o nome do Ladrão:");
            nome = scanner.nextLine().trim(); // Remove espaços em branco extras

            // Verifica se o nome contém números
            if (nome.matches(".*\\d.*")) {
                slowConsole.imprimirDevagar("O nome não pode conter números. Digite novamente.");
                continue;
            }

            slowConsole.imprimirDevagar("Digite o valor da vida inicial do Ladrão (máximo " + maxPoints + "):");
            if (scanner.hasNextInt()) {
                vida = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                // Verifica se a vida digitada é válida
                if (vida > maxPoints) {
                    slowConsole.imprimirDevagar("Você excedeu o limite de pontos para a vida. Tente novamente.");
                    continue;
                }

                maxPoints -= vida; // Desconta os pontos da vida de maxPoints
            } else {
                slowConsole.imprimirDevagar("Entrada inválida. Digite um número para a vida.");
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            slowConsole.imprimirDevagar("Digite o valor do ataque do Ladrão (máximo " + maxPoints + "):");
            if (scanner.hasNextInt()) {
                ataque = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                // Verifica se o ataque digitado é válido
                if (ataque > maxPoints) {
                    slowConsole.imprimirDevagar("Você excedeu o limite de pontos para o ataque. Tente novamente.");
                    maxPoints = 99; // Restaura os pontos de vida descontados
                    continue;
                }

                maxPoints -= ataque; // Desconta os pontos do ataque de maxPoints
            } else {
                slowConsole.imprimirDevagar("Entrada inválida. Digite um número para o ataque.");
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            slowConsole.imprimirDevagar("Digite o valor do ataque especial do Ladrão (máximo " + maxPoints + "):");
            if (scanner.hasNextInt()) {
                especial = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                // Verifica se o ataque especial digitado é válido
                if (especial > maxPoints) {
                    slowConsole.imprimirDevagar("Você excedeu o limite de pontos para o ataque especial. Tente novamente.");
                    maxPoints = 99; // Restaura os pontos de ataque descontados
                    continue;
                }

                maxPoints -= especial; // Desconta os pontos do ataque especial de maxPoints
            } else {
                slowConsole.imprimirDevagar("Entrada inválida. Digite um número para o ataque especial.");
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            slowConsole.imprimirDevagar("Digite a frase de efeito do Ladrão:");
            frase = scanner.nextLine().trim(); // Remove espaços em branco extras

            // Verifica se a frase contém números

            // Se todas as entradas forem válidas, sair do loop
            entradaValida = true;

        } while (!entradaValida);

        return new Rogue(nome, vida, ataque, especial, frase);
    }
}
