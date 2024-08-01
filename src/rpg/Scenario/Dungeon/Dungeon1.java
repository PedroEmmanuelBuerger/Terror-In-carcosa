package rpg.Scenario.Dungeon;

import rpg.Character.Classes.Attributes;
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
            int randomMonster = random.nextInt(5);
            return switch (randomMonster) {
                case 0 -> new Mob("Goblin", 35, 5, 13, "Grrrr!", 5, 5);
                case 1 -> new Mob("Zombie", 50, 12, 10, "Braaaaains...", 10, 10);
                case 2 -> new Mob("Morcego", 25, 5, 4, "zizpzi", 3, 3);
                case 3 -> new Mob("Mercenario", 65, 15, 7, "Me passe seu ouro!", 15, 15);
                case 4 -> new Mob("Coveiro", 45, 10, 7, "Te enterrarei aqui!", 7, 7);
                default -> new Mob("Goblin", 35, 5, 13, "Grrrr!", 5, 5);
            };
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
