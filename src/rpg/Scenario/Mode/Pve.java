package rpg.Scenario.Mode;

import rpg.Character.Classes.Attributes;
import rpg.Character.Classes.Mage;
import rpg.Events.*;
import rpg.Utils.CombatSystem;
import rpg.Utils.SlowConsole;
import rpg.Character.CharacterCreation.CreatePlayer;
import rpg.itens.Specials.SpeelBook;

import java.util.Random;
import java.util.Scanner;

public class Pve {
    private boolean specialEncounterOccurred = false; // Flag para rastrear o evento SpecialEncounter

    public static void startBattle(Scanner scanner) {
        Random random = new Random();
        Attributes personagem = CreatePlayer.createPlayer(scanner);
        personagem.getTechnicalInfo();

        Pve pveInstance = new Pve(); // Crie uma instância de Pve para rastrear eventos
        personagem.setLevelDungeon(1); // Defina o nível da dungeon inicial como 1

        while (personagem.getHealthbar() > 0) {
            int randomEvent = random.nextInt(12); // Atualizado para 12 eventos possíveis

            if (randomEvent < 3) { // 3/12 chance para combate (ajustado para 5 eventos de combate possíveis)
                // Encontro de combate
                CombatSystem.startCombat(scanner, personagem);
                // Após o combate, atualize o nível da dungeon se necessário
            } else {
                // Evento não combativo
                pveInstance.nonCombatEvent(personagem);
            }
        }
    }

    private void nonCombatEvent(Attributes personagem) {
        SlowConsole slowConsole = new SlowConsole();
        Random random = new Random();
        int eventType = random.nextInt(12); // Atualizado para 16 eventos possíveis (dá mais variação)

        NonCombatEvent event = null;
        switch (eventType) {
            case 0:
                if (personagem instanceof Mage mage) {
                    if (!specialEncounterOccurred) {
                        SpeelBook speelBookactual = mage.getSpeelBook(); // Verifica se o evento já ocorreu
                        event = new SpecialEncounter(speelBookactual);
                        specialEncounterOccurred = true; // Marca o evento como ocorrido
                    }
                } else {
                    SpeelBook speelBook = new SpeelBook(); // Crie o SpeelBook aqui para que ele possa ser usado nos eventos
                    if (!specialEncounterOccurred) { // Verifica se o evento já ocorreu
                        event = new SpecialEncounter(speelBook);
                        specialEncounterOccurred = true; // Marca o evento como ocorrido
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
                event = new FoundItem(); // Adiciona o novo evento de encontrar item
                break;
            case 8:
                event = new FindGoldEvent(random.nextInt(101) + 50); // Encontrar entre 50 e 150 de ouro
                break;
            case 9:
                event = new Shooper(); // Adiciona o evento de loja
                break;
            // Menos probabilidade para os eventos mais raros
            case 10:
                event = new NewWeapon(); // Adiciona o evento de arma
                break;
            case 11:
                event = new Pause(); // Evento de pausa
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
