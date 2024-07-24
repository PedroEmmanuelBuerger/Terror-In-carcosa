package rpg.Monsters;

import rpg.Classes.Attributes;

public class Goblin extends Attributes {

    public Goblin(String name, int healthbar, int attack, int specialAttack, String battleCry) {
        super(name, healthbar, attack, specialAttack, battleCry);
    }
    @Override
    public void attack(Attributes target) {
        int damage = this.getAttack();
        System.out.println(this.getName() + " ataca " + target.getName() + " com suas garras afiadas, causando "+damage+" de dano!");
        target.takeDamage(damage);
    }
    @Override
    public void attackWithSpecial(Attributes target) {
        int damage = this.getSpecial();
        System.out.println(this.getName() + " realiza um ataque especial contra " + target.getName() + ", causando "+damage+" de dano!");
        target.takeDamage(damage);
    }
}
