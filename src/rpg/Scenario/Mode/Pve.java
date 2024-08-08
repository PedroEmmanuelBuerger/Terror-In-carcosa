package rpg.Scenario.Mode;

import rpg.Character.Classes.Attributes;
import rpg.Character.Classes.Mage;
import rpg.Events.*;
import rpg.Utils.CombatSystem;
import rpg.Utils.SlowConsole;
import rpg.Character.CharacterCreation.CreatePlayer;
import rpg.itens.Specials.SpeelBook;

import java.io.IOException;
import java.util.Random;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;

import java.util.Scanner;

public class Pve {
    private boolean specialEncounterOccurred = false; // Flag para rastrear o evento SpecialEncounter

    public static void startBattle() {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        Attributes personagem = CreatePlayer.createPlayer(scanner);
        personagem.getTechnicalInfo();

        Pve pveInstance = new Pve(); // Crie uma instância de Pve para rastrear eventos
        personagem.setLevelDungeon(1); // Defina o nível da dungeon inicial como 1

        SlowConsole slowConsole = new SlowConsole();

        Terminal terminal = null;
        NonBlockingReader reader = null;
        try {
            terminal = TerminalBuilder.terminal();
            reader = terminal.reader(); // Obtém o NonBlockingReader diretamente do terminal

            while (personagem.getHealthbar() > 0) {
                slowConsole.imprimirDevagar("Mova-se![W,A,S,D]");

                // Leia um caractere e verifique o código
                int codePoint = reader.read();
                String key = getKeyFromCodePoint(codePoint);

                if (key != null) {
                    switch (key) {
                        case "up":
                        case "down":
                        case "right":
                        case "left":
                            int randomEvent = random.nextInt(12); // Atualizado para 12 eventos possíveis

                            if (randomEvent < 3) { // 3/12 chance para combate (ajustado para 5 eventos de combate possíveis)
                                // Encontro de combate
                                CombatSystem.startCombat(scanner, personagem);
                                // Após o combate, atualize o nível da dungeon se necessário
                            } else {
                                // Evento não combativo
                                pveInstance.nonCombatEvent(personagem, scanner);
                            }
                            break;
                        default:
                            slowConsole.imprimirDevagar("Tecla não reconhecida.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (terminal != null) {
                try {
                    terminal.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getKeyFromCodePoint(int codePoint) {
        // Converta o código do ponto para a tecla correspondente
        // Dependendo do terminal, você pode precisar ajustar isso
        switch (codePoint) {
            case 119: return "up";    // Códigos de teclas
            case 115: return "down";
            case 97: return "right";
            case 100: return "left";
            default: return null;
        }
    }

    private void nonCombatEvent(Attributes personagem, Scanner scanner) {
        SlowConsole slowConsole = new SlowConsole();
        Random random = new Random();
        int eventType = random.nextInt(12); // Atualizado para 12 eventos possíveis

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