package rpg.itens.Armors;

import java.util.Random;

public class UltradimensionalOctopusSkin implements Armor {
    private final int defense;

    public UltradimensionalOctopusSkin() {
        Random random = new Random();
        int minDefense = 6;
        int maxDefense = 10;
        this.defense = random.nextInt((maxDefense - minDefense) + 1) + minDefense;
    }

    @Override
    public int armor() {
        return defense;
    }

    @Override
    public String getName() {
        return "Pele de Polvo Ultradimensional";
    }

    @Override
    public int getPrice() {
        return 120;
    }
}
