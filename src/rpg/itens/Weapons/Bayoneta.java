package rpg.itens.Weapons;

import java.util.Random;

public class Bayoneta implements Weapon {
    private final int minAttack = 5;
    private final int maxAttack = 10; // Ajuste o intervalo conforme necessário
    private final String name = "Bayoneta";

    @Override
    public int getAttack() {
        return new Random().nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public String getName() {
        return name;
    }
}
