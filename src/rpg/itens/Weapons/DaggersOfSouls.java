package rpg.itens.Weapons;

import java.util.Random;

public class DaggersOfSouls implements Weapon {
    private final int attack; // Dano aleatório definido uma vez
    private final String name = "Adagas da Alma";
    private int price = 15;

    @Override
    public int getPrice() {
        return price;
    }
    public DaggersOfSouls() {
        Random random = new Random();
        int minAttack = 15;
        int maxAttack = 23;
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
