package rpg.itens.Weapons.Necromancer;

import rpg.itens.Weapons.Weapon;

import java.util.Random;

public class DemonHearth implements Weapon {
    private final int attack;

    @Override
    public int getPrice() {
        return 30;
    }
    public DemonHearth() {
        Random random = new Random();
        int minAttack = 13;
        int maxAttack = 17;
        this.attack = random.nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public int attack() {
        return attack;
    }

    @Override
    public String getName() {
        return "Coração de Demônio";
    }
}
