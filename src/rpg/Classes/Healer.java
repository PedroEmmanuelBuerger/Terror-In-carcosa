package rpg.Classes;

import rpg.Utils.SlowConsole;
import rpg.Utils.ManaAdm;

public class Healer extends Attributes {
    private int mana;
    private int maxMana;
    SlowConsole slowConsole = new SlowConsole();

    public Healer(String name, int healthbar, int Mana, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        this.mana = Mana;
        this.setClasses("Healer");
        this.maxMana = mana;
        this.setWeapon("Livro Sagrado");
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public void attackWithSpecial(Attributes enemy) {
        int holyDamage = this.getSpecial() / 2;
        boolean manaRes;
        ManaAdm manaAdm = new ManaAdm();
        manaRes = manaAdm.costMana(this.mana, 15, this.getName()); // Chamar costMana na instância criada
        if (!manaRes) {
            slowConsole.imprimirDevagar(this.getName() + " conjurou um feitiço sagrado, causando " + holyDamage + " de dano a " + enemy.getName() + "!");
            this.mana -= 15; // Reduzir a mana após o uso do feitiço
            slowConsole.imprimirDevagar(this.getName() + " gastou 15 de mana, ficando com " + this.mana + " restante.");
            enemy.takeDamage(holyDamage);
            if (!enemy.isAlive()) {
                slowConsole.imprimirDevagar("Vida total de " + enemy.getName() + " é 0");
                slowConsole.imprimirDevagar(enemy.getName() + " foi derrotado!");
                slowConsole.imprimirDevagar("Você ganhou " + enemy.getExp() + " de EXP!");
                gainExp(enemy.getExp());
            }
        }
    }

    public void heal(Attributes ally) {
        boolean manaRes;
        ManaAdm manaAdm = new ManaAdm();
        manaRes = manaAdm.costMana(this.mana, 30, this.getName()); // Chamar costMana na instância criada
        if (!manaRes) {
            int healAmount = getSpecial() + ally.getHealthbar();
            if (ally.isAlive()) {
                if (ally.getMaxHealthInitial() < healAmount) {
                    ally.setHealthbar(ally.getMaxHealthInitial());
                    slowConsole.imprimirDevagar(getName() + " curou toda a vida de " + ally.getName() + "!");
                    this.mana = this.mana - 30;
                    slowConsole.imprimirDevagar(this.getName() + " gastou 30 de mana, ficando com " + this.mana + " restante.");
                } else {
                    ally.setHealthbar(healAmount);
                    slowConsole.imprimirDevagar(getName() + " curou " + getSpecial() + " de vida de " + ally.getName() + "!");
                    this.mana = this.mana - 30;
                    slowConsole.imprimirDevagar(this.getName() + " gastou 30 de mana, ficando com " + this.mana + " restante.");
                }
            } else {
                slowConsole.imprimirDevagar(ally.getName() + " está morto! Impossível curar.");
            }
        }
    }

    public void ressurection(Attributes ally) {
        boolean manaRes;
        ManaAdm manaAdm = new ManaAdm();
        manaRes = manaAdm.costMana(this.mana, 15, this.getName()); // Chamar costMana na instância criada
        if (!manaRes) {
            if (ally.isAlive()) {
                slowConsole.imprimirDevagar(ally.getName() + " está vivo, impossível ressuscitar!");
            } else {
                ally.setHealthbar(ally.getMaxHealthInitial() / 2);
                slowConsole.imprimirDevagar(ally.getName() + " foi ressuscitado! Sua vida atual é " + ally.getHealthbar());
                mana -= 35;
                slowConsole.imprimirDevagar(getName() + " gastou 35 de mana, ficando com " + mana + " restante.");
            }
        }
    }

    @Override
    public void gainExp(int expGain) {
        setExp(getExp() + expGain); // Adiciona a experiência ganha

        // Verifica se o personagem subiu de nível
        while (getExp() >= getLevel() * 7) {
            int levelsGained = getExp() / (getLevel() * 7); // Quantos níveis foram ganhos
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
            setMaxMana(getMaxMana() + 5);
            slowConsole.imprimirDevagar(getName() + " Aumentou " + (5 * levelsGained) + " de Mana!");
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
        slowConsole.imprimirDevagar("Arma: " + getWeapon());
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
