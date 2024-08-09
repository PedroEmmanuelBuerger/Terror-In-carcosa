package rpg.Mode.Campaign.Dungeon;

import rpg.Character.Classes.Attributes;

import java.util.Scanner;

public class CombatSystem {
    public static void startCombat(Scanner scanner, Attributes personagem) {
        Dungeon dungeon;

        if (personagem.getLevelDungeon() == 1) {
            dungeon = new Dungeon1();
        } else if (personagem.getLevelDungeon() == 2) {
            dungeon = new Dungeon2();
        } else if (personagem.getLevelDungeon() == 3) {
            dungeon = new Dungeon3();
        }
        else {
            throw new IllegalStateException("Dungeon não encontrada para o nível: " + personagem.getLevelDungeon());
        }

        dungeon.startCombat(scanner, personagem);
    }
}
