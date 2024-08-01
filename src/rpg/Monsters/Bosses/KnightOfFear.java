package rpg.Monsters.Bosses;

import rpg.Utils.SlowConsole;
import rpg.Character.Classes.Attributes;

public class KnightOfFear extends Attributes {
    SlowConsole slowConsole = new SlowConsole();
    public KnightOfFear(String name, int healthbar, int attack, int specialAttack, String battleCry) {
        super(name, healthbar, attack, specialAttack, battleCry);
        this.setExp(150);
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
