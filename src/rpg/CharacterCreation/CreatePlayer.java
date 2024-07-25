package rpg.CharacterCreation;

import Utils.SlowConsole;
import Utils.InputUtils;
import rpg.Classes.*;

import java.util.Scanner;

public class CreatePlayer {

    public static Attributes createPlayer(Scanner scanner) {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("Escolha sua classe:");
        slowConsole.imprimirDevagar("1 - Guerreiro");
        slowConsole.imprimirDevagar("2 - Mago");
        slowConsole.imprimirDevagar("3 - Ladino");
        slowConsole.imprimirDevagar("4 - Curandeiro");

        int escolha;
        Attributes personagem = null;

        do {
            escolha = InputUtils.lerInteiro(scanner, "Escolha inválida. Por favor, escolha uma opção válida.");

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
                    slowConsole.imprimirDevagar("Escolha inválida. Por favor, escolha uma opção válida.");
            }
        } while (personagem == null);

        return personagem;
    }
}
