package rpg.Campaign.Events;

import rpg.Character.Classes.*;
import rpg.itens.Armors.*;
import rpg.Utils.SlowConsole;

import java.util.Random;
import java.util.Scanner;

public class NewArmor implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();

    @Override
    public void executeEvent(Attributes personagem) {
        Armor[] armors = getAvailableArmors(personagem);

        Random random = new Random();
        Armor newArmor = armors[random.nextInt(armors.length)];

        slowConsole.imprimirDevagar("Você encontrou uma armadura antiga e corrompida: " + newArmor.getName() + " com proteção (" + newArmor.armor() + ").");

        Armor currentArmor = personagem.getArmor();
        if (currentArmor != null) {
            slowConsole.imprimirDevagar("Sua armadura atual é: " + currentArmor.getName() + " com proteção (" + currentArmor.armor() + ").");
        }

        slowConsole.imprimirDevagar("Deseja equipar esta armadura profana? (s/n)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("s")) {
            if (currentArmor != null) {
                // Aplica o bônus da nova armadura
                if (personagem instanceof Healer healer) {
                    healer.setMana(healer.getMana() + newArmor.armor() - currentArmor.armor());
                } else if (personagem instanceof Mage mage) {
                    mage.setMana(mage.getMana() + newArmor.armor() - currentArmor.armor());
                } else if (personagem instanceof Necromancer necromancer) {
                    necromancer.setMana(necromancer.getMana() + newArmor.armor() - currentArmor.armor());
                }
            } else {
                // Inicializa a mana com o bônus da nova armadura
                if (personagem instanceof Healer healer) {
                    healer.setMana(newArmor.armor());
                } else if (personagem instanceof Mage mage) {
                    mage.setMana(newArmor.armor());
                } else if (personagem instanceof Necromancer necromancer) {
                    necromancer.setMana(newArmor.armor());
                }
            }

            personagem.setArmor(newArmor);

            slowConsole.imprimirDevagar(personagem.getName() + " agora está usando a nova armadura: " + newArmor.getName() + " com proteção " + newArmor.armor() + ", uma adição sinistra ao seu equipamento.");
        } else {
            slowConsole.imprimirDevagar(personagem.getName() + " decidiu não equipar a armadura, como se um pressentimento de mal-estar o tivesse envolvido.");
        }
    }

    private Armor[] getAvailableArmors(Attributes personagem) {
        if (personagem instanceof Healer) {
            return new Armor[] {
                    new Robe(), // Robe é específico para Healers, Mages e Necromancers
                    new Rags(),
                    new LeatherUndefinedColor(),
                    new FabricoftheCosmos(),
                    new DemonCarcass(),
                    new CursedGoldenArmor()
            };
        } else if (personagem instanceof Mage) {
            return new Armor[] {
                    new Robe(),
                    new Rags(),
                    new LeatherUndefinedColor(),
                    new FabricoftheCosmos(),
                    new DemonCarcass(),
                    new CursedGoldenArmor()
            };
        } else if (personagem instanceof Necromancer) {
            return new Armor[] {
                    new Robe(),
                    new Rags(),
                    new LeatherUndefinedColor(),
                    new FabricoftheCosmos(),
                    new DemonCarcass(),
                    new CursedGoldenArmor()
            };
        } else {
            return new Armor[] {
                    new Rags(),
                    new LeatherUndefinedColor(),
                    new FabricoftheCosmos(),
                    new DemonCarcass(),
                    new CursedGoldenArmor()
            };
        }
    }
}
