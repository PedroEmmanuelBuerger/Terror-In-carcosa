package rpg.Scenario.Mode;

import rpg.Classes.Attributes;
import rpg.Classes.Mage;
import rpg.Events.*;
import rpg.Utils.CombatSystem;
import rpg.Utils.SlowConsole;
import rpg.CharacterCreation.CreatePlayer;
import rpg.itens.Specials.SpeelBook;

import java.util.Random;
import java.util.Scanner;

public class Pve {
    private int levelDungeon = 1;
    private boolean specialEncounterOccurred = false; // Flag para rastrear o evento SpecialEncounter

    public static void startBattle(Scanner scanner) {
        Random random = new Random();
        Attributes personagem = CreatePlayer.createPlayer(scanner);
        personagem.getTechnicalInfo();

        Pve pveInstance = new Pve(); // Crie uma instância de Pve para rastrear eventos
        personagem.setLevelDungeon(1); // Defina o nível da dungeon inicial como 1

        while (personagem.getHealthbar() > 0) {
            int randomEvent = random.nextInt(5); // Ajustado para 5 eventos possíveis

            if (randomEvent < 2) { // 2/5 chance para combate
                // Encontro de combate
                CombatSystem.startCombat(scanner, personagem);
                // Após o combate, atualize o nível da dungeon se necessário
                pveInstance.levelDungeon = personagem.getLevelDungeon();
            } else {
                // Evento não combativo
                pveInstance.nonCombatEvent(personagem);
            }
        }
    }

    public void setLevelDungeon(int levelDungeon) {
        this.levelDungeon = levelDungeon;
    }

    public void nonCombatEvent(Attributes personagem) {
        SlowConsole slowConsole = new SlowConsole();
        Random random = new Random();
        int eventType = random.nextInt(12); // Atualizado para 12 eventos possíveis

        NonCombatEvent event = null;
        switch (eventType) {
            case 0:
                // Código existente...
                break;
            case 1:
                // Código existente...
                break;
            case 2:
                // Código existente...
                break;
            case 3:
                // Código existente...
                break;
            case 4:
                // Código existente...
                break;
            case 5:
                // Código existente...
                break;
            case 6:
                // Código existente...
                break;
            case 7:
                // Código existente...
                break;
            case 8:
                // Código existente...
                break;
            case 9:
                event = new Pause();
                break;
            case 10:
                // Código existente...
                break;
            case 11:
                event = new Shooper(); // Adiciona o evento de loja
                break;
            default:
                slowConsole.imprimirDevagar("Evento não reconhecido.");
                return;
        }

        if (event != null) {
            event.executeEvent(personagem);
        }
    }
}
