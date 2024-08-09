package rpg.Campaign.Dungeon;

import rpg.Character.Classes.Attributes;
import rpg.Monsters.Bosses.KnightOfFear;
import rpg.Monsters.Mob;
import rpg.Utils.Messages.End;
import rpg.Utils.Messages.Portraits;
import rpg.Utils.Messages.Start;

public class Dungeon3 extends DungeonBase {

    @Override
    protected Attributes createEnemy(Attributes personagem) {
        if (personagem.getLevel() >= 20) {
            Start.EncounterTaigon();
            KnightOfFear boss = new KnightOfFear("O Rei de Amarelo", 1000, 60, 80, "O VAZIO CHEGOU PARA VOCÊ...", Portraits.PortraitTaigon());
            boss.generateTechinicalInfo();
            return boss;
        } else {
            int randomMonster = random.nextInt(5);
            Mob mob;
            switch (randomMonster) {
                case 1 -> mob = new Mob("Colosso das Sombras", 150, 40, 8, "A ESCURIDÃO ME PERMEIA!", 50, 50, Portraits.PortraitColossoDasSombras());
                case 2 -> mob = new Mob("Olho das Abissais Profundezas", 170, 25, 30, "SEU DESTINO É O VÍNCULO!", 65, 65, Portraits.PortraitOlhoDasAbissaisProfundezas());
                case 3 -> mob = new Mob("Rito das Trevas", 130, 50, 15, "A FOSSA DA EXISTÊNCIA TE CHAMA!", 60, 60, Portraits.PortraitRitoDasTrevas());
                case 4 -> mob = new Mob("Cavaleiro da Névoa", 180, 30, 30, "EU SEREI O PORTADOR DA NÉVOA!", 85, 85, Portraits.PortraitCavaleiroDaNevoa());
                default -> mob = new Mob("Golem da Ruína", 120, 30, 20, "SUA ALMA SERÁ ANEXADA AO ABISMO!", 40, 40, Portraits.PortraitGolemDaRuina());
            }
            mob.generateTechinicalInfo();
            return mob;
        }
    }

    @Override
    protected void onBossDefeated(Attributes personagem) {
        if (personagem.getLevelDungeon() == 3) {
            Start.EncounterTaigon();
            String characterClass = personagem.getClasses();

            switch (characterClass) {
                case "Guerreiro":
                    End.FinishWarrior();
                    break;
                case "Mago":
                    End.FinishMage();
                    break;
                case "Necromante":
                    End.FinishNecromancer();
                    break;
                case "Curandeiro":
                    End.FinishHealer();
                    break;
                case "Bandido":
                    End.FinishRogue();
                    break;
                default:
                    System.out.println("Você derrotou o Cavaleiro do Medo Taigon, mas sua classe não é reconhecida para mensagens específicas.");
            }
        }
        Start.FinishTaigon();
        System.exit(0);
    }
}
