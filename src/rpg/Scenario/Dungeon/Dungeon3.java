package rpg.Scenario.Dungeon;

import rpg.Character.Classes.Attributes;
import rpg.Monsters.Bosses.KnightOfFear;
import rpg.Monsters.Mob;
import rpg.Utils.Messages.End;
import rpg.Utils.Messages.Start;

public class Dungeon3 extends DungeonBase {

    @Override
    protected Attributes createEnemy(Attributes personagem) {
        if (personagem.getLevel() >= 20) {
            Start.EncounterTaigon(); // Exibe a mensagem ao encontrar o Cavaleiro do Medo Taigon
            return new KnightOfFear("O Rei de Amarelo", 1000, 60, 80, "O VAZIO CHEGOU PARA VOCÊ...");
        } else {
            int randomMonster = random.nextInt(5);
            return switch (randomMonster) {
                case 1 -> new Mob("Colosso das Sombras", 150, 40, 8, "A ESCURIDÃO ME PERMEIA!", 50, 50);
                case 2 -> new Mob("Olho das Abissais Profundezas", 170, 25, 30, "SEU DESTINO É O VÍNCULO!", 65, 65);
                case 3 -> new Mob("Rito das Trevas", 130, 50, 15, "A FOSSA DA EXISTÊNCIA TE CHAMA!", 60, 60);
                case 4 -> new Mob("Cavaleiro da Névoa", 180, 30, 30, "EU SEREI O PORTADOR DA NÉVOA!", 85, 85);
                default -> new Mob("Golem da Ruína", 120, 30, 20, "SUA ALMA SERÁ ANEXADA AO ABISMO!", 40, 40);
            };
        }
    }

    @Override
    protected void onBossDefeated(Attributes personagem) {
        if (personagem.getLevelDungeon() == 3) {
            Start.EncounterTaigon();
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
        Start.FinishTaigon();
        System.exit(0);
    }
}
