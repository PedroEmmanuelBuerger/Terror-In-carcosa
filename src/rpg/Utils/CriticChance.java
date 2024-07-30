package rpg.Utils;

import java.util.Random;

public class CriticChance {
    private int damage;
    private double criticalChance = 15.0; // Chance de ataque crítico em porcentagem
    private int criticalMultiplier = 2;

    public CriticChance(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getCriticalChance() {
        return criticalChance;
    }

    public void setCriticalChance(double criticalChance) {
        this.criticalChance = criticalChance;
    }

    public int getCriticalMultiplier() {
        return criticalMultiplier;
    }

    public void setCriticalMultiplier(int criticalMultiplier) {
        this.criticalMultiplier = criticalMultiplier;
    }

    public int chanceCritic() {
        Random random = new Random();
        SlowConsole slowConsole = new SlowConsole();
        double randomNumber = random.nextDouble() * 100.0;
        if (randomNumber <= this.criticalChance) {
            slowConsole.imprimirDevagar("Seu ataque critou!");
            return damage * this.criticalMultiplier;
        } else {
            return damage;
        }
    }
}
