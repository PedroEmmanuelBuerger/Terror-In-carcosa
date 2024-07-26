    package rpg.Utils;

    import rpg.Scenario.Dungeon.Dungeon1;
    import rpg.Scenario.Dungeon.Dungeon2;
    import rpg.Classes.Attributes;

    import java.util.Scanner;

    public class CombatSystem {

        public static void startCombat(Scanner scanner, Attributes personagem) {
            if(personagem.getLevelDungeon() == 1) {
                Dungeon1 dungeon1 = new Dungeon1();
                dungeon1.startCombat(scanner, personagem);
            }
            else {
                Dungeon2 dungeon2 = new Dungeon2();
                dungeon2.startCombat(scanner, personagem);
            }
        }
    }