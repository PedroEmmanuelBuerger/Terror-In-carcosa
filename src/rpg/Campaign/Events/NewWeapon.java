package rpg.Campaign.Events;

import rpg.Character.Classes.*;
import rpg.itens.Weapons.*;
import rpg.Utils.SlowConsole;

import java.util.Random;
import java.util.Scanner;

public class NewWeapon implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();

    @Override
    public void executeEvent(Attributes personagem) {
        Weapon[] weapons = getAvailableWeapons(personagem);

        Random random = new Random();
        Weapon newWeapon = weapons[random.nextInt(weapons.length)];

        slowConsole.imprimirDevagar("Você encontrou uma arma antiga e corrompida: " + newWeapon.getName() + " com dano (" + newWeapon.attack() + ").");

        Weapon currentWeapon = personagem.getWeapon();
        if (currentWeapon != null) {
            slowConsole.imprimirDevagar("Sua arma atual é: " + currentWeapon.getName() + " com dano (" + currentWeapon.attack() + ").");
        }

        slowConsole.imprimirDevagar("Deseja equipar esta arma profana? (s/n)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("s")) {
            if (currentWeapon != null) {
                int damageDifference = newWeapon.attack() - currentWeapon.attack();

                switch (personagem) {
                    case Warrior warrior -> warrior.setAttack(warrior.getAttack() + damageDifference);
                    case Rogue rogue -> rogue.setAttack(rogue.getAttack() + damageDifference);
                    case Healer healer -> healer.setSpecial(healer.getSpecial() + damageDifference);
                    case Mage mage -> mage.setSpecial(mage.getSpecial() + damageDifference);
                    case Necromancer necromancer -> necromancer.setSpecial(necromancer.getSpecial() + damageDifference);
                    default -> {
                    }
                }
            } else {
                switch (personagem) {
                    case Warrior warrior -> warrior.setAttack(newWeapon.attack());
                    case Rogue rogue -> rogue.setAttack(newWeapon.attack());
                    case Healer healer -> healer.setSpecial(newWeapon.attack());
                    case Mage mage -> mage.setSpecial(newWeapon.attack());
                    case Necromancer necromancer -> necromancer.setSpecial(newWeapon.attack());
                    default -> {
                    }
                }
            }

            personagem.setWeapon(newWeapon);

            slowConsole.imprimirDevagar(personagem.getName() + " empunha agora a nova arma: " + newWeapon.getName() + " com dano " + newWeapon.attack() + ", uma adição sinistra ao seu arsenal.");
        } else {
            slowConsole.imprimirDevagar(personagem.getName() + " decidiu não equipar a arma, como se um pressentimento de mal-estar o tivesse envolvido.");
        }
    }

    private Weapon[] getAvailableWeapons(Attributes personagem) {
        if (personagem instanceof Warrior) {
            return new Weapon[] {
                    new Zheiwender(),
                    new SwordOfThousandTruths()
            };
        } else if (personagem instanceof Rogue) {
            return new Weapon[] {
                    new Bayoneta(),
                    new Crossbow()
            };
        } else if (personagem instanceof Healer) {
            return new Weapon[] {
                    new Necronomicon(),
                    new Bible()
            };
        } else if (personagem instanceof Mage) {
            return new Weapon[] {
                    new StickOfTruth(),
                    new Wand()
            };
        } else if (personagem instanceof Necromancer) {
            return new Weapon[] {
                    new DaggersOfSouls(),
                    new DemonHearth()
            };
        }
        return new Weapon[0];
    }
}
