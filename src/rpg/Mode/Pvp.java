package rpg.Mode;

import rpg.Character.Classes.Attributes;
import rpg.Character.CharacterCreation.CreatePlayer;
import rpg.Utils.CombatSystemPvP;
import rpg.Utils.SlowConsole;

import java.util.Scanner;

public class Pvp {
    public static void startBattle() {
        Scanner scanner = new Scanner(System.in);
        SlowConsole slowConsole = new SlowConsole();

        // Criar dois jogadores
        Attributes player1 = CreatePlayer.createPlayer(scanner);
        Attributes player2 = CreatePlayer.createPlayer(scanner);

        player1.getTechnicalInfo();
        player2.getTechnicalInfo();

        // Definir nível de dungeon como 1 para ambos os jogadores
        player1.setLevelDungeon(1);
        player2.setLevelDungeon(1);

        slowConsole.imprimirDevagar("O combate entre " + player1.getName() + " e " + player2.getName() + " começou!");

        CombatSystemPvP combatSystemPvP = new CombatSystemPvP();
        combatSystemPvP.startCombat(scanner, player1, player2);
    }
}
