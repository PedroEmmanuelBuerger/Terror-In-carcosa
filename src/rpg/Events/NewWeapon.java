package rpg.Events;

import rpg.Classes.Attributes;
import rpg.Classes.Warrior;
import rpg.itens.Weapons.SwordOfThousandTruths;
import rpg.itens.Weapons.Weapon;
import rpg.itens.Weapons.Zheiwender;
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
        } else {
            slowConsole.imprimirDevagar(personagem.getName() + " não pode usar essa arma.");
        }
    }
}
