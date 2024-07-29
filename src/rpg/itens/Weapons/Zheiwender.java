package rpg.itens.Weapons;

import java.util.Random;

public class Zheiwender implements Weapon {
    private final int attack; // Dano aleatório definido uma vez
    private final String name = "Zheiwender";

    public Zheiwender() {
        Random random = new Random();
        int minAttack = 5;
        int maxAttack = 10;
        this.attack = random.nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public int getAttack() {
        return attack; // Retorna o dano definido no construtor
    }

    @Override
    public String getName() {
        return name;
    }
}
