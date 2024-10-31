package rpg.Mode.Campaign;

import rpg.Mode.Campaign.Events.*;
import rpg.Character.Classes.Attributes;
import rpg.Character.Classes.Mage;
import rpg.Mode.Campaign.Dungeon.CombatSystem;
import rpg.Utils.SlowConsole;
import rpg.Character.CharacterCreation.CreatePlayer;
import rpg.itens.Specials.SpeelBook;

import java.util.Random;
import java.util.Scanner;

public class Pve {
    private boolean specialEncounterOccurred = false;

    public static void startBattle() {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        Attributes personagem = CreatePlayer.createPlayer(scanner,1);
        personagem.getTechnicalInfo();

        Pve pveInstance = new Pve();
        personagem.setLevelDungeon(1);

        SlowConsole slowConsole = new SlowConsole();

        while (personagem.getHealthbar() > 0) {
            slowConsole.imprimirDevagar("Mova-se![W,A,S,D]");
            String key = scanner.nextLine().toLowerCase().trim();

            if (key.equals("w") || key.equals("a") || key.equals("s") || key.equals("d")) {
                int randomEvent = random.nextInt(13);

                if (randomEvent < 6) {
                    CombatSystem.startCombat(scanner, personagem);
                } else {
                    pveInstance.nonCombatEvent(personagem);
                }
            }
        }
    }

    private void nonCombatEvent(Attributes personagem) {
        Random random = new Random();
        int eventType = random.nextInt(18);

        NonCombatEvent event = null;
        switch (eventType) {
            case 0:
                if (personagem instanceof Mage mage) {
                    if (!specialEncounterOccurred) {
                        SpeelBook speelBookactual = mage.getSpeelBook();
                        event = new SpecialEncounter(speelBookactual);
                        specialEncounterOccurred = true;
                    }
                } else {
                    SpeelBook speelBook = new SpeelBook();
                    if (!specialEncounterOccurred) {
                        event = new SpecialEncounter(speelBook);
                        specialEncounterOccurred = true;
                    }
                }
                break;
            case 1:
               event = new ManaRecoveryEvent();
                break;
            case 2:
                event = new HealthRecoveryEvent();
                break;
            case 3:
                event = new HealingPotionEvent();
                break;
            case 4:
                event = new RareItemEvent();
                break;
            case 5:
                event = new MagicItem();
                break;
            case 6:
                event = new OldRune();
                break;
            case 7:
                event = new FoundItem();
                break;
            case 8:
                event = new FindGoldEvent(random.nextInt(101) + 50);
                break;
            case 9:
                event = new Shooper();
                break;
            case 10:
                event = new NewWeapon();
                break;
            case 11:
                event = new Pause();
                break;
            case 12:
                event = new Armer();
                break;
            case 13:
                event = new NewArmor();
                break;
            case 14:
                event = new ArmorShopper();
                break;
            case 15:
                event = new SanityRecovery();
                break;
            default:
                new Pause();
                return;
        }

        if (event != null) {
            event.executeEvent(personagem);
        }
    }
}
