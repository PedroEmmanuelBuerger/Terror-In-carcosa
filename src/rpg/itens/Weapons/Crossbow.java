package rpg.itens.Weapons;

import java.util.Random;

public class Crossbow implements Weapon {
    private final int attack; // Dano aleatório definido uma vez

    @Override
    public int getPrice() {
        return 15;
    }
    public Crossbow() {
        Random random = new Random();
        int minAttack = 7;
        int maxAttack = 15;
        this.attack = random.nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public int attack() {
        return attack; // Retorna o dano definido no construtor
    }

    @Override
    public String getName() {
        return "Crossbow";
    }
}
