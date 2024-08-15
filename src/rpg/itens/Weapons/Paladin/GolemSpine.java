package rpg.itens.Weapons.Paladin;

import rpg.itens.Weapons.Weapon;

import java.util.Random;

public class GolemSpine implements Weapon {
    private final int attack;

    @Override
    public int getPrice() {
        return 25;
    }
    public GolemSpine() {
        Random random = new Random();
        int minAttack = 35;
        int maxAttack = 43;
        this.attack = random.nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public int attack() {
        return attack;
    }

    @Override
    public String getName() {
        return "Espinha de gigante";
    }
}
