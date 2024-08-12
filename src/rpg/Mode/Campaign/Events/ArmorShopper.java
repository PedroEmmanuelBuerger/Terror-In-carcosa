package rpg.Mode.Campaign.Events;

import rpg.Character.Classes.*;
import rpg.itens.Armors.*;
import rpg.itens.Armors.Armor;
import rpg.Utils.SlowConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ArmorShopper implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();
    private final Scanner scanner = new Scanner(System.in);

    private final List<Armor> allArmors;
    private List<Armor> armorsForSale;

    public ArmorShopper() {
        allArmors = new ArrayList<>();
        allArmors.add(new Rags());
        allArmors.add(new LeatherUndefinedColor());
        allArmors.add(new FabricoftheCosmos());
        allArmors.add(new DemonCarcass());
        allArmors.add(new CursedGoldenArmor());
        allArmors.add(new Robe());
        allArmors.add(new UltradimensionalOctopusSkin());
        allArmors.add(new RuinedKing()); // Adicione RuinedKing
    }

    @Override
    public void executeEvent(Attributes personagem) {
        slowConsole.imprimirDevagar("=== Você encontrou um ferreiro das trevas especializado em armaduras... ===");
        boolean shopping = true;

        filterAndSelectArmorsForSale(personagem);

        while (shopping) {
            slowConsole.imprimirDevagar("Você tem " + personagem.getGold() + " Ouro.");
            slowConsole.imprimirDevagar("=== Armaduras à venda ===");

            for (int i = 0; i < armorsForSale.size(); i++) {
                Armor armor = armorsForSale.get(i);
                slowConsole.imprimirDevagar((i + 1) + ". " + armor.getName() + " - " + armor.getPrice() + " Ouro - Proteção: " + armor.armor());
            }
            slowConsole.imprimirDevagar((armorsForSale.size() + 1) + ". Voltar ao Jogo");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == armorsForSale.size() + 1) {
                shopping = false;
            } else if (choice > 0 && choice <= armorsForSale.size()) {
                Armor selectedArmor = armorsForSale.get(choice - 1);
                buyArmor(personagem, selectedArmor);
                armorsForSale.remove(selectedArmor);
                if (armorsForSale.isEmpty()) {
                    slowConsole.imprimirDevagar("Não há mais armaduras à venda.");
                    shopping = false;
                }
            } else {
                slowConsole.imprimirDevagar("Opção inválida. Tente novamente.");
            }
        }
    }

    private void filterAndSelectArmorsForSale(Attributes personagem) {
        List<Armor> availableArmors = new ArrayList<>();
        for (Armor armor : allArmors) {
            if (armor instanceof Robe && !(personagem instanceof Healer || personagem instanceof Mage || personagem instanceof Necromancer)) {
                continue;
            }
            availableArmors.add(armor);
        }

        Random random = new Random();
        armorsForSale = new ArrayList<>();
        if (availableArmors.size() > 2) {
            for (int i = 0; i < 2; i++) {
                int index = random.nextInt(availableArmors.size());
                armorsForSale.add(availableArmors.remove(index));
            }
        } else {
            armorsForSale.addAll(availableArmors);
        }
    }

    private void buyArmor(Attributes personagem, Armor armor) {
        if (personagem.getGold() >= armor.getPrice()) {
            personagem.setGold(personagem.getGold() - armor.getPrice());
            personagem.setArmor(armor);

            applyArmorBonus(personagem, armor);

            if (armor instanceof RuinedKing) {
                personagem.setHealthbar(1);
                personagem.setMaxHealthInitial(1);
                slowConsole.imprimirDevagar("Você equipou " + armor.getName() + " e sua vida foi reduzida para 1.");
            } else {
                slowConsole.imprimirDevagar("Você comprou " + armor.getName() + " por " + armor.getPrice() + " Ouro.");
                slowConsole.imprimirDevagar("Agora, sua armadura equipada é: " + armor.getName() + " com proteção " + armor.armor() + ".");
            }
        } else {
            slowConsole.imprimirDevagar("Ouro insuficiente para comprar " + armor.getName() + ".");
        }
    }

    private void applyArmorBonus(Attributes personagem, Armor armor) {
        if (personagem instanceof Healer healer) {
            if (armor instanceof Robe) {
                healer.setMana(healer.getMana() + armor.armor());
            }
        } else if (personagem instanceof Mage mage) {
            if (armor instanceof Robe) {
                mage.setMana(mage.getMana() + armor.armor());
            }
        } else if (personagem instanceof Necromancer necromancer) {
            if (armor instanceof Robe) {
                necromancer.setMana(necromancer.getMana() + armor.armor());
            }
        }
    }
}
