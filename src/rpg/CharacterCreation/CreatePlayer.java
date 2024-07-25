package rpg.CharacterCreation;

import rpg.Utils.SlowConsole;
import rpg.Utils.InputUtils;
import rpg.Classes.*;

import java.util.Scanner;

public class CreatePlayer {

    public static Attributes createPlayer(Scanner scanner) {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("Escolha sua classe:");
        slowConsole.imprimirDevagar("1 - Guerreiro(+15 de vida)");
        slowConsole.imprimirDevagar("2 - Mago (+15 de mana)");
        slowConsole.imprimirDevagar("3 - Ladino(+15 de ataque)");
        slowConsole.imprimirDevagar("4 - Curandeiro(+5 de vida, +5 de mana, +5 de ataque especial)");

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
