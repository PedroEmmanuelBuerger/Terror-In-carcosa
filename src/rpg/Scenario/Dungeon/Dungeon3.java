package rpg.Scenario.Dungeon;

import rpg.Classes.Attributes;
import rpg.Monsters.Mob;
import rpg.Monsters.KingDragon;
import rpg.Utils.Messages.Start;

public class Dungeon3 extends DungeonBase {
    @Override
    protected Attributes createEnemy(Attributes personagem) {
        if (personagem.getLevel() >= 20) {
            Start.EncounterLordDragonKing(); // Exibe a mensagem ao encontrar o Lorde Rei Dragão
            return new KingDragon("Lorde Rei Dragão", 500, 45, 60, "FIRE!!!!!!!!!!!!!");
        } else {
            int randomMonster = random.nextInt(2);
            switch (randomMonster) {
                case 0: return new Mob("Golem", 120, 30, 20, "Meu dever!", 25);
                case 1: return new Mob("Ciclope", 150, 40, 8, "Estou te vendo!", 30);
                default:  return new Mob("Golem", 120, 30, 20, "Meu dever!", 25);
            }
        }
    }

    @Override
    protected void onBossDefeated(Attributes personagem) {
        if (personagem.getLevelDungeon() == 3) {
            Start.FinishDungeon(); // Exibe a mensagem e avança para a próxima dungeon, se houver
        }
        personagem.setLevelDungeon(personagem.getLevelDungeon() + 1); // Atualiza o nível da dungeon
    }
}
