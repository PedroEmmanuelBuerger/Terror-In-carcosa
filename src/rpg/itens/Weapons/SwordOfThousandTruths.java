package rpg.itens.Weapons;

import java.util.Random;

public class SwordOfThousandTruths implements Weapon {
    private final int attack; // Dano aleatório definido uma vez
    private final String name = "Espada de Mil Verdades";

    public SwordOfThousandTruths() {
        Random random = new Random();
        int minAttack = 15;
        int maxAttack = 25;
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
