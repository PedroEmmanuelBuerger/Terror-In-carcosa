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
            return new Ghazkull("Ghazkull", 350, 20, 30, "SUA ALMA É MINHA!");
        } else {
            int randomMonster = random.nextInt(5);
            return switch (randomMonster) {
                case 0 -> new Mob("Sombra do Abismo", 35, 5, 13, "Sinto o cosmos chorando!", 5, 5);
                case 1 -> new Mob("Andarilho dos Vastos Vácuos", 50, 12, 10, "A eternidade me consome...", 10, 10);
                case 2 -> new Mob("Crepúsculo Alado", 25, 5, 4, "O vazio é meu lar...", 3, 3);
                case 3 -> new Mob("Caçador de Almas", 65, 15, 7, "Seu ouro não pode salvar sua mente...", 15, 15);
                case 4 -> new Mob("Guardião dos Sepulcros", 45, 10, 7, "A escuridão vai te engolir!", 7, 7);
                default -> new Mob("Sombra do Abismo", 35, 5, 13, "Sinto o cosmos chorando!", 5, 5);
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
