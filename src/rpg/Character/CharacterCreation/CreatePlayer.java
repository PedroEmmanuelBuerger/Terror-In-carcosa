package rpg.Character.CharacterCreation;

import rpg.Utils.Messages.Chose;
import rpg.Utils.SlowConsole;
import rpg.Utils.InputUtils;
import rpg.Character.Classes.*;

import java.util.Scanner;

public class CreatePlayer {

    public static Attributes createPlayer(Scanner scanner) {
        SlowConsole slowConsole = new SlowConsole();
        Attributes personagem = null;

        // Escolha de classe
        slowConsole.imprimirDevagar("Escolha sua classe:");
        slowConsole.imprimirDevagar("1 - Guerreiro do Abismo (+15 de vida)");
        slowConsole.imprimirDevagar("2 - Mago dos Mistérios Cósmicos (+15 de mana)");
        slowConsole.imprimirDevagar("3 - Ladrão das Sombras (+15 de ataque)");
        slowConsole.imprimirDevagar("4 - Curandeiro das Entidades (+5 de vida, +5 de mana, +5 de ataque especial)");
        slowConsole.imprimirDevagar("5 - Necromante das Profundezas (+10 de ataque especial, +5 de mana)");

        int escolha;
        do {
            escolha = InputUtils.lerInteiro(scanner, "Escolha inválida. Por favor, escolha uma opção válida.");
            switch (escolha) {
                case 1:
                    Chose.ChooseWarrior(); // Mensagem para Guerreiro
                    personagem = CreateWarrior.createWarrior(scanner);
                    break;
                case 2:
                    Chose.ChooseMage(); // Mensagem para Mago
                    personagem = CreateMage.createMage(scanner);
                    break;
                case 3:
                    Chose.ChooseRogue(); // Mensagem para Ladino
                    personagem = CreateRogue.createRogue(scanner);
                    break;
                case 4:
                    Chose.ChooseHealer(); // Mensagem para Curandeiro
                    personagem = CreateHealer.createHealer(scanner);
                    break;
                case 5:
                    Chose.ChooseNecromancer(); // Mensagem para Necromante
                    personagem = CreateNecromancer.createNecromancer(scanner);
                    break;
                default:
                    slowConsole.imprimirDevagar("Escolha inválida. Por favor, escolha uma opção válida.");
            }
        } while (personagem == null);

        // Escolha de raça
        slowConsole.imprimirDevagar("Escolha sua raça:");
        slowConsole.imprimirDevagar("1 - Humano do Vórtice (+10 de ataque)");
        slowConsole.imprimirDevagar("2 - Orc do Abismo (+10 de vida)");
        slowConsole.imprimirDevagar("3 - Elfo do Crepúsculo (+10 de ataque especial)");
        slowConsole.imprimirDevagar("4 - Anão do Abismo (+5 de ataque e +5 de vida)");

        int escolhaRace;
        Race race = null;

        do {
            escolhaRace = InputUtils.lerInteiro(scanner, "Escolha inválida. Por favor, escolha uma opção válida.");

            switch (escolhaRace) {
                case 1:
                    race = Race.HUMAN;
                    Chose.ChooseHuman(); // Mensagem para Humano
                    break;
                case 2:
                    race = Race.ORC;
                    Chose.ChooseOrc(); // Mensagem para Orc
                    break;
                case 3:
                    race = Race.ELF;
                    Chose.ChooseElf(); // Mensagem para Elfo
                    break;
                case 4:
                    race = Race.DWARF;
                    Chose.ChooseDwarf(); // Mensagem para Anão
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
