package rpg.Character.CharacterCreation;

import rpg.Utils.SlowConsole;
import rpg.Character.Classes.Warrior;

import java.util.Scanner;

public class CreateWarrior {
    public static Warrior createWarrior(Scanner scanner) {
        SlowConsole slowConsole = new SlowConsole();
        String nome;
        int vida = 0;
        int ataque = 0;
        int especial = 0;
        String frase = "";

        // Loop para garantir entradas válidas
        boolean entradaValida = false;
        do {
            // Reinicializa maxPoints a cada nova tentativa de entrada
            int tempMaxPoints = 99;

            slowConsole.imprimirDevagar("Digite o nome do Guerreiro:");
            nome = scanner.nextLine().trim(); // Remove espaços em branco extras

            // Verifica se o nome contém números
            if (nome.matches(".*\\d.*")) {
                slowConsole.imprimirDevagar("O nome não pode conter números. Digite novamente.");
                continue;
            }

            // Entrada para vida
            slowConsole.imprimirDevagar("Digite o valor da vida inicial do Guerreiro (máximo " + tempMaxPoints + "):");
            if (scanner.hasNextInt()) {
                vida = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                // Verifica se a vida digitada é válida
                if (vida > tempMaxPoints) {
                    slowConsole.imprimirDevagar("Você excedeu o limite de pontos para a vida. Tente novamente.");
                    continue;
                }
                if (vida == 0) {
                    slowConsole.imprimirDevagar("Vida inicial não pode ser 0");
                    continue;
                }
                tempMaxPoints -= vida; // Atualiza tempMaxPoints após a entrada válida
                vida += 15; // Adiciona pontos extras à vida
            } else {
                slowConsole.imprimirDevagar("Entrada inválida. Digite um número para a vida.");
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            // Entrada para ataque
            slowConsole.imprimirDevagar("Digite o valor do ataque do Guerreiro (máximo " + tempMaxPoints + "):");
            if (scanner.hasNextInt()) {
                ataque = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                // Verifica se o ataque digitado é válido
                if (ataque > tempMaxPoints) {
                    slowConsole.imprimirDevagar("Você excedeu o limite de pontos para o ataque. Tente novamente.");
                    continue;
                }

                tempMaxPoints -= ataque; // Atualiza tempMaxPoints após a entrada válida
            } else {
                slowConsole.imprimirDevagar("Entrada inválida. Digite um número para o ataque.");
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            // Entrada para ataque especial
            slowConsole.imprimirDevagar("Digite o valor do ataque especial do Guerreiro (máximo " + tempMaxPoints + "):");
            if (scanner.hasNextInt()) {
                especial = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                // Verifica se o ataque especial digitado é válido
                if (especial > tempMaxPoints) {
                    slowConsole.imprimirDevagar("Você excedeu o limite de pontos para o ataque especial. Tente novamente.");
                    continue;
                }

                tempMaxPoints -= especial; // Atualiza tempMaxPoints após a entrada válida
            } else {
                slowConsole.imprimirDevagar("Entrada inválida. Digite um número para o ataque especial.");
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            slowConsole.imprimirDevagar("Digite a frase de efeito do Guerreiro:");
            frase = scanner.nextLine().trim(); // Remove espaços em branco extras

            // Se todas as entradas forem válidas, sair do loop
            entradaValida = true;

        } while (!entradaValida);

        return new Warrior(nome, vida, ataque, especial, frase);
    }
}
