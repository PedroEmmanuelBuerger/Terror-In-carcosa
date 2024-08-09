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
    private SlowConsole slowConsole = new SlowConsole();
    private Scanner scanner = new Scanner(System.in);

    private List<Armor> allArmors; // Lista completa de armaduras
    private List<Armor> armorsForSale; // Armaduras disponíveis para venda

    public ArmorShopper() {
        allArmors = new ArrayList<>();
        allArmors.add(new Rags());
        allArmors.add(new LeatherUndefinedColor());
        allArmors.add(new FabricoftheCosmos());
        allArmors.add(new DemonCarcass());
        allArmors.add(new CursedGoldenArmor());
        allArmors.add(new Robe()); // Adicione Robe para Healers, Mages, e Necromancers
    }

    @Override
    public void executeEvent(Attributes personagem) {
        slowConsole.imprimirDevagar("=== Você encontrou um ferreiro das trevas especializado em armaduras... ===");
        boolean shopping = true;

        // Filtra armaduras que podem ser compradas pela classe do personagem e seleciona aleatoriamente duas
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
        // Filtra armaduras que podem ser compradas pela classe do personagem
        List<Armor> availableArmors = new ArrayList<>();
        for (Armor armor : allArmors) {
            if (armor instanceof Robe && !(personagem instanceof Healer || personagem instanceof Mage || personagem instanceof Necromancer)) {
                continue;
            }
            availableArmors.add(armor);
        }

        // Seleciona aleatoriamente duas armaduras
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

            // Aplica o bônus específico se necessário
            applyArmorBonus(personagem, armor);

            slowConsole.imprimirDevagar("Você comprou " + armor.getName() + " por " + armor.getPrice() + " Ouro.");
            slowConsole.imprimirDevagar("Agora, sua armadura equipada é: " + armor.getName() + " com proteção " + armor.armor() + ".");
        } else {
            slowConsole.imprimirDevagar("Ouro insuficiente para comprar " + armor.getName() + ".");
        }
    }

    private void applyArmorBonus(Attributes personagem, Armor armor) {
        if (personagem instanceof Healer healer) {
            if (armor instanceof Robe) {
                healer.setMana(healer.getMana() + armor.armor()); // Aplica bônus de mana se for o Robe
            }
        } else if (personagem instanceof Mage mage) {
            if (armor instanceof Robe) {
                mage.setMana(mage.getMana() + armor.armor()); // Aplica bônus de mana se for o Robe
            }
        } else if (personagem instanceof Necromancer necromancer) {
            if (armor instanceof Robe) {
                necromancer.setMana(necromancer.getMana() + armor.armor()); // Aplica bônus de mana se for o Robe
            }
        }
    }
}
