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

        boolean entradaValida = false;
        do {
            int tempMaxPoints = 99;

            slowConsole.imprimirDevagar("Digite o nome do Guerreiro:");
            nome = scanner.nextLine().trim();

            if (nome.matches(".*\\d.*")) {
                slowConsole.imprimirDevagar("O nome não pode conter números. Digite novamente.");
                continue;
            }

            slowConsole.imprimirDevagar("Digite o valor da vida inicial do Guerreiro (máximo " + tempMaxPoints + "):");
            if (scanner.hasNextInt()) {
                vida = scanner.nextInt();
                scanner.nextLine();

                if (vida > tempMaxPoints) {
                    slowConsole.imprimirDevagar("Você excedeu o limite de pontos para a vida. Tente novamente.");
                    continue;
                }
                if (vida == 0) {
                    slowConsole.imprimirDevagar("Vida inicial não pode ser 0");
                    continue;
                }
                tempMaxPoints -= vida;
                vida += 15;
            } else {
                slowConsole.imprimirDevagar("Entrada inválida. Digite um número para a vida.");
                scanner.nextLine();
                continue;
            }

            slowConsole.imprimirDevagar("Digite o valor do ataque do Guerreiro (máximo " + tempMaxPoints + "):");
            if (scanner.hasNextInt()) {
                ataque = scanner.nextInt();
                scanner.nextLine();

                if (ataque > tempMaxPoints) {
                    slowConsole.imprimirDevagar("Você excedeu o limite de pontos para o ataque. Tente novamente.");
                    continue;
                }

                tempMaxPoints -= ataque;
            } else {
                slowConsole.imprimirDevagar("Entrada inválida. Digite um número para o ataque.");
                scanner.nextLine();
                continue;
            }

            slowConsole.imprimirDevagar("Digite o valor do ataque especial do Guerreiro (máximo " + tempMaxPoints + "):");
            if (scanner.hasNextInt()) {
                especial = scanner.nextInt();
                scanner.nextLine();

                if (especial > tempMaxPoints) {
                    slowConsole.imprimirDevagar("Você excedeu o limite de pontos para o ataque especial. Tente novamente.");
                    continue;
                }

                tempMaxPoints -= especial;
            } else {
                slowConsole.imprimirDevagar("Entrada inválida. Digite um número para o ataque especial.");
                scanner.nextLine();
                continue;
            }

            slowConsole.imprimirDevagar("Digite a frase de efeito do Guerreiro:");
            frase = scanner.nextLine().trim();

            entradaValida = true;

        } while (!entradaValida);

        return new Warrior(nome, vida, ataque, especial, frase);
    }
}
