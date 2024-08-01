package rpg.Character.Classes;

import rpg.Utils.CriticChance;
import rpg.Utils.SlowConsole;
import rpg.itens.Specials.SpeelBook;
import rpg.itens.Weapons.Initials.Staff;
import rpg.itens.Weapons.Weapon;

public class Mage extends Attributes {
    private SpeelBook speelBook;
    private int mana;
    private int maxMana;
    SlowConsole slowConsole = new SlowConsole();
    private Weapon weapon = new Staff(0);
    public Mage(String name, int healthbar, int mana, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        this.mana = mana;
        this.maxMana = mana;
        this.speelBook = new SpeelBook(); // Inicializa o SpeelBook
        this.setClasses("Mago");
        setWeapon(weapon);
    }

    public SpeelBook getSpeelBook() {
        return speelBook;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
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
        CriticChance criticChance = new CriticChance(damage);
        damage = criticChance.chanceCritic(); // Corrigir chamada ao método chanceCritic
        slowConsole.imprimirDevagar(this.getName() + " lançou uma bola de fogo em " + enemy.getName() + " causando " + damage + " de dano!");
        enemy.takeDamage(damage);
        if (!enemy.isAlive()) {
            slowConsole.imprimirDevagar("Vida total de " + enemy.getName() + " é 0");
            slowConsole.imprimirDevagar(enemy.getName() + " foi derrotado!");
            slowConsole.imprimirDevagar("Você ganhou " + enemy.getExp() + " de EXP!");
            slowConsole.imprimirDevagar(enemy.getName() + " Derrubou " + enemy.getGold() + " De Ouro!");
            gainGold(enemy.getGold());
            gainExp(enemy.getExp()); // Ganha experiência baseada no nível do inimigo
        }
    }

    @Override
    public void attackWithSpecial(Attributes enemy) {
        int damageSpell = speelBook.selectSpell(this);
        CriticChance criticChance = new CriticChance(damageSpell);
        damageSpell = criticChance.chanceCritic();
        if (damageSpell != 0) {
            enemy.takeDamage(this.getSpecial() + damageSpell);
            if (!enemy.isAlive()) {
                slowConsole.imprimirDevagar("Vida total de " + enemy.getName() + " é 0");
                slowConsole.imprimirDevagar(enemy.getName() + " foi derrotado!");
                slowConsole.imprimirDevagar("Você ganhou " + enemy.getExp() + " de EXP!");
                slowConsole.imprimirDevagar(enemy.getName() + " Derrubou " + enemy.getGold() + " De Ouro!");
                gainGold(enemy.getGold());
                gainExp(enemy.getExp());
            }
        }
    }

    @Override
    public void getTechnicalInfo() {
        super.getTechnicalInfo();
        slowConsole.imprimirDevagar("Mana: " + getMana());
    }

    @Override
    public void gainExp(int expGain) {
        setExp(getExp() + expGain); // Adiciona a experiência ganha

        // Verifica se o personagem subiu de nível
        while (getExp() >= getLevel() * 10) {
            int levelsGained = getExp() / (getLevel() * 10); // Quantos níveis foram ganhos
            setExp(getExp() % (getLevel() * 7)); // Experiência restante após subir de nível

            // Aumenta o nível do personagem
            setLevel(getLevel() + levelsGained);
            slowConsole.imprimirDevagar(getName() + " upou de nível! Nível atual é: " + getLevel());

            // Aumenta os atributos ao subir de nível
            setSpecial(getSpecial() + 5 * levelsGained);
            slowConsole.imprimirDevagar(getName() + " Aumentou " + (5 * levelsGained) + " de ataque Special!");
            setHealthbar(getHealthbar() + 5 * levelsGained);
            slowConsole.imprimirDevagar(getName() + " Aumentou " + (5 * levelsGained) + " de vida!");
            setMana(getMana() + 5 * levelsGained);
            setMaxMana(getMaxMana() + 5);
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
}
