package rpg.itens.Weapons;

import java.util.Random;

public class Zheiwender implements Weapon {
    private final int attack; // Dano aleatório definido uma vez

    @Override
    public int getPrice() {
        return 15;
    }
    public Zheiwender() {
        Random random = new Random();
        int minAttack = 5;
        int maxAttack = 10;
        this.attack = random.nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public int attack() {
        return attack; // Retorna o dano definido no construtor
    }

    @Override
    public String getName() {
        return "Zheiwender";
    }
}
