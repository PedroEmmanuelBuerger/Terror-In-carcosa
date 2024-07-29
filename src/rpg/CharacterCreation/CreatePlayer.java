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
        slowConsole.imprimirDevagar("5 - Necromante(+10 de ataque especial, +5 de mana)");

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
                case 5:
                    personagem = CreateNecromancer.createNecromancer(scanner);
                default:
                    slowConsole.imprimirDevagar("Escolha inválida. Por favor, escolha uma opção válida.");
            }
        } while (personagem == null);
        // Escolha de raça
        slowConsole.imprimirDevagar("Escolha sua raça:");
        slowConsole.imprimirDevagar("1 - Humano (+10 de ataque)");
        slowConsole.imprimirDevagar("2 - Orc (+10 de vida)");
        slowConsole.imprimirDevagar("3 - Elfo (+10 de ataque especial)");
        slowConsole.imprimirDevagar("4 - Anão (+5 de ataque e +5 de vida)");

        int escolhaRace;
        Race race = null;

        do {
            escolhaRace = InputUtils.lerInteiro(scanner, "Escolha inválida. Por favor, escolha uma opção válida.");

            switch (escolhaRace) {
                case 1:
                    race = Race.HUMAN;
                    break;
                case 2:
                    race = Race.ORC;
                    break;
                case 3:
                    race = Race.ELF;
                    break;
                case 4:
                    race = Race.DWARF;
                    break;
                default:
                    slowConsole.imprimirDevagar("Escolha inválida. Por favor, escolha uma opção válida.");
            }
        } while (race == null);

        personagem.setRace(race);
        personagem.applyRaceBonuses(); // Aplica os bônus da raça

        return personagem;
    }
}
