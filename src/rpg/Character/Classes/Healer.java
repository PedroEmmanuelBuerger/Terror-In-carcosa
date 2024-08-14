package rpg.Character.Classes;
import rpg.Utils.CriticChance;
import rpg.Utils.SlowConsole;
import rpg.Utils.ManaAdm;
import rpg.itens.Weapons.Initials.Mace;
import rpg.itens.Weapons.Weapon;

public class Healer extends Attributes {
    private int mana;
    private int maxMana;
    SlowConsole slowConsole = new SlowConsole();

    public Healer(String name, int healthbar, int mana, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        this.mana = mana;
        this.setClasses("Curandeiro das Trevas");
        this.maxMana = mana;
        Weapon weapon = new Mace(0);
        setWeapon(weapon);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public void attackWithSpecial(Attributes enemy) {
        int sacredDamage = this.getSpecial() / 2;
        CriticChance criticChance = new CriticChance(sacredDamage);
        sacredDamage = criticChance.chanceCritic();
        boolean manaRes;
        ManaAdm manaAdm = new ManaAdm();
        manaRes = manaAdm.costMana(this.mana, 10, this.getName());
        if (!manaRes) {
            slowConsole.imprimirDevagar(this.getName() + " lançou um feitiço sagrado, infligindo " + sacredDamage + " de dano a " + enemy.getName() + "!");
            this.mana -= 10;
            slowConsole.imprimirDevagar(this.getName() + " drenou 10 de mana, restando " + this.mana + ".");
            enemy.takeDamage(sacredDamage);
            if (!enemy.isAlive()) {
                slowConsole.imprimirDevagar("A saúde total de " + enemy.getName() + " chegou a 0");
                slowConsole.imprimirDevagar(enemy.getName() + " foi destruído!");
                slowConsole.imprimirDevagar(this.getName() + " absorveu " + enemy.getExp() + " de EXP das profundezas!");
                slowConsole.imprimirDevagar(enemy.getName() + " deixou para trás " + enemy.getGold() + " de Ouro Profano!");
                gainGold(enemy.getGold());
                gainExp(enemy.getExp());
            }
        }
    }

    public void heal(Attributes ally) {
        boolean manaRes;
        ManaAdm manaAdm = new ManaAdm();
        manaRes = manaAdm.costMana(this.mana, 7, this.getName()); // Chamar costMana na instância criada
        if (!manaRes) {
            int healAmount = (getSpecial() / 2) + ally.getHealthbar();
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

    public void ressurection(Attributes ally) {
        boolean manaRes;
        ManaAdm manaAdm = new ManaAdm();
        manaRes = manaAdm.costMana(this.mana, 35, this.getName()); // Chamar costMana na instância criada
        if (!manaRes) {
            if (ally.isAlive()) {
                slowConsole.imprimirDevagar(ally.getName() + " já se encontra entre os vivos. Impossível ressuscitar!");
            } else {
                ally.setHealthbar(ally.getMaxHealthInitial() / 2);
                slowConsole.imprimirDevagar(ally.getName() + " foi reanimado das profundezas! Sua saúde atual é " + ally.getHealthbar());
                this.mana -= 35;
                slowConsole.imprimirDevagar(getName() + " drenou 35 de mana, restando " + this.mana + ".");
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

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }
}
