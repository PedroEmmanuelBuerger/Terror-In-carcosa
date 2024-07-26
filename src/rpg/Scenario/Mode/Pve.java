package rpg.Scenario.Mode;

import rpg.Events.*;
import rpg.Utils.CombatSystem;
import rpg.Utils.SlowConsole;
import rpg.Classes.Attributes;
import rpg.CharacterCreation.CreatePlayer;

import java.util.Random;
import java.util.Scanner;

public class Pve {
    private int levelDungeon = 1;

    public static void startBattle(Scanner scanner) {
        Random random = new Random();
        Attributes personagem = CreatePlayer.createPlayer(scanner);
        personagem.getTechnicalInfo();

        while (personagem.getHealthbar() > 0) {
            int randomEvent = random.nextInt(2);

            if (randomEvent == 0) {
                // Encontro de combate
                CombatSystem.startCombat(scanner, personagem);
            } else {
                // Evento não combativo
                nonCombatEvent(personagem);
            }
        }
    }

    public void setLevelDungeon(int levelDungeon) {
        this.levelDungeon = levelDungeon;
    }

    private static void nonCombatEvent(Attributes personagem) {
        SlowConsole slowConsole = new SlowConsole();
        Random random = new Random();
        int eventType = random.nextInt(6); // Agora temos 6 tipos de eventos

        NonCombatEvent event;
        switch (eventType) {
            case 0:
                event = new ManaRecoveryEvent();
                break;
            case 1:
                event = new HealthRecoveryEvent();
                break;
            case 2:
                event = new HealingPotionEvent();
                break;
            case 3:
                event = new RareItemEvent();
                break;
            case 4:
                event = new MagicItem();
                break;
            case 5:
                event = new OldRune();
                break;
            default:
                slowConsole.imprimirDevagar("Evento não reconhecido.");
                return;
        }

        event.executeEvent(personagem);
    }
}
