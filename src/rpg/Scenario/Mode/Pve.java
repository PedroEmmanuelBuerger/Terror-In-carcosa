    package rpg.Scenario.Mode;

    import rpg.Classes.Attributes;
    import rpg.Classes.Mage;
    import rpg.Classes.Healer;
    import rpg.Classes.Warrior;
    import rpg.Events.*;
    import rpg.Utils.CombatSystem;
    import rpg.Utils.SlowConsole;
    import rpg.CharacterCreation.CreatePlayer;
    import rpg.itens.SpeelBook;

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

            while (personagem.getHealthbar() > 0) {
                int randomEvent = random.nextInt(7); // Ajustado para 7 eventos possíveis

                if (randomEvent < 2) {
                    // Encontro de combate
                    CombatSystem.startCombat(scanner, personagem);
                } else {
                    // Evento não combativo
                    pveInstance.nonCombatEvent(personagem);
                }
            }
        }

        public void setLevelDungeon(int levelDungeon) {
            this.levelDungeon = levelDungeon;
        }

        private void nonCombatEvent(Attributes personagem) {
            SlowConsole slowConsole = new SlowConsole();
            Random random = new Random();
            int eventType = random.nextInt(8); // Ajuste para 8 eventos possíveis, incluindo o novo

            NonCombatEvent event = null;
            switch (eventType) {
                case 0:
                    if (personagem instanceof Mage) {
                        Mage mage = (Mage) personagem;
                        if (!specialEncounterOccurred) {
                            SpeelBook speelBookactual = mage.getSpeelBook();// Verifica se o evento já ocorreu
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
                    event = new NewWeapon(); // Adiciona o novo evento
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
