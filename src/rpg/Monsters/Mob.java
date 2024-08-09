package rpg.Monsters;

import rpg.Utils.SlowConsole;
import rpg.Character.Classes.Attributes;

import java.util.Random;

public class Mob extends Attributes {
    SlowConsole slowConsole = new SlowConsole();
    private static final Random random = new Random();
    private final String portrait;

    public Mob(String name, int healthbar, int attack, int specialAttack, String battleCry, int exp, int gold, String portrait) {
        super(name, healthbar, attack, specialAttack, battleCry);
        this.setExp(exp); // Configura a experiência do mob
        this.setGold(generateRandomGold(gold)); // Configura o ouro com um valor aleatório baseado no parâmetro
        this.portrait = portrait;
    }

    @Override
    public void attack(Attributes target) {
        int damage = this.getAttack();
        slowConsole.imprimirDevagar(this.getName() + " ataca " + target.getName() + " com suas ferramentas afiadas, causando " + damage + " de dano!");
        target.takeDamage(damage);
    }

    @Override
    public void attackWithSpecial(Attributes target) {
        int damage = this.getSpecial();
        slowConsole.imprimirDevagar(this.getName() + " realiza um ataque especial contra " + target.getName() + ", causando " + damage + " de dano!");
        target.takeDamage(damage);
    }

    // Método auxiliar para gerar um valor aleatório de gold baseado em um intervalo
    private int generateRandomGold(int baseGold) {
        int minVal = baseGold - 5;
        int maxVal = baseGold + 5;
        int valFinal = random.nextInt((maxVal - minVal) + 1) + minVal;
        if (valFinal <= 0) {
            valFinal = 1;
        }
        return valFinal;
    }

    public void generateTechinicalInfo() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar(portrait);
        slowConsole.imprimirDevagar("Nome: " + this.getName());
        slowConsole.imprimirDevagar("Vida: " + this.getHealthbar());
        slowConsole.imprimirDevagar("Ataque: " + this.getAttack());
        slowConsole.imprimirDevagar("Ataque Especial: " + this.getSpecial());
        slowConsole.imprimirDevagar("Exp: " + this.getExp());
        slowConsole.imprimirDevagar("Gold: " + this.getGold());
    }
}
