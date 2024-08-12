package rpg.itens.Armors;

import java.util.Random;

public class RuinedKing implements Armor {
    private final int defense;

    public RuinedKing() {
        Random random = new Random();
        int minDefense = 500;
        int maxDefense = 999;
        this.defense = random.nextInt((maxDefense - minDefense) + 1) + minDefense;
    }

    @Override
    public int armor() {
        return defense;
    }

    @Override
    public String getName() {
        return "Ruined King";
    }

    @Override
    public int getPrice() {
        return 30;
    }
}
