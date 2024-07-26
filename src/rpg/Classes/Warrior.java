package rpg.Classes;

import rpg.Utils.SlowConsole;
import rpg.itens.Weapons.Initials.Axe;
import rpg.itens.Weapons.Weapon;

public class Warrior extends Attributes {
    private boolean defense = false;
    SlowConsole slowConsole = new SlowConsole();
    private Weapon weapon = new Axe(0);

    public Warrior(String name, int healthbar, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        setClasses("Warrior");
        setWeapon(weapon);
    }

    public boolean isDefese() {
        return defense;
    }

    public void setDefese(boolean defese) {
        this.defense = defese;
    }

    public void defend() {
        setDefese(true);
        slowConsole.imprimirDevagar(getName() + " entrou em posição de defesa!");
    }

    @Override
    public void takeDamage(int damage) {
        // Reduz a saúde do guerreiro atual pelo valor do dano recebido
        int currentHealth = this.getHealthbar();
        if (this.isDefese() == true) {
            this.setHealthbar(currentHealth - (damage / 2));
            slowConsole.imprimirDevagar(this.getName() + " defendeu, recebendo " + damage / 2 + " de dano!");
            this.setDefese(false);
            getHealth(this);
        } else {
            this.setHealthbar(currentHealth - damage);
            slowConsole.imprimirDevagar(this.getName() + " recebeu " + damage + " de dano!");
            getHealth(this);
        }
    }
}
