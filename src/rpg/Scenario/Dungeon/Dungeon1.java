package rpg.Scenario.Dungeon;

import rpg.Classes.Attributes;
import rpg.Monsters.Boss;
import rpg.Monsters.Goblin;
import rpg.Monsters.Zombie;
import rpg.Utils.Messages.Start;
import rpg.itens.Specials.Imp;

import java.util.Scanner;

public class Dungeon1 extends DungeonBase {
    @Override
    protected Attributes createEnemy(Attributes personagem) {
        if (personagem.getLevel() >= 5) {
            Start.EncounterGhazkull(); // Exibe a mensagem ao encontrar Ghazkull
            return new Boss("Ghazkull", 350, 20, 30, "HAHAHAHAHAHA");
        } else {
            int randomMonster = random.nextInt(2);
            switch (randomMonster) {
                case 0: return new Goblin("Goblin", 35, 5, 17, "Grrrr!");
                case 1: return new Zombie("Zombie", 50, 12, 14, "Braaaaains...");
                default: return new Goblin("Goblin", 35, 5, 17, "Grrrr!");
            }
        }
    }

    @Override
    protected void onBossDefeated(Attributes personagem) {
        if (personagem.getLevelDungeon() == 1) {
            Start.FinishFirstBoss(); // Exibe a mensagem e avança para a próxima dungeon
        }
        personagem.setLevelDungeon(personagem.getLevelDungeon() + 1); // Atualiza o nível da dungeon
    }
}
