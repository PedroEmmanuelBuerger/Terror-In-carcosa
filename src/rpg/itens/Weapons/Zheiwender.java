package rpg.itens.Weapons;

import java.util.Random;

public class Zheiwender implements Weapon {
    private final int minAttack = 5;
    private final int maxAttack = 10;
    private String Name = "Zheiwender";

    @Override
    public int getAttack() {
        return new Random().nextInt((maxAttack - minAttack) + 1) + minAttack;
    }

    @Override
    public String getName() {
        return Name;
    }
}
