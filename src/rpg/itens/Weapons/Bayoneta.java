package rpg.itens.Weapons;

import java.util.Random;

public class Bayoneta implements Weapon {
    private final int attack; // Dano aleatório definido uma vez
    private final String name = "Bayoneta";
    private int price = 15;

    @Override
    public int getPrice() {
        return price;
    }

    // Construtor da classe que define o dano aleatório
    public Bayoneta() {
        Random random = new Random();
        int minAttack = 8;
        int maxAttack = 15;
        // Define o valor do ataque aleatório apenas uma vez no momento da criação
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
