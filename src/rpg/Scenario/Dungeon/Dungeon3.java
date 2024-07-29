package rpg.Scenario.Dungeon;

import rpg.Classes.Attributes;
import rpg.Monsters.Bosses.KnightOfFear;
import rpg.Monsters.Mob;
import rpg.Monsters.Bosses.KingDragon;
import rpg.Utils.Messages.Start;

public class Dungeon3 extends DungeonBase {
    @Override
    protected Attributes createEnemy(Attributes personagem) {
        if (personagem.getLevel() >= 20) {
            Start.EncounterTaigon(); // Exibe a mensagem ao encontrar o Lorde Rei Dragão
            return new KnightOfFear("Cavaleiro do medo Taigon", 1000, 60, 80, "Seu fim esta próximo...");
        } else {
            int randomMonster = random.nextInt(2);
            switch (randomMonster) {
                case 0: return new Mob("Golem", 120, 30, 20, "Meu dever!", 40);
                case 1: return new Mob("Ciclope", 150, 40, 8, "Estou te vendo!", 50);
                default:  return new Mob("Golem", 120, 30, 20, "Meu dever!", 40);
            }
        }
    }

    @Override
    protected void onBossDefeated(Attributes personagem) {
        if (personagem.getLevelDungeon() == 3) {
            Start.FinishTaigon(); // Exibe a mensagem e avança para a próxima dungeon, se houver
        }
        personagem.setLevelDungeon(personagem.getLevelDungeon() + 1); // Atualiza o nível da dungeon
    }
}
