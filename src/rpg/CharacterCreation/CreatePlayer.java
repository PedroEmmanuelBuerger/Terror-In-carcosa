package rpg.CharacterCreation;

import rpg.Classes.*;

import java.util.Scanner;

public class CreatePlayer {

    public static Attributes createPlayer(Scanner scanner) {
        System.out.println("Escolha sua classe:");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Ladino");
        System.out.println("4 - Curandeiro");
        int escolha;
        Attributes personagem = null;

        do {
            escolha = lerInteiro(scanner, "Escolha inválida. Por favor, escolha uma opção válida.");

            switch (escolha) {
                case 1:
                    personagem = CreateWarrior.createWarrior(scanner);
                    break;
                case 2:
                    personagem = CreateMage.createMage(scanner);
                    break;
                case 3:
                    personagem = CreateRogue.createRogue(scanner);
                    break;
                case 4:
                    personagem = CreateHealer.createHealer(scanner);
                    break;
                default:
                    System.out.println("Escolha inválida. Por favor, escolha uma opção válida.");
            }
        } while (personagem == null);

        return personagem;
    }

    private static int lerInteiro(Scanner scanner, String mensagemErro) {
        while (!scanner.hasNextInt()) {
            System.out.println(mensagemErro);
            scanner.next(); // Limpa a entrada inválida
        }
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha pendente
        return escolha;
    }
}
