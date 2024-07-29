package rpg.Scenario.Dungeon;

import rpg.Classes.Attributes;
import rpg.Monsters.Mob;
import rpg.Monsters.Bosses.Ghazkull;
import rpg.Utils.Messages.Start;

public class Dungeon1 extends DungeonBase {
    @Override
    protected Attributes createEnemy(Attributes personagem) {
        if (personagem.getLevel() >= 5) {
            Start.EncounterGhazkull(); // Exibe a mensagem ao encontrar Ghazkull
            return new Ghazkull("Ghazkull", 350, 20, 30, "HAHAHAHAHAHA");
        } else {
            int randomMonster = random.nextInt(2);
            switch (randomMonster) {
                case 0:
                    return new Mob("Goblin", 35, 5, 17, "Grrrr!", 5);
                case 1:
                    return new Mob("Zombie", 50, 12, 14, "Braaaaains...", 10);
                default:
                    return new Mob("Goblin", 35, 5, 17, "Grrrr!", 5);
            }
        }
    }

    @Override
    protected void onBossDefeated(Attributes personagem) {
        if (personagem.getLevelDungeon() == 1) {
            Start.FinishGhazkull(); // Exibe a mensagem e avança para a próxima dungeon
        }
        personagem.setLevelDungeon(personagem.getLevelDungeon() + 1); // Atualiza o nível da dungeon
    }
}
