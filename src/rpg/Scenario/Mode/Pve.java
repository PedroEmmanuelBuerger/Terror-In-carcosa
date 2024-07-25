package rpg.Scenario.Mode;

import rpg.Utils.CombatSystem;
import rpg.Utils.SlowConsole;
import rpg.Classes.Attributes;
import rpg.CharacterCreation.CreatePlayer;
import rpg.Events.NonCombatEvent;
import rpg.Events.ManaRecoveryEvent;
import rpg.Events.HealthRecoveryEvent;
import rpg.Events.HealingPotionEvent;
import rpg.Events.RareItemEvent;

import java.util.Random;
import java.util.Scanner;

public class Pve {
    int levelDungeon = 1;
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
        int eventType = random.nextInt(4); // Agora temos 4 tipos de eventos

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
            default:
                slowConsole.imprimirDevagar("Evento não reconhecido.");
                return;
        }

        event.executeEvent(personagem);
    }
}
