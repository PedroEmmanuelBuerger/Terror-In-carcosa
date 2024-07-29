package rpg.itens.Weapons;

import java.util.Random;

public class StickOfTruth implements Weapon {
    private final int minAttack = 10;
    private final int maxAttack = 20; // Ajuste o intervalo conforme necessário
    private final String name = "Cajado da Verdade";

    @Override
    public int getAttack() {
        return new Random().nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public String getName() {
        return name;
    }
}
