package rpg.itens.Weapons.Mage;

import rpg.itens.Weapons.Weapon;

import java.util.Random;

public class Wand implements Weapon {
    private final int attack;

    @Override
    public int getPrice() {
        return 25;
    }
    public Wand() {
        Random random = new Random();
        int minAttack = 8;
        int maxAttack = 14;
        this.attack = random.nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public int attack() {
        return attack;
    }

    @Override
    public String getName() {
        return "Varinha";
    }
}
