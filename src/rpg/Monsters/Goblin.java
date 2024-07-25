package rpg.Monsters;

import Utils.SlowConsole;
import rpg.Classes.Attributes;

public class Goblin extends Attributes {
    int exp = 5;
    SlowConsole slowConsole = new SlowConsole();

    public Goblin(String name, int healthbar, int attack, int specialAttack, String battleCry) {
        super(name, healthbar, attack, specialAttack, battleCry);
        this.setExp(5);
    }

    @Override
    public void attack(Attributes target) {
        int damage = this.getAttack();
        slowConsole.imprimirDevagar(this.getName() + " ataca " + target.getName() + " com suas garras afiadas, causando " + damage + " de dano!");
        target.takeDamage(damage);
    }

    @Override
    public void attackWithSpecial(Attributes target) {
        int damage = this.getSpecial();
        slowConsole.imprimirDevagar(this.getName() + " realiza um ataque especial contra " + target.getName() + ", causando " + damage + " de dano!");
        target.takeDamage(damage);
    }
}
