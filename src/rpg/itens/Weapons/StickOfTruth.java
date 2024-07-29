package rpg.itens.Weapons;

import java.util.Random;

public class StickOfTruth implements Weapon {
    private final int attack; // Dano aleatório definido uma vez
    private final String name = "Cajado da Verdade";

    public StickOfTruth() {
        Random random = new Random();
        int minAttack = 10;
        int maxAttack = 20;
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
