package rpg.Classes;

import Utils.SlowConsole;
import rpg.Classes.Attributes;
import rpg.itens.SpeelBook;

public class Mage extends Attributes {
    private int mana;
    SlowConsole slowConsole = new SlowConsole();

    public Mage(String name, int healthbar, int mana, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        this.mana = mana;
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
        if (damageSpell != 0) {
            enemy.takeDamage(this.getSpecial() + damageSpell);
            if (!enemy.isAlive()) {
                slowConsole.imprimirDevagar("Vida total de " + enemy.getName() + " é 0");
                slowConsole.imprimirDevagar(enemy.getName() + " foi derrotado!");
                gainExp(enemy.getLevel() * 10);
            }
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

    @Override
    public void gainExp(int expGain) {
        setExp(getExp() + expGain); // Adiciona a experiência ganha

        // Verifica se o personagem subiu de nível
        while (getExp() >= getLevel() * 10) {
            int levelsGained = getExp() / (getLevel() * 10); // Quantos níveis foram ganhos
            setExp(getExp() % (getLevel() * 10)); // Experiência restante após subir de nível

            // Aumenta o nível do personagem
            setLevel(getLevel() + levelsGained);
            slowConsole.imprimirDevagar(getName() + " upou de nível! Nível atual é: " + getLevel());

            // Aumenta os atributos ao subir de nível
            setSpecial(getSpecial() + 5 * levelsGained);
            slowConsole.imprimirDevagar(getName() + " Aumentou " + (5 * levelsGained) + " de ataque Special!");
            setHealthbar(getHealthbar() + 5 * levelsGained);
            slowConsole.imprimirDevagar(getName() + " Aumentou " + (5 * levelsGained) + " de vida!");
            setMana(getMana() + 5 * levelsGained);
            slowConsole.imprimirDevagar(getName() + " Aumentou " + (5 * levelsGained) + " de Mana!");
        }
    }

    public void recoverMana(int amount) {
        int maxMana = getMaxMana();
        if (this.mana + amount <= maxMana) {
            this.mana += amount;
        } else {
            this.mana = maxMana;
        }
    }

    public int getMaxMana() {
        // Define o máximo de mana com base no nível do personagem
        return getLevel() * 10;
    }
}
