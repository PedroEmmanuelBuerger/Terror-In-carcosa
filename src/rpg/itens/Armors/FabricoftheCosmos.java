package rpg.itens.Armors;

import java.util.Random;

public class FabricoftheCosmos implements Armor {
    private final int defense;

    public FabricoftheCosmos() {
        Random random = new Random();
        int minDefense = 10;
        int maxDefense = 15;
        this.defense = random.nextInt((maxDefense - minDefense) + 1) + minDefense;
    }

    @Override
    public int armor() {
        return defense;
    }

    @Override
    public String getName() {
        return "Fabrico do Cosmos";
    }

    @Override
    public int getPrice() {
        return 200;
    }
}
