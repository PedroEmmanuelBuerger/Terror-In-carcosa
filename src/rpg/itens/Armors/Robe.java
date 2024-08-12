package rpg.itens.Armors;

import java.util.Random;

public class Robe implements Armor {
    private final int defense;
    private final int mana;

    public Robe() {
        Random random = new Random();
        int minDefense = 3;
        int maxDefense = 7;
        this.defense = random.nextInt((maxDefense - minDefense) + 1) + minDefense;
        this.mana = 10;
    }

    @Override
    public int armor() {
        return defense;
    }

    @Override
    public String getName() {
        return "Robe Místico";
    }

    @Override
    public int getPrice() {
        return 50;
    }

    @Override
    public int getMana() {
        return mana;
    }
}
