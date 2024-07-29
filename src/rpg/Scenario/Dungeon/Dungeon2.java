package rpg.Scenario.Dungeon;

import rpg.Classes.Attributes;
import rpg.Monsters.Carnical;
import rpg.Monsters.KingDragon;
import rpg.Monsters.Lobisomen;
import rpg.Utils.Messages.Start;

public class Dungeon2 extends DungeonBase {
    @Override
    protected Attributes createEnemy(Attributes personagem) {
        if (personagem.getLevel() >= 10) {
            Start.EncounterLordDragonKing(); // Exibe a mensagem ao encontrar o Lorde Rei Dragão
            return new KingDragon("Lorde Rei Dragão", 500, 45, 60, "FIRE!!!!!!!!!!!!!");
        } else {
            int randomMonster = random.nextInt(2);
            switch (randomMonster) {
                case 0: return new Carnical("Carniçal", 80, 15, 25, "GRAAAAAAAA!");
                case 1: return new Lobisomen("Lobisomen", 110, 27, 12, "ARRGH!");
                default: return new Carnical("Carniçal", 80, 15, 25, "GRAAAAAAAA!");
            }
        }
    }

    @Override
    protected void onBossDefeated(Attributes personagem) {
        if (personagem.getLevelDungeon() == 2) {
            Start.FinishDungeon(); // Exibe a mensagem e avança para a próxima dungeon, se houver
        }
        personagem.setLevelDungeon(personagem.getLevelDungeon() + 1); // Atualiza o nível da dungeon
    }
}
