package rpg.Classes;

import Utils.ManaAdm;
import Utils.SlowConsole;
import rpg.itens.SpeelBook;

public class Mage extends Attributes {
    private int mana;
    SlowConsole slowConsole = new SlowConsole();
    public Mage(String name, int healthbar, int Mana, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        this.mana = Mana;
        this.setClasses("Mage");
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public void attack(Attributes enemy) {
        int damage = this.getAttack();
        slowConsole.imprimirDevagar(this.getName() + " lançou uma bola de fogo em " + enemy.getName() + " causando " + damage + " de dano!");
        enemy.takeDamage(damage);
    }

    @Override
    public void attackWithSpecial(Attributes enemy) {
        SpeelBook speelBook = new SpeelBook();
        int damageSpell = speelBook.selectSpell(this);
        enemy.takeDamage(this.getSpecial() + damageSpell);
        if (!enemy.isAlive()) {
            slowConsole.imprimirDevagar("Vida total de " + enemy.getName() + " é 0");
            slowConsole.imprimirDevagar(enemy.getName() + " foi derrotado!");
            gainExp(enemy.getLevel() * 10);
        }
    }
    @Override
    public void getTechnicalInfo() {
        slowConsole.imprimirDevagar("Nome: " + getName());
        slowConsole.imprimirDevagar("Classe: " + getClasses());
        slowConsole.imprimirDevagar("Vida: " + getHealthbar());
        slowConsole.imprimirDevagar("Ataque: " + getAttack());
        slowConsole.imprimirDevagar("Ataque Mágico: " + getSpecial());
        slowConsole.imprimirDevagar("Mana: " + getMana());
        slowConsole.imprimirDevagar("Frase: " + getQuote());
    }
}
