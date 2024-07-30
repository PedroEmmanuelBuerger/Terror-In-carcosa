package rpg.Scenario.Dungeon;

import rpg.Classes.Attributes;
import rpg.Monsters.Bosses.KnightOfFear;
import rpg.Monsters.Mob;
import rpg.Monsters.Bosses.KingDragon;
import rpg.Utils.Messages.End;
import rpg.Utils.Messages.Start;

public class Dungeon3 extends DungeonBase {

    @Override
    protected Attributes createEnemy(Attributes personagem) {
        if (personagem.getLevel() >= 20) {
            Start.EncounterTaigon(); // Exibe a mensagem ao encontrar o Cavaleiro do Medo Taigon
            return new KnightOfFear("Cavaleiro do medo Taigon", 1000, 60, 80, "Seu fim está próximo...");
        } else {
            int randomMonster = random.nextInt(5);
            switch (randomMonster) {
                case 0:
                    return new Mob("Golem", 120, 30, 20, "Meu dever!", 40);
                case 1:
                    return new Mob("Ciclope", 150, 40, 8, "Estou te vendo!", 50);
                case 2:
                    return new Mob("Observador", 170, 25, 30, "Seja reduzido a Pó", 65);
                case 3:
                    return new Mob("Cervo Demoniaco", 130, 50, 15, "Te levarei até o inferno!", 60);
                case 4:
                    return new Mob("Cavaleiro Corrompido", 180, 30, 30, "Servirei a Taigon até o fim!", 85);
                default:
                    return new Mob("Golem", 120, 30, 20, "Meu dever!", 40);
            }
        }
    }

    @Override
    protected void onBossDefeated(Attributes personagem) {
        if (personagem.getLevelDungeon() == 3) {
            Start.FinishTaigon();
            String characterClass = personagem.getClasses(); // Supondo que existe um método para obter a classe do personagem

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
        System.exit(0);
    }
}
