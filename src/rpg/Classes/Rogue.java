package rpg.Classes;

import rpg.Utils.SlowConsole;

import java.util.Random;

public class Rogue extends Attributes {
    SlowConsole slowConsole = new SlowConsole();

    public Rogue(String name, int healthbar, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        this.setClasses("Rogue");
        this.setWeapon("Adagas");
    }

    Double dodgeSkills = 25.0;
    Random random = new Random();

    @Override
    public void takeDamage(int damage) {
        double randomNumber = random.nextDouble() * 100.0;
        int currentHealth = this.getHealthbar();
        if (randomNumber <= dodgeSkills) {
            slowConsole.imprimirDevagar(getName() + " Desviou!");
        } else {
            this.setHealthbar(currentHealth - damage);
            slowConsole.imprimirDevagar(this.getName() + " recebeu " + damage + " de dano!");
            getHealth(this);
            if (this.getHealthbar() <= 0) {
                slowConsole.imprimirDevagar(this.getName() + " foi derrotado!");
            }
        }
    }
}
