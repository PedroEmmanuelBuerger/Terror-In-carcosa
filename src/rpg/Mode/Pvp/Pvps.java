package rpg.Mode.Pvp;

import rpg.Character.Classes.Attributes;
import rpg.Character.CharacterCreation.CreatePlayer;
import rpg.Utils.SlowConsole;

import java.util.Scanner;

public class Pvps {
    public static void startBattle() {
        Scanner scanner = new Scanner(System.in);
        SlowConsole slowConsole = new SlowConsole();

        slowConsole.imprimirDevagar("Player 1:");
        Attributes player1 = CreatePlayer.createPlayer(scanner,3);
        player1.getTechnicalInfo();
        slowConsole.imprimirDevagar("Player 2:");
        Attributes player2 = CreatePlayer.createPlayer(scanner,3);
        player2.getTechnicalInfo();

        CombatSystemPvP combatSystemPvP = new CombatSystemPvP();
        combatSystemPvP.startCombat(scanner, player1, player2);
    }
}
