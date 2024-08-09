package rpg.Character.Classes;

import rpg.Utils.CriticChance;
import rpg.Utils.SlowConsole;
import rpg.itens.Specials.SpeelBook;
import rpg.itens.Weapons.Initials.Staff;
import rpg.itens.Weapons.Weapon;

public class Mage extends Attributes {
    private final SpeelBook speelBook;
    private int mana;
    private int maxMana;
    SlowConsole slowConsole = new SlowConsole();

    public Mage(String name, int healthbar, int mana, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        this.mana = mana;
        this.maxMana = mana;
        this.speelBook = new SpeelBook();
        this.setClasses("Arcanista das Sombras");
        Weapon weapon = new Staff(0);
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
        damage = criticChance.chanceCritic();
        slowConsole.imprimirDevagar(this.getName() + " lançou uma esfera de fogo negra contra " + enemy.getName() + ", causando " + damage + " de dano!");
        enemy.takeDamage(damage);
        if (!enemy.isAlive()) {
            slowConsole.imprimirDevagar("A vida de " + enemy.getName() + " despencou para 0");
            slowConsole.imprimirDevagar(enemy.getName() + " foi obliterado!");
            slowConsole.imprimirDevagar("Você absorveu " + enemy.getExp() + " de EXP das profundezas do abismo!");
            slowConsole.imprimirDevagar(enemy.getName() + " deixou para trás " + enemy.getGold() + " de Ouro Profano!");
            gainGold(enemy.getGold());
            gainExp(enemy.getExp());
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
                slowConsole.imprimirDevagar("A vida de " + enemy.getName() + " despencou para 0");
                slowConsole.imprimirDevagar(enemy.getName() + " foi obliterado!");
                slowConsole.imprimirDevagar("Você absorveu " + enemy.getExp() + " de EXP das profundezas do abismo!");
                slowConsole.imprimirDevagar(enemy.getName() + " deixou para trás " + enemy.getGold() + " de Ouro Profano!");
                gainGold(enemy.getGold());
                gainExp(enemy.getExp());
            }
        }
    }

    @Override
    public void getTechnicalInfo() {
        super.getTechnicalInfo();
        slowConsole.imprimirDevagar("Mana Profana: " + getMana());
    }

    @Override
    public void gainExp(int expGain) {
        setExp(getExp() + expGain);

        while (getExp() >= getLevel() * 10) {
            int levelsGained = getExp() / (getLevel() * 10);
            setExp(getExp() % (getLevel() * 10));

            setLevel(getLevel() + levelsGained);
            slowConsole.imprimirDevagar(getName() + " ascendeu nas artes arcanas! Nível atual é: " + getLevel());

            setSpecial(getSpecial() + 5 * levelsGained);
            slowConsole.imprimirDevagar(getName() + " aumentou " + (5 * levelsGained) + " de Poder Arcanos!");
            setHealthbar(getHealthbar() + 5 * levelsGained);
            slowConsole.imprimirDevagar(getName() + " aumentou " + (5 * levelsGained) + " de Vitalidade!");
            setMana(getMana() + 5 * levelsGained);
            setMaxMana(getMaxMana() + 5);
            slowConsole.imprimirDevagar(getName() + " absorveu " + (5 * levelsGained) + " de Mana Arcanos!");
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
