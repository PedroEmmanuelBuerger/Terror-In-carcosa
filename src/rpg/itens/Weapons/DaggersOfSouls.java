package rpg.itens.Weapons;

import java.util.Random;

public class DaggersOfSouls implements Weapon {
    private final int attack; // Dano aleatório definido uma vez

    @Override
    public int getPrice() {
        return 15;
    }
    public DaggersOfSouls() {
        Random random = new Random();
        int minAttack = 15;
        int maxAttack = 23;
        this.attack = random.nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public int attack() {
        return attack; // Retorna o dano definido no construtor
    }

    @Override
    public String getName() {
        return "Adagas da Alma";
    }
}
