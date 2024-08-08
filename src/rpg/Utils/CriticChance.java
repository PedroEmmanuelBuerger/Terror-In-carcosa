package rpg.Utils;

import java.util.Random;

public class CriticChance {
    private int damage;

    public CriticChance(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int chanceCritic() {
        Random random = new Random();
        SlowConsole slowConsole = new SlowConsole();
        double randomNumber = random.nextDouble() * 100.0;
        // Chance de ataque crítico em porcentagem
        double criticalChance = 25.0;
        if (randomNumber <= criticalChance) {
            slowConsole.imprimirDevagar("Seu ataque critou!");
            int criticalMultiplier = 2;
            return damage * criticalMultiplier;
        } else {
            return damage;
        }
    }
}
