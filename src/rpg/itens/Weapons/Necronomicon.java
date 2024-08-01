package rpg.itens.Weapons;

import java.util.Random;

public class Necronomicon implements Weapon {
    private final int attack; // Dano aleatório definido uma vez
    private final String name = "Livro dos Mortos: Necronomicon";
    private int price = 15;

    @Override
    public int getPrice() {
        return price;
    }
    public Necronomicon() {
        Random random = new Random();
        int minAttack = 18;
        int maxAttack = 28;
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
