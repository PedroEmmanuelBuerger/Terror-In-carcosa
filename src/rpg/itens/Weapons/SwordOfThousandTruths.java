package rpg.itens.Weapons;

import java.util.Random;

public class SwordOfThousandTruths implements Weapon {
    private final int attack;

    @Override
    public int getPrice() {
        return 50;
    }
    public SwordOfThousandTruths() {
        Random random = new Random();
        int minAttack = 15;
        int maxAttack = 23;
        this.attack = random.nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public int attack() {
        return attack;
    }

    @Override
    public String getName() {
        return "Espada de Mil Verdades";
    }
}
