package rpg.Events;

import rpg.Classes.*;
import rpg.itens.Weapons.*;
import rpg.itens.Weapons.SwordOfThousandTruths; // Importa a nova arma
import rpg.Utils.SlowConsole;

import java.util.Random;
import java.util.Scanner;

public class NewWeapon implements NonCombatEvent {
    private SlowConsole slowConsole = new SlowConsole();

    @Override
    public void executeEvent(Attributes personagem) {
        // Verifica se o personagem é um Warrior ou qualquer outra classe que pode usar a arma
        if (personagem instanceof Warrior) {
            Warrior warrior = (Warrior) personagem;

            // Cria uma lista de armas disponíveis
            Weapon[] weapons = {
                    new Zheiwender(5), // Dano da arma é 5
                    new SwordOfThousandTruths(15) // Dano da nova arma é 15
            };

            // Seleciona aleatoriamente uma arma da lista
            Random random = new Random();
            Weapon newWeapon = weapons[random.nextInt(weapons.length)];

            slowConsole.imprimirDevagar("Você achou uma nova arma: " + newWeapon.getName() + " com dano (" + newWeapon.getAttack() + "). Deseja equipá-la? (s/n)");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("s")) {
                warrior.setWeapon(newWeapon);
                // Atualiza o ataque considerando a nova arma
                warrior.setAttack((warrior.getAttack() + newWeapon.getAttack()) - warrior.getWeapon().getAttack());

                slowConsole.imprimirDevagar(personagem.getName() + " equipou a nova arma: " + newWeapon.getName() + " com dano " + newWeapon.getAttack() + "!");
            } else {
                slowConsole.imprimirDevagar(personagem.getName() + " decidiu não equipar a arma.");
            }
        }
        if (personagem instanceof Rogue) {
            Rogue rogue = (Rogue) personagem;

            Weapon[] weapons = {
                    new Bayoneta(8), // Dano da arma é 5
                    new Crossbow(5) // Dano da nova arma é 15
            };

            Random random = new Random();
            Weapon newWeapon = weapons[random.nextInt(weapons.length)];

            slowConsole.imprimirDevagar("Você achou uma nova arma: " + newWeapon.getName() + " com dano (" + newWeapon.getAttack() + "). Deseja equipá-la? (s/n)");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("s")) {
                rogue.setWeapon(newWeapon);
                // Atualiza o ataque considerando a nova arma
                rogue.setAttack((rogue.getAttack() + newWeapon.getAttack()) - rogue.getWeapon().getAttack());

                slowConsole.imprimirDevagar(personagem.getName() + " equipou a nova arma: " + newWeapon.getName() + " com dano " + newWeapon.getAttack() + "!");
            } else {
                slowConsole.imprimirDevagar(personagem.getName() + " decidiu não equipar a arma.");
            }
        }
        if (personagem instanceof Healer) {
            Healer healer = (Healer) personagem;

            Weapon[] weapons = {
                    new Necronomicon(99), // Dano da arma é 5
                    new Bible(15) // Dano da nova arma é 15
            };

            Random random = new Random();
            Weapon newWeapon = weapons[random.nextInt(weapons.length)];

            slowConsole.imprimirDevagar("Você achou uma nova arma: " + newWeapon.getName() + " com dano (" + newWeapon.getAttack() + "). Deseja equipá-la? (s/n)");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("s")) {
                healer.setWeapon(newWeapon);
                // Atualiza o ataque considerando a nova arma
                healer.setSpecial((healer.getSpecial() + newWeapon.getAttack()) - healer.getWeapon().getAttack());

                slowConsole.imprimirDevagar(personagem.getName() + " equipou a nova arma: " + newWeapon.getName() + " com dano " + newWeapon.getAttack() + "!");
            } else {
                slowConsole.imprimirDevagar(personagem.getName() + " decidiu não equipar a arma.");
            }
        }
        if (personagem instanceof Mage) {
            Mage mage = (Mage) personagem;

            Weapon[] weapons = {
                    new StickOfTruth(32), // Dano da arma é 5
                    new Wand(13) // Dano da nova arma é 15
            };

            Random random = new Random();
            Weapon newWeapon = weapons[random.nextInt(weapons.length)];

            slowConsole.imprimirDevagar("Você achou uma nova arma: " + newWeapon.getName() + " com dano (" + newWeapon.getAttack() + "). Deseja equipá-la? (s/n)");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("s")) {
                mage.setWeapon(newWeapon);
                // Atualiza o ataque considerando a nova arma
                mage.setSpecial((mage.getSpecial() + newWeapon.getAttack()) - mage.getWeapon().getAttack());

                slowConsole.imprimirDevagar(personagem.getName() + " equipou a nova arma: " + newWeapon.getName() + " com dano " + newWeapon.getAttack() + "!");
            } else {
                slowConsole.imprimirDevagar(personagem.getName() + " decidiu não equipar a arma.");
            }
        }
    }
}
