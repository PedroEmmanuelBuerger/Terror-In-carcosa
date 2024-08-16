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
        int reducedDamage = damage - (this.getArmor() != null ? this.getArmor().armor() : 0);

        if (reducedDamage <= 0) {
            slowConsole.imprimirDevagar("A armadura de " + this.getName() + " resistiu ao ataque!");
            return;
        }

        if (this.isDefese()) {
            reducedDamage /= 2;
            slowConsole.imprimirDevagar(getName() + " defendeu com bravura, reduzindo o dano para " + reducedDamage + "!");
            this.setDefese(false);
        }

        this.setHealthbar(currentHealth - reducedDamage);
        slowConsole.imprimirDevagar(getName() + " sofreu " + reducedDamage + " de dano, enfrentando o caos!");
        getHealth(this);
    }
}
