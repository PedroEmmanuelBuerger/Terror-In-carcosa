package rpg.Mode;

import rpg.Character.Classes.Attributes;
import rpg.Character.CharacterCreation.CreatePlayer;
import rpg.Character.Classes.Mage;
import rpg.Mode.Campaign.Events.*;
import rpg.Mode.Coops.Dugeon.Dungeon1Coop;
import rpg.Mode.Coops.Dugeon.DungeonCoop;
import rpg.Utils.SlowConsole;
import rpg.itens.Specials.SpeelBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Coop {
    private boolean specialEncounterOccurred = false;

    public static void startCoop() {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        List<Attributes> jogadores = new ArrayList<>();

        // Criação de múltiplos jogadores
        for (int i = 0; i < 2; i++) {  // Pode alterar o número de jogadores conforme necessário
            System.out.println("Criação do Jogador " + (i + 1) + ":");
            Attributes jogador = CreatePlayer.createPlayer(scanner);
            jogador.getTechnicalInfo();
            jogadores.add(jogador);
        }

        Coop coopInstance = new Coop();

        for (Attributes jogador : jogadores) {
            jogador.setLevelDungeon(1);
        }

        SlowConsole slowConsole = new SlowConsole();

        while (jogadores.stream().anyMatch(j -> j.getHealthbar() > 0)) {
            slowConsole.imprimirDevagar("Movam-se![W,A,S,D]");
            String key = scanner.nextLine().toLowerCase().trim();

            if (key.equals("w") || key.equals("a") || key.equals("s") || key.equals("d")) {
                int randomEvent = random.nextInt(12);

                if (randomEvent < 6) {
                    coopInstance.startCombat(scanner, jogadores);
                } else {
                    for (Attributes jogador : jogadores) {
                        coopInstance.nonCombatEvent(jogador);
                    }
                }
            }
        }
    }

    private void startCombat(Scanner scanner, List<Attributes> jogadores) {
        DungeonCoop dungeon = createDungeon(jogadores);
        dungeon.startCombat(scanner, jogadores);
    }

    private DungeonCoop createDungeon(List<Attributes> jogadores) {
        int levelDungeon = jogadores.get(0).getLevelDungeon();
        for (Attributes jogador : jogadores) {
            if (jogador.getLevelDungeon() != levelDungeon) {
                throw new IllegalStateException("Todos os jogadores devem estar no mesmo nível de dungeon.");
            }
        }

        return switch (levelDungeon) {
            case 1 -> new Dungeon1Coop();
            default -> throw new IllegalStateException("Dungeon não encontrada para o nível: " + levelDungeon);
        };
    }

    private void nonCombatEvent(Attributes jogador) {
        Random random = new Random();
        int eventType = random.nextInt(15);

        NonCombatEvent event = null;
        switch (eventType) {
            case 0:
                if (jogador instanceof Mage mage) {
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
            default:
                new Pause();
                return;
        }

        if (event != null) {
            event.executeEvent(jogador);
        }
    }
}
