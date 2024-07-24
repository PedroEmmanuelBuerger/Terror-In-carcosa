package rpg.Monsters;

import rpg.Classes.Attributes;

public class Boss extends Attributes {
    public Boss(String name, int healthbar, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
    }

    @Override
    public void attackWithSpecial(Attributes enemy) {
        int damage = this.getSpecial();
        System.out.println(this.getName() + " atacou " + enemy.getName() + " com um ataque especial, causando " + damage + " de dano!");
        enemy.takeDamage(damage);
        if (enemy.getHealthbar() <= 0) {
            System.out.println(this.getName() + ":" + this.getQuote());
        }
    }
}
