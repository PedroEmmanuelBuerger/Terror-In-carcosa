package rpg.itens.Weapons;

import java.util.Random;

public class SwordOfThousandTruths implements Weapon {
    private final int minAttack = 15;
    private final int maxAttack = 25; // Ajuste o intervalo conforme necessário
    private final String name = "Espada de Mil Verdades";

    @Override
    public int getAttack() {
        return new Random().nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public String getName() {
        return name;
    }
}
