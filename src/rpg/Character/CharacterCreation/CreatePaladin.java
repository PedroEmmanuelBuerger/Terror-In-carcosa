package rpg.Character.CharacterCreation;

import rpg.Utils.SlowConsole;
import rpg.Character.Classes.Paladin;

import java.util.Scanner;

public class CreatePaladin {
    public static Paladin createPaladin(Scanner scanner, int Mode) {
        SlowConsole slowConsole = new SlowConsole();
        String nome;
        int vida = 0;
        int ataque = 0;
        int especial = 0;
        int mana = 0;
        String frase = "";

        boolean entradaValida = false;
        do {
            int tempMaxPoints;
            if (Mode == 3 || Mode == 2) {
                tempMaxPoints = 50;
            } else {
                tempMaxPoints = 99;
            }

            slowConsole.imprimirDevagar("Digite o nome do Paladino:");
            nome = scanner.nextLine().trim();
            if (nome.matches(".*\\d.*")) {
                slowConsole.imprimirDevagar("O nome não pode conter números. Digite novamente.");
                continue;
            }

            slowConsole.imprimirDevagar("Digite o valor da vida inicial do Paladino (máximo " + tempMaxPoints + "):");
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
            } else {
                slowConsole.imprimirDevagar("Entrada inválida. Digite um número para a vida.");
                scanner.nextLine();
                continue;
            }

            slowConsole.imprimirDevagar("Digite o valor do ataque do Paladino (máximo " + tempMaxPoints + "):");
            if (scanner.hasNextInt()) {
                ataque = scanner.nextInt();
                scanner.nextLine();
                if (ataque > tempMaxPoints) {
                    slowConsole.imprimirDevagar("Você excedeu o limite de pontos para o ataque. Tente novamente.");
                    continue;
                }
                tempMaxPoints -= ataque;
                ataque += 5;
            } else {
                slowConsole.imprimirDevagar("Entrada inválida. Digite um número para o ataque.");
                scanner.nextLine();
                continue;
            }

            slowConsole.imprimirDevagar("Digite o valor do ataque especial do Paladino (máximo " + tempMaxPoints + "):");
            if (scanner.hasNextInt()) {
                especial = scanner.nextInt();
                scanner.nextLine();
                if (especial > tempMaxPoints) {
                    slowConsole.imprimirDevagar("Você excedeu o limite de pontos para o ataque especial. Tente novamente.");
                    continue;
                }
                tempMaxPoints -= especial;
                especial += 5;
            } else {
                slowConsole.imprimirDevagar("Entrada inválida. Digite um número para o ataque especial.");
                scanner.nextLine();
                continue;
            }

            slowConsole.imprimirDevagar("Digite o valor da mana do Paladino (máximo " + tempMaxPoints + "):");
            if (scanner.hasNextInt()) {
                mana = scanner.nextInt();
                scanner.nextLine();
                if (mana > tempMaxPoints) {
                    slowConsole.imprimirDevagar("Você excedeu o limite de pontos para a mana. Tente novamente.");
                    continue;
                }
                mana += 5;
            } else {
                slowConsole.imprimirDevagar("Entrada inválida. Digite um número para a mana.");
                scanner.nextLine();
                continue;
            }

            slowConsole.imprimirDevagar("Digite a frase de efeito do Paladino:");
            frase = scanner.nextLine().trim();

            entradaValida = true;
        } while (!entradaValida);
        return new Paladin(nome, vida, mana, ataque, especial, frase);
    }
}
