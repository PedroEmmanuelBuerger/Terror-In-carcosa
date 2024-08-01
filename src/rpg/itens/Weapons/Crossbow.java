package rpg.itens.Weapons;

import java.util.Random;

public class Crossbow implements Weapon {
    private final int attack; // Dano aleatório definido uma vez
    private final String name = "Crossbow";
    private int price = 15;

    @Override
    public int getPrice() {
        return price;
    }
    public Crossbow() {
        Random random = new Random();
        int minAttack = 7;
        int maxAttack = 15;
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
