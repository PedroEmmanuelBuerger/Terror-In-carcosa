package rpg.Monsters;

import rpg.Classes.Attributes;

public class Zombie extends Attributes {

    public Zombie(String name, int healthbar, int attack, int specialAttack, String battleCry) {
        super(name, healthbar, attack, specialAttack, battleCry);
    }
@Override
    public void attack(Attributes target) {
        int damage = this.getAttack();
        System.out.println(this.getName() + " avança e morde " + target.getName() + "!");
        target.takeDamage(damage);
    }
    @Override
    public void attackWithSpecial(Attributes target) {
        int damage = this.getSpecial();
        System.out.println(this.getName() + " tenta infectar " + target.getName() + " com seu ataque especial!");
        target.takeDamage(damage);
    }
}
