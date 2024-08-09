package rpg.Character.Classes;

import rpg.Utils.SlowConsole;
import rpg.itens.Weapons.Initials.Daggers;
import rpg.itens.Weapons.Weapon;

import java.util.Random;

public class Rogue extends Attributes {
    private static final double BASE_DODGE_SKILLS = 25.0;
    private double dodgeSkills;
    private final SlowConsole slowConsole = new SlowConsole();
    private final Random random = new Random();

    public Rogue(String name, int healthbar, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        this.setClasses("Sombra Oculta");
        Weapon weapon = new Daggers(0);
        setWeapon(weapon);
        this.dodgeSkills = BASE_DODGE_SKILLS;
    }

    public double getDodgeSkills() {
        return dodgeSkills;
    }

    public void setDodgeSkills(double dodgeSkills) {
        this.dodgeSkills = dodgeSkills;
    }

    @Override
    public void takeDamage(int damage) {
        double randomNumber = random.nextDouble() * 100.0;
        int currentHealth = this.getHealthbar();
        if (randomNumber <= dodgeSkills) {
            slowConsole.imprimirDevagar(getName() + " escorreu para as sombras e desviou do ataque!");
        } else {
            this.setHealthbar(currentHealth - damage);
            slowConsole.imprimirDevagar(getName() + " foi atingido e sofreu " + damage + " de dano!");
            getHealth(this);
            if (this.getHealthbar() <= 0) {
                slowConsole.imprimirDevagar(getName() + " caiu nas trevas eternas...");
            }
        }
    }
}
