package rpg.Classes;

import Utils.ManaAdm;
import Utils.SlowConsole;

public class Healer extends Attributes {
    int mana;

    public Healer(String name, int healthbar, int Mana, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        this.mana = Mana;
        this.setClasses("Healer");
    }

    SlowConsole slowConsole = new SlowConsole();

    @Override
    public void attackWithSpecial(Attributes enemy) {
        int holyDamage = this.getSpecial() / 2;
        boolean manaRes;
        ManaAdm manaAdm = new ManaAdm();
        manaRes = manaAdm.costMana(this.mana, 15, this.getName()); // Chamar costMana na instância criada
        if (!manaRes) {
            // Criar uma instância de ManaAdm
            slowConsole.imprimirDevagar(this.getName() + " conjurou um feitiço sagrado, causando " + holyDamage + " de dano a " + enemy.getName() + "!");
            this.mana -= 15; // Reduzir a mana após o uso do feitiço
            slowConsole.imprimirDevagar(this.getName() + " gastou 15 de mana, ficando com " + this.mana + " restante.");
            enemy.takeDamage(holyDamage);
            if (!enemy.isAlive()) {
                slowConsole.imprimirDevagar("Vida total de " + enemy.getName() + " é 0");
                slowConsole.imprimirDevagar(enemy.getName() + " foi derrotado!");
                gainExp(enemy.getLevel() * 10);
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
                    getHealth(ally);
                    ally.setHealthbar(healAmount);
                    slowConsole.imprimirDevagar(getName() + " curou " + getSpecial() + " de vida de " + ally.getName() + "!");
                    this.mana = this.mana - 30;
                    slowConsole.imprimirDevagar(this.getName() + " gastou 30 de mana, ficando com " + this.mana + " restante.");
                    getHealth(ally);
                }
            } else {
                slowConsole.imprimirDevagar(this.getName() + " Esta morto!. Impossivel curar");
            }
        }
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void ressurection(Attributes ally) {
        boolean manaRes;
        ManaAdm manaAdm = new ManaAdm();
        manaRes = manaAdm.costMana(this.mana, 15, this.getName()); // Chamar costMana na instância criada
        if (!manaRes) {
            if (ally.isAlive() == true) {
                slowConsole.imprimirDevagar(ally.getName() + " Esta vivo, impossivel ressuscitar!");
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
        while (getExp() >= getLevel() * 10) {
            setExp(getExp() + getLevel() * 10); // Subtrai o máximo de experiência para o próximo nível
            setLevel(getLevel() + 1); // Aumenta o nível do personagem
            slowConsole.imprimirDevagar(getName() + " upou de nível! Nível atual é: " + getLevel());
            setSpecial(getSpecial() + 5);
            slowConsole.imprimirDevagar(getName() + " Aumentou 5 de ataque Special!");
            setHealthbar(getHealthbar() + 5);
            slowConsole.imprimirDevagar(getName() + " Aumentou 5 de vida!");
            setMana(getMana() + 5);
            slowConsole.imprimirDevagar(getName() + " Aumentou 5 de Mana!");
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
