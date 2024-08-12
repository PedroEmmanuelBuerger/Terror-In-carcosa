package rpg.itens.Armors;

import java.util.Random;

public class DemonCarcass implements Armor {
    private final int defense;

    public DemonCarcass() {
        Random random = new Random();
        int minDefense = 7;
        int maxDefense = 12;
        this.defense = random.nextInt((maxDefense - minDefense) + 1) + minDefense;
    }

    @Override
    public int armor() {
        return defense;
    }

    @Override
    public String getName() {
        return "Carcaça Demoníaca";
    }

    @Override
    public int getPrice() {
        return 150;
    }
}
