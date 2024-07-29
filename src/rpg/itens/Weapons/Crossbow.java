package rpg.itens.Weapons;

import java.util.Random;

public class Crossbow implements Weapon {
    private final int minAttack = 5;
    private final int maxAttack = 15; // Ajuste o intervalo conforme necessário
    private final String name = "Crossbow";

    @Override
    public int getAttack() {
        return new Random().nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public String getName() {
        return name;
    }
}
