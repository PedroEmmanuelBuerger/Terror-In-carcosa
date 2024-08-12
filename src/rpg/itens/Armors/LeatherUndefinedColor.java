package rpg.itens.Armors;

import java.util.Random;

public class LeatherUndefinedColor implements Armor {
    private final int defense;

    public LeatherUndefinedColor() {
        Random random = new Random();
        int minDefense = 2;
        int maxDefense = 6;
        this.defense = random.nextInt((maxDefense - minDefense) + 1) + minDefense;
    }

    @Override
    public int armor() {
        return defense;
    }

    @Override
    public String getName() {
        return "Couro de Cor Indefinida";
    }

    @Override
    public int getPrice() {
        return 30;
    }
}
