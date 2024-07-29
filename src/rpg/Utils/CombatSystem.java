package rpg.Utils;

import rpg.Scenario.Dungeon.Dungeon;
import rpg.Scenario.Dungeon.Dungeon1;
import rpg.Scenario.Dungeon.Dungeon2;
import rpg.Classes.Attributes;

import java.util.Scanner;

public class CombatSystem {
    public static void startCombat(Scanner scanner, Attributes personagem) {
        Dungeon dungeon;

        // Escolhe a dungeon com base no nível atual do jogador
        if (personagem.getLevelDungeon() == 1) {
            dungeon = new Dungeon1();
        } else if (personagem.getLevelDungeon() == 2) {
            dungeon = new Dungeon2();
        } else {
            // Lógica para dungeons futuras ou um caso padrão
            throw new IllegalStateException("Dungeon não encontrada para o nível: " + personagem.getLevelDungeon());
        }

        dungeon.startCombat(scanner, personagem);
    }
}
