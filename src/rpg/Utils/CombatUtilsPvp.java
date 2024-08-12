package rpg.Utils;

import rpg.Character.Classes.Attributes;
import rpg.Character.Classes.Healer;
import rpg.Character.Classes.Mage;
import rpg.Character.Classes.Necromancer;
import rpg.Character.Classes.Warrior;

public class CombatUtilsPvp {
    private static final SlowConsole slowConsole = new SlowConsole();

    public static void printPlayerActions(Attributes player) {
        slowConsole.imprimirDevagar("Escolha sua ação:");
        slowConsole.imprimirDevagar("1 - Atacar");

        if (player instanceof Mage) {
            slowConsole.imprimirDevagar("2 - Usar Magia");
        } else if (player instanceof Necromancer) {
            slowConsole.imprimirDevagar("2 - Invocar Imp (Custo de mana: 15)");
            slowConsole.imprimirDevagar("3 - Necromancia");
            slowConsole.imprimirDevagar("4 - Ver Status");
            slowConsole.imprimirDevagar("5 - Usar Item");
        } else {
            slowConsole.imprimirDevagar("2 - Ataque Especial");
            slowConsole.imprimirDevagar("3 - Ver Status");
            slowConsole.imprimirDevagar("4 - Usar Item");

            if (player instanceof Warrior) {
                slowConsole.imprimirDevagar("5 - Defender");
            } else if (player instanceof Healer) {
                slowConsole.imprimirDevagar("5 - Ressurreição");
                slowConsole.imprimirDevagar("6 - Curar");
            }
        }
    }
}
