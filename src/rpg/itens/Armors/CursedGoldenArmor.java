package rpg.itens.Armors;

import java.util.Random;

public class CursedGoldenArmor implements Armor {
    private final int defense;

    public CursedGoldenArmor() {
        Random random = new Random();
        int minDefense = 10;
        int maxDefense = 20;
        this.defense = random.nextInt((maxDefense - minDefense) + 1) + minDefense;
    }

    @Override
    public int armor() {
        return defense;
    }

    @Override
    public String getName() {
        return "Armadura Dourada Amaldiçoada";
    }

    @Override
    public int getPrice() {
        return 300;
    }
}
