package rpg.Character.Classes;

import rpg.Utils.SlowConsole;
import rpg.itens.Weapons.Initials.Axe;
import rpg.itens.Weapons.Weapon;

public class Warrior extends Attributes {
    private boolean defense = false;
    private final SlowConsole slowConsole = new SlowConsole();

    public Warrior(String name, int healthbar, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        setClasses("Guerreiro das Trevas");
        Weapon weapon = new Axe(0);
        setWeapon(weapon);
        this.setMaxHealthInitial(healthbar);
    }

    public boolean isDefese() {
        return defense;
    }

    public void setDefese(boolean defense) {
        this.defense = defense;
    }

    public void defend() {
        setDefese(true);
        slowConsole.imprimirDevagar(getName() + " assumiu uma postura defensiva, preparando-se para resistir aos horrores que se aproximam!");
    }

    @Override
    public void takeDamage(int damage) {
        int currentHealth = this.getHealthbar();
        damage = damage - this.getArmor().armor();
        if (this.isDefese()) {
            int reducedDamage = damage / 2;
            this.setHealthbar(currentHealth - reducedDamage);
            slowConsole.imprimirDevagar(getName() + " defendeu com bravura, reduzindo o dano para " + reducedDamage + "!");
            this.setDefese(false);
            getHealth(this);
        } else {
            this.setHealthbar(currentHealth - damage);
            slowConsole.imprimirDevagar(getName() + " sofreu " + damage + " de dano, enfrentando o caos!");
            getHealth(this);
        }
    }
}
