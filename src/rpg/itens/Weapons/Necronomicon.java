package rpg.itens.Weapons;

import java.util.Random;

public class Necronomicon implements Weapon {
    private final int minAttack = 50;
    private final int maxAttack = 100; // Ajuste o intervalo conforme necessário
    private final String name = "Livro dos Mortos: Necronomicon";

    @Override
    public int getAttack() {
        return new Random().nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public String getName() {
        return name;
    }
}
