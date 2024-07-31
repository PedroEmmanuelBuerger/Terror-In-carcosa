package rpg.Monsters;

import rpg.Utils.SlowConsole;
import rpg.Classes.Attributes;

public class Mob extends Attributes {
    private static final int GOLD_VARIATION = 5; // Variação de ouro (±5)
    private int baseGoldDrop; // Valor base de ouro que o mob pode soltar
    private SlowConsole slowConsole = new SlowConsole();

    public Mob(String name, int healthbar, int attack, int specialAttack, String battleCry, int exp, int baseGoldDrop) {
        super(name, healthbar, attack, specialAttack, battleCry);
        this.setExp(exp);
        this.baseGoldDrop = baseGoldDrop; // Inicializa o valor base de ouro
    }

    @Override
    public void attack(Attributes target) {
        int damage = this.getAttack();
        slowConsole.imprimirDevagar(this.getName() + " ataca " + target.getName() + " com suas garras afiadas, causando " + damage + " de dano!");
        target.takeDamage(damage);
    }

    @Override
    public void attackWithSpecial(Attributes target) {
        int damage = this.getSpecial();
        slowConsole.imprimirDevagar(this.getName() + " realiza um ataque especial contra " + target.getName() + ", causando " + damage + " de dano!");
        target.takeDamage(damage);
    }

    public int getGoldDrop() {
        // Calcula o intervalo de ouro com base no valor base e na variação
        int minGold = baseGoldDrop - GOLD_VARIATION;
        int maxGold = baseGoldDrop + GOLD_VARIATION;

        // Gera um valor aleatório de ouro dentro do intervalo especificado
        return minGold + (int) (Math.random() * (maxGold - minGold + 1));
    }
}
