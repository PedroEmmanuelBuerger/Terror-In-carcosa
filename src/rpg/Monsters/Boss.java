package rpg.Monsters;

import Utils.SlowConsole;
import rpg.Classes.Attributes;

public class Boss extends Attributes {
    SlowConsole slowConsole = new SlowConsole();

    public Boss(String name, int healthbar, int attack, int specialAttack, String battleCry) {
        super(name, healthbar, attack, specialAttack, battleCry);
    }

    @Override
    public void attack(Attributes target) {
        int damage = this.getAttack();
        slowConsole.imprimirDevagar(this.getName() + " ataca " + target.getName() + " com força, causando " + damage + " de dano!");
        target.takeDamage(damage);
    }

    @Override
    public void attackWithSpecial(Attributes target) {
        int damage = this.getSpecial();
        slowConsole.imprimirDevagar(this.getName() + " usa um ataque especial contra " + target.getName() + " causando " + damage + " de dano!!");
        target.takeDamage(damage);
    }
}
