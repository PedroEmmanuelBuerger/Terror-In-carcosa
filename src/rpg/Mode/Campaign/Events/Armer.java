package rpg.Mode.Campaign.Events;

import rpg.Character.Classes.*;
import rpg.itens.Weapons.Healer.Bible;
import rpg.itens.Weapons.Healer.Necronomicon;
import rpg.itens.Weapons.Mage.StickOfTruth;
import rpg.itens.Weapons.Mage.Wand;
import rpg.itens.Weapons.Necromancer.DaggersOfSouls;
import rpg.itens.Weapons.Necromancer.DemonHearth;
import rpg.itens.Weapons.Paladin.GiantShield;
import rpg.itens.Weapons.Paladin.GolemSpine;
import rpg.itens.Weapons.Rogue.Bayoneta;
import rpg.itens.Weapons.Rogue.Crossbow;
import rpg.itens.Weapons.Warrior.SwordOfThousandTruths;
import rpg.itens.Weapons.Warrior.Zheiwender;
import rpg.itens.Weapons.Weapon;
import rpg.Utils.SlowConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Armer implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();
    private final Scanner scanner = new Scanner(System.in);

    private final List<Weapon> weaponsForSale;

    public Armer() {
        weaponsForSale = new ArrayList<>();
    }

    @Override
    public void executeEvent(Attributes personagem) {
        slowConsole.imprimirDevagar("=== Você encontrou um armeiro das trevas... ===");
        boolean shopping = true;

        if (personagem instanceof Warrior) {
            weaponsForSale.add(new Zheiwender());
            weaponsForSale.add(new SwordOfThousandTruths());
        } else if (personagem instanceof Rogue) {
            weaponsForSale.add(new Bayoneta());
            weaponsForSale.add(new Crossbow());
        } else if (personagem instanceof Healer) {
            weaponsForSale.add(new Necronomicon());
            weaponsForSale.add(new Bible());
        } else if (personagem instanceof Mage) {
            weaponsForSale.add(new StickOfTruth());
            weaponsForSale.add(new Wand());
        } else if (personagem instanceof Necromancer) {
            weaponsForSale.add(new DaggersOfSouls());
            weaponsForSale.add(new DemonHearth());
        } else if (personagem instanceof Paladin) {
            weaponsForSale.add(new GolemSpine());
            weaponsForSale.add(new GiantShield());
        }

        while (shopping) {
            slowConsole.imprimirDevagar("Você tem " + personagem.getGold() + " Ouro.");
            slowConsole.imprimirDevagar("=== Armas à venda ===");

            for (int i = 0; i < weaponsForSale.size(); i++) {
                Weapon weapon = weaponsForSale.get(i);
                slowConsole.imprimirDevagar((i + 1) + ". " + weapon.getName() + " - " + weapon.getPrice() + " Ouro - Dano: " + weapon.attack());
            }
            slowConsole.imprimirDevagar((weaponsForSale.size() + 1) + ". Voltar ao Jogo");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == weaponsForSale.size() + 1) {
                shopping = false;
            } else if (choice > 0 && choice <= weaponsForSale.size()) {
                Weapon selectedWeapon = weaponsForSale.get(choice - 1);
                buyWeapon(personagem, selectedWeapon);
                if (weaponsForSale.isEmpty()) {
                    slowConsole.imprimirDevagar("Não há mais armas à venda.");
                    shopping = false;
                }
            } else {
                slowConsole.imprimirDevagar("Opção inválida. Tente novamente.");
            }
        }
    }

    private void buyWeapon(Attributes personagem, Weapon weapon) {
        if (personagem.getGold() >= weapon.getPrice()) {
            personagem.setGold(personagem.getGold() - weapon.getPrice());

            Weapon currentWeapon = personagem.getWeapon();
            if (currentWeapon != null) {
                int damageDifference = weapon.attack() - currentWeapon.attack();

                switch (personagem) {
                    case Warrior warrior -> warrior.setAttack(warrior.getAttack() + damageDifference);
                    case Rogue rogue -> rogue.setAttack(rogue.getAttack() + damageDifference);
                    case Healer healer -> healer.setSpecial(healer.getSpecial() + damageDifference);
                    case Mage mage -> mage.setSpecial(mage.getSpecial() + damageDifference);
                    case Necromancer necromancer -> necromancer.setSpecial(necromancer.getSpecial() + damageDifference);
                    case Paladin paladin -> {
                        paladin.setSpecial(paladin.getSpecial() + damageDifference);
                        paladin.setAttack(paladin.getAttack() + damageDifference);
                    }
                    default -> {
                    }
                }
            } else {
                switch (personagem) {
                    case Warrior warrior -> warrior.setAttack(weapon.attack());
                    case Rogue rogue -> rogue.setAttack(weapon.attack());
                    case Healer healer -> healer.setSpecial(weapon.attack());
                    case Mage mage -> mage.setSpecial(weapon.attack());
                    case Necromancer necromancer -> necromancer.setSpecial(weapon.attack());
                    case Paladin paladin -> {
                        paladin.setSpecial(weapon.attack());
                        paladin.setAttack(weapon.attack());
                    }
                    default -> {
                    }
                }
            }

            personagem.setWeapon(weapon);
            weaponsForSale.remove(weapon);
            slowConsole.imprimirDevagar("Você comprou " + weapon.getName() + " por " + weapon.getPrice() + " Ouro.");
            slowConsole.imprimirDevagar(personagem.getName() + " empunha agora a nova arma: " + weapon.getName() + " com dano " + weapon.attack() + ", uma adição sinistra ao seu arsenal.");
        } else {
            slowConsole.imprimirDevagar("Ouro insuficiente para comprar " + weapon.getName() + ".");
        }
    }
}
