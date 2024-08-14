package rpg.Character.CharacterCreation;

import rpg.Utils.Messages.Chose;
import rpg.Utils.SlowConsole;
import rpg.Utils.InputUtils;
import rpg.Character.Classes.*;
import rpg.itens.Armors.Rags;

import java.util.Scanner;

public class CreatePlayer {

    public static Attributes createPlayer(Scanner scanner, int mode) {
        SlowConsole slowConsole = new SlowConsole();
        Attributes personagem = null;

        slowConsole.imprimirDevagar("Escolha sua classe:");
        slowConsole.imprimirDevagar("1 - Guerreiro do Abismo (+15 de vida)");
        slowConsole.imprimirDevagar("2 - Mago dos Mistérios Cósmicos (+15 de mana)");
        slowConsole.imprimirDevagar("3 - Ladrão das Sombras (+15 de ataque)");
        slowConsole.imprimirDevagar("4 - Curandeiro das Entidades (+5 de vida, +5 de mana, +5 de ataque especial)");
        slowConsole.imprimirDevagar("5 - Necromante das Profundezas (+5 de ataque especial, +5 de mana, +5 de mente)");
        slowConsole.imprimirDevagar("6 - Paladino do do Desconhecido (+5 de ataque, + 5 de mana, +5 de ataque especial");

        int escolha;
        do {
            escolha = InputUtils.lerInteiro(scanner, "Escolha inválida. Por favor, escolha uma opção válida.");
            switch (escolha) {
                case 1:
                    Chose.ChooseWarrior();
                    personagem = CreateWarrior.createWarrior(scanner, mode);
                    break;
                case 2:
                    Chose.ChooseMage();
                    personagem = CreateMage.createMage(scanner, mode);
                    break;
                case 3:
                    Chose.ChooseRogue();
                    personagem = CreateRogue.createRogue(scanner, mode);
                    break;
                case 4:
                    Chose.ChooseHealer();
                    personagem = CreateHealer.createHealer(scanner, mode);
                    break;
                case 5:
                    Chose.ChooseNecromancer();
                    personagem = CreateNecromancer.createNecromancer(scanner, mode);
                    personagem.setMind(personagem.getMind() + 5);
                    break;
                case 6:
                    Chose.ChoosePaladin();
                    personagem = CreatePaladin.createPaladin(scanner, mode);
                default:
                    slowConsole.imprimirDevagar("Escolha inválida. Por favor, escolha uma opção válida.");
            }
        } while (personagem == null);

        slowConsole.imprimirDevagar("Escolha sua raça:");
        slowConsole.imprimirDevagar("1 - Humano do Vórtice (+5 de ataque, + 5 de mente)");
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
                    Chose.ChooseHuman();
                    break;
                case 2:
                    race = Race.ORC;
                    Chose.ChooseOrc();
                    break;
                case 3:
                    race = Race.ELF;
                    Chose.ChooseElf();
                    break;
                case 4:
                    race = Race.DWARF;
                    Chose.ChooseDwarf();
                    break;
                default:
                    slowConsole.imprimirDevagar("Escolha inválida. Por favor, escolha uma opção válida.");
            }
        } while (race == null);

        personagem.setRace(race);
        personagem.applyRaceBonuses();
        if (personagem.getRace() == Race.HUMAN) {
            personagem.setMind(personagem.getMind() + 5);
        }
        Rags rag = new Rags();
        personagem.setArmor(rag);
        return personagem;
    }
}
