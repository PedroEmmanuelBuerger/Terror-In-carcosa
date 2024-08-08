package rpg.Scenario.Dungeon;

import rpg.Character.Classes.Attributes;
import rpg.Monsters.Mob;
import rpg.Monsters.Bosses.KingDragon;
import rpg.Utils.Messages.Portraits;
import rpg.Utils.Messages.Start;

public class Dungeon2 extends DungeonBase {
    @Override
    protected Attributes createEnemy(Attributes personagem) {
        if (personagem.getLevel() >= 10) {
            Start.EncounterLordDragonKing(); // Exibe a mensagem ao encontrar o Lorde Rei Dragão
            KingDragon boss = new KingDragon("Lorde Rei Dragão", 550, 45, 60, "O FOGO CONSUMIRÁ A REALIDADE!", Portraits.PortraitKingDragon());
            boss.generateTechinicalInfo();
            return boss;
        } else {
            int randomMonster = random.nextInt(5);
            Mob mob;
            switch (randomMonster) {
                case 1 -> mob = new Mob("Lobisomem das Profundezas", 110, 27, 12, "A NOITE SE FECHA SOBRE VOCÊ!", 25, 25, Portraits.PortraitLobisomemDasProfundezas());
                case 2 -> mob = new Mob("Demônio do Caos", 90, 40, 0, "SEJA CONSUMIDO PELA ESCURIDÃO!", 30, 30, Portraits.PortraitDemonioDoCaos());
                case 3 -> mob = new Mob("Cavalo das Sombras", 70, 25, 30, "SUA EXISTÊNCIA É INSIGNIFICANTE!", 25, 25, Portraits.PortraitCavaloDasSombras());
                case 4 -> mob = new Mob("Aranha do Abismo", 75, 17, 20, "A TEIA DO CAOS SE FECHA!", 18, 18, Portraits.PortraitAranhaDoAbismo());
                default -> mob = new Mob("Devorador de Luz", 80, 15, 25, "A LUZ SERÁ DEVORADA!", 20, 20, Portraits.PortraitDevoradorDeLuz());
            }
            mob.generateTechinicalInfo(); // Exibe as informações técnicas incluindo o retrato
            return mob;
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
