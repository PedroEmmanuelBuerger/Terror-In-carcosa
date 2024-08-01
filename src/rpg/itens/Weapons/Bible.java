package rpg.itens.Weapons;

import java.util.Random;

public class Bible implements Weapon {
    private final int attack; // Dano aleatório definido uma vez

    @Override
    public int getPrice() {
        return 15;
    }
    public Bible() {
        Random random = new Random();
        int minAttack = 8;
        int maxAttack = 17;
        this.attack = random.nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public int attack() {
        return attack; // Retorna o dano definido no construtor
    }

    @Override
    public String getName() {
        return "Bíblia Sagrada";
    }
}
