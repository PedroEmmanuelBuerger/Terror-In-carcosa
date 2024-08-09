package rpg.itens.Weapons;

import java.util.Random;

public class Necronomicon implements Weapon {
    private final int attack;

    @Override
    public int getPrice() {
        return 1000;
    }
    public Necronomicon() {
        Random random = new Random();
        int minAttack = 18;
        int maxAttack = 28;
        this.attack = random.nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public int attack() {
        return attack;
    }

    @Override
    public String getName() {
        return "Livro dos Mortos: Necronomicon";
    }
}
