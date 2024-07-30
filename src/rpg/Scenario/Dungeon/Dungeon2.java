package rpg.Scenario.Dungeon;

import rpg.Classes.Attributes;
import rpg.Monsters.Mob;
import rpg.Monsters.Bosses.KingDragon;
import rpg.Utils.Messages.Start;

public class Dungeon2 extends DungeonBase {
    @Override
    protected Attributes createEnemy(Attributes personagem) {
        if (personagem.getLevel() >= 10) {
            Start.EncounterLordDragonKing(); // Exibe a mensagem ao encontrar o Lorde Rei Dragão
            return new KingDragon("Lorde Rei Dragão", 550, 45, 60, "FIRE!!!!!!!!!!!!!");
        } else {
            int randomMonster = random.nextInt(2);
            switch (randomMonster) {
                case 0: return new Mob("Carniçal", 80, 15, 25, "GRAAAAAAAA!", 20);
                case 1: return new Mob("Lobisomen", 110, 27, 12, "ARRGH!", 25);
                default: return new Mob("Carniçal", 80, 15, 25, "GRAAAAAAAA!", 20);
            }
        }
    }

    @Override
    protected void onBossDefeated(Attributes personagem) {
        if (personagem.getLevelDungeon() == 2) {
            Start.FinishDragonKing(); // Exibe a mensagem e avança para a próxima dungeon, se houver
        }
        personagem.setLevelDungeon(personagem.getLevelDungeon() + 1); // Atualiza o nível da dungeon
    }
}
