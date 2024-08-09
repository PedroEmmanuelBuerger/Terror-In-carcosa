package rpg.itens.Weapons;

import java.util.Random;

public class StickOfTruth implements Weapon {
    private final int attack;

    @Override
    public int getPrice() {
        return 15;
    }
    public StickOfTruth() {
        Random random = new Random();
        int minAttack = 10;
        int maxAttack = 20;
        this.attack = random.nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public int attack() {
        return attack;
    }

    @Override
    public String getName() {
        return "Cajado da Verdade";
    }
}
