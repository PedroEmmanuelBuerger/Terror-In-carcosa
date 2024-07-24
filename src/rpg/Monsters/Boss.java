package rpg.Monsters;

import rpg.Classes.Attributes;

public class Boss extends Attributes {

    public Boss(String name, int healthbar, int attack, int specialAttack, String battleCry) {
        super(name, healthbar, attack, specialAttack, battleCry);
    }
    @Override
    public void attack(Attributes target) {
        int damage = this.getAttack();
        System.out.println(this.getName() + " ataca " + target.getName() + " com força, causando "+damage+" de dano!");
        target.takeDamage(damage);
    }
    @Override
    public void attackWithSpecial(Attributes target) {
        int damage = this.getSpecial();
        System.out.println(this.getName() + " usa um ataque especial contra " + target.getName() + " causando "+damage+" de dano!!");
        target.takeDamage(damage);
    }
}
