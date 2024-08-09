package rpg.itens.Weapons;

import java.util.Random;

public class Bayoneta implements Weapon {
    private final int attack;

    @Override
    public int getPrice() {
        return 13;
    }

    public Bayoneta() {
        Random random = new Random();
        int minAttack = 8;
        int maxAttack = 15;
        this.attack = random.nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public int attack() {
        return attack;
    }

    @Override
    public String getName() {
        return "Bayoneta";
    }
}
