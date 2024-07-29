package rpg.Monsters;

import rpg.Utils.SlowConsole;
import rpg.Classes.Attributes;

public class Mob extends Attributes {
    SlowConsole slowConsole = new SlowConsole();

    public Mob(String name, int healthbar, int attack, int specialAttack, String battleCry, int exp) {
        super(name, healthbar, attack, specialAttack, battleCry);
        this.setExp(exp); // Configura a experiência do mob
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
