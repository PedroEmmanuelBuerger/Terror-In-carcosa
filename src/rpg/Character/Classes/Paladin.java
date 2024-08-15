package rpg.Character.Classes;

import rpg.Utils.SlowConsole;
import rpg.Utils.ManaAdm;
import rpg.itens.Weapons.Initials.Axe;
import rpg.itens.Weapons.Initials.Mace;
import rpg.itens.Weapons.Weapon;

public class Paladin extends Attributes {
    private final SlowConsole slowConsole = new SlowConsole();
    private int mana;
    private int maxMana;
    private boolean defense = false;

    public Paladin(String name, int healthbar, int mana, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        setClasses("Paladino do Desconhecido");
        Weapon weapon = new Mace(0);
        setWeapon(weapon);
        this.mana = mana;
        this.maxMana = mana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public boolean isDefese() {
        return defense;
    }

    public void setDefese(boolean defense) {
        this.defense = defense;
    }

    public void defend() {
        setDefese(true);
        slowConsole.imprimirDevagar(getName() + " assumiu uma postura defensiva, preparando-se para resistir aos horrores que se aproximam!");
    }

    public void heal(Attributes ally) {
        boolean manaRes;
        ManaAdm manaAdm = new ManaAdm();
        manaRes = manaAdm.costMana(this.mana, 7, this.getName());
        if (!manaRes) {
            int healAmount = getSpecial() + ally.getHealthbar();
            if (ally.isAlive()) {
                if (ally.getMaxHealthInitial() < healAmount) {
                    ally.setHealthbar(ally.getMaxHealthInitial());
                    slowConsole.imprimirDevagar(getName() + " restaurou toda a vitalidade de " + ally.getName() + " com uma benção obscura!");
                } else {
                    ally.setHealthbar(healAmount);
                    slowConsole.imprimirDevagar(getName() + " devolveu " + getSpecial() + " de vida a " + ally.getName() + " com seu poder sombrio!");
                }
                this.mana -= 7;
                slowConsole.imprimirDevagar(getName() + " drenou 7 de mana, restando " + this.mana + ".");
            } else {
                slowConsole.imprimirDevagar(ally.getName() + " já se encontra entre as sombras! Impossível curar.");
            }
        }
    }

    @Override
    public void gainExp(int expGain) {
        setExp(getExp() + expGain);

        while (getExp() >= getLevel() * 10) {
            int levelsGained = getExp() / (getLevel() * 10);
            setExp(getExp() % (getLevel() * 10));

            setLevel(getLevel() + levelsGained);
            slowConsole.imprimirDevagar(getName() + " ascendeu em poder! Nível atual é: " + getLevel());

            setSpecial(getSpecial() + 5 * levelsGained);
            slowConsole.imprimirDevagar(getName() + " aumentou " + (5 * levelsGained) + " de Poder Arcano!");
            setHealthbar(getHealthbar() + 5 * levelsGained);
            slowConsole.imprimirDevagar(getName() + " aumentou " + (5 * levelsGained) + " de Vitalidade!");
            setMana(getMana() + 5 * levelsGained);
            setMaxMana(getMaxMana() + 5);
            slowConsole.imprimirDevagar(getName() + " acumulou " + (5 * levelsGained) + " de Mana Profana!");
        }
    }

    @Override
    public void getTechnicalInfo() {
        super.getTechnicalInfo();
        slowConsole.imprimirDevagar("Mana Profana: " + getMana());
    }

    public void recoverMana(int amount) {
        int maxMana = getMaxMana();
        if (this.mana + amount <= maxMana) {
            this.mana += amount;
        } else {
            this.mana = maxMana;
        }
    }

    @Override
    public void takeDamage(int damage) {
        int currentHealth = this.getHealthbar();
        damage = damage - this.getArmor().armor();
        if (this.isDefese()) {
            int reducedDamage = damage / 2;
            this.setHealthbar(currentHealth - reducedDamage);
            slowConsole.imprimirDevagar(getName() + " defendeu com bravura, reduzindo o dano para " + reducedDamage + "!");
            this.setDefese(false);
            getHealth(this);
        } else {
            this.setHealthbar(currentHealth - damage);
            slowConsole.imprimirDevagar(getName() + " sofreu " + damage + " de dano, enfrentando o caos!");
            getHealth(this);
        }
    }
}
