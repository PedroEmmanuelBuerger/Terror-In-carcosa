package rpg.itens.Weapons;

import java.util.Random;

public class Bible implements Weapon {
    private final int attack; // Dano aleatório definido uma vez
    private final String name = "Bíblia Sagrada";
    private int price = 15;

    @Override
    public int getPrice() {
        return price;
    }
    public Bible() {
        Random random = new Random();
        int minAttack = 8;
        int maxAttack = 17;
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
