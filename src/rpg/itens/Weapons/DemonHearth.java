package rpg.itens.Weapons;

import java.util.Random;

public class DemonHearth implements Weapon {
    private final int minAttack = 20;
    private final int maxAttack = 30; // Ajuste o intervalo conforme necessário
    private final String name = "Coração de Demônio";

    @Override
    public int getAttack() {
        return new Random().nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public String getName() {
        return name;
    }
}
