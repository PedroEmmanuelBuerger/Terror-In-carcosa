package rpg.Mode.Coops.Dungeons;

import rpg.Character.Classes.Attributes;
import rpg.Monsters.Bosses.Ghazkull;
import rpg.Monsters.Minions.Mob;
import rpg.Utils.Messages.Portraits;
import rpg.Utils.Messages.Start;

import java.util.List;

public class Dungeon1Coop extends DungeonBaseCoop {

    @Override
    protected Attributes createEnemy(List<Attributes> jogadores) {
        int nivelMedio = jogadores.getFirst().getLevel();

        if (nivelMedio >= 5) {
            Start.EncounterGhazkull();
            Ghazkull boss = new Ghazkull("Ghazkull", 350, 20, 30, "SUA ALMA É MINHA!", Portraits.PortraitGhazkull());
            boss.generateTechinicalInfo();
            return boss;
        } else {
            int randomMonster = random.nextInt(5);
            Mob mob;
            switch (randomMonster) {
                case 1 -> mob = new Mob("Andarilho dos Vastos Vácuos", 50, 12, 10, "A eternidade me consome...", 10, 10, Portraits.PortraitAndarilhoDosVastosVacuo());
                case 2 -> mob = new Mob("Crepúsculo Alado", 25, 5, 4, "O vazio é meu lar...", 3, 3, Portraits.PortraitCrepusculoAlado());
                case 3 -> mob = new Mob("Caçador de Almas", 65, 15, 7, "Seu ouro não pode salvar sua mente...", 15, 15, Portraits.PortraitCacadorDeAlmas());
                case 4 -> mob = new Mob("Guardião dos Sepulcros", 45, 10, 7, "A escuridão vai te engolir!", 7, 7, Portraits.PortraitGuardiaoDosSepulcros());
                default -> mob = new Mob("Sombra do Abismo", 35, 5, 13, "Sinto o cosmos chorando!", 5, 5, Portraits.PortraitSombraDoAbismo());
            }
            mob.generateTechinicalInfo();
            return mob;
        }
    }

    @Override
    protected void onBossDefeated(List<Attributes> jogadores) {
        for (Attributes jogador : jogadores) {
            if (jogador.getLevelDungeon() == 1) {
                Start.FinishGhazkull();
            }
            jogador.setLevelDungeon(jogador.getLevelDungeon() + 1);
        }
    }
}
