package rpg.Character.Classes;

import rpg.Utils.SlowConsole;
import rpg.Utils.ManaAdm;
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
            int healAmount = getSpecial();
            if (ally.isAlive()) {
                if (healAmount + ally.getHealthbar() > ally.getMaxHealthInitial()) {
                    ally.setHealthbar(ally.getMaxHealthInitial());
                    slowConsole.imprimirDevagar(getName() + " restaurou toda a vitalidade de " + ally.getName() + " com uma benção obscura! ficando com "+ally.getMaxHealthInitial() + " de vida");
                } else {
                    ally.setHealthbar(ally.getHealthbar() + healAmount);
                    slowConsole.imprimirDevagar(getName() + " devolveu " + getSpecial() + " de vida a " + ally.getName() + " com seu poder sombrio! curando "+healAmount+" de vida");
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
            setMaxHealthInitial(getMaxHealthInitial() + 5 * levelsGained);
            slowConsole.imprimirDevagar(getName() + " aumentou " + (5 * levelsGained) + " de Vitalidade!");
            setMana(getMana() + 5 * levelsGained);
            setMaxMana(getMaxMana() + 5);
            slowConsole.imprimirDevagar(getName() + " acumulou " + (5 * levelsGained) + " de Mana Profana!");
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

    @Override
    public void takeDamage(int damage) {
        int currentHealth = this.getHealthbar();
        int reducedDamage = damage - (this.getArmor() != null ? this.getArmor().armor() : 0);

        if (reducedDamage <= 0) {
            slowConsole.imprimirDevagar("A armadura de " + this.getName() + " resistiu ao ataque!");
            return;
        }

        if (this.isDefese()) {
            reducedDamage /= 2;
            slowConsole.imprimirDevagar(getName() + " defendeu com bravura, reduzindo o dano para " + reducedDamage + "!");
            this.setDefese(false);
        }

        this.setHealthbar(currentHealth - reducedDamage);
        slowConsole.imprimirDevagar(getName() + " sofreu " + reducedDamage + " de dano, enfrentando o caos!");
        getHealth(this);
    }
}
