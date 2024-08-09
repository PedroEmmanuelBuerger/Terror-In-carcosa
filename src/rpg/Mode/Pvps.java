package rpg.Mode;

import rpg.Character.Classes.Attributes;
import rpg.Character.CharacterCreation.CreatePlayer;
import rpg.Mode.Pvp.CombatSystemPvP;
import rpg.Utils.SlowConsole;

import java.util.Scanner;

public class Pvps {
    public static void startBattle() {
        Scanner scanner = new Scanner(System.in);
        SlowConsole slowConsole = new SlowConsole();

        // Criar dois jogadores
        slowConsole.imprimirDevagar("Player 1:");
        Attributes player1 = CreatePlayer.createPlayer(scanner);
        player1.getTechnicalInfo();
        slowConsole.imprimirDevagar("Player 2:");
        Attributes player2 = CreatePlayer.createPlayer(scanner);
        player2.getTechnicalInfo();

        CombatSystemPvP combatSystemPvP = new CombatSystemPvP();
        combatSystemPvP.startCombat(scanner, player1, player2);
    }
}
