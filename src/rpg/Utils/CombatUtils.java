package rpg.Utils;

import rpg.Character.Classes.Attributes;
import rpg.Character.Classes.Healer;
import rpg.Character.Classes.Mage;
import rpg.Character.Classes.Necromancer;
import rpg.Character.Classes.Warrior;

public class CombatUtils {
    private static final SlowConsole slowConsole = new SlowConsole();

    public static void printPlayerActions(Attributes player) {
        slowConsole.imprimirDevagar("1 - Atacar");
        if (player instanceof Mage) {
            slowConsole.imprimirDevagar("2 - Livro de magias");
        } else if (player instanceof Necromancer) {
            slowConsole.imprimirDevagar("2 - Invocar Imp (Custo de mana: 15)");
        } else {
            slowConsole.imprimirDevagar("2 - Ataque Especial");
        }
        if (player instanceof Necromancer) {
            slowConsole.imprimirDevagar("3 - Necromancia");
            slowConsole.imprimirDevagar("4 - Fugir");
            slowConsole.imprimirDevagar("5 - Status");
            slowConsole.imprimirDevagar("6 - Usar Item");
        } else {
            slowConsole.imprimirDevagar("3 - Fugir");
            slowConsole.imprimirDevagar("4 - Status");
            slowConsole.imprimirDevagar("5 - Usar Item");
            if (player instanceof Warrior) {
                slowConsole.imprimirDevagar("6 - Defender");
            } else if (player instanceof Healer) {
                slowConsole.imprimirDevagar("6 - Ressurreição");
                slowConsole.imprimirDevagar("7 - Curar");
            }
        }
    }
}
