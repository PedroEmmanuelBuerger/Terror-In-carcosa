package rpg.Classes;

import rpg.Utils.SlowConsole;
import rpg.itens.Specials.Imp;
import rpg.itens.Specials.SpeelBook;
import rpg.itens.Weapons.Initials.Ring;
import rpg.itens.Weapons.Initials.Staff;
import rpg.itens.Weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Necromancer extends Attributes {
    private int mana;
    private int maxMana;
    private int limitImp = 4;
    SlowConsole slowConsole = new SlowConsole();
    private Weapon weapon = new Ring(0);
    private List<Imp> imps = new ArrayList<>(); // Lista para armazenar os Imps invocados

    public Necromancer(String name, int healthbar, int mana, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        this.mana = mana;
        this.maxMana = mana;
        this.setClasses("Necromancer");
        setWeapon(weapon);
    }

    public int getLimitImp() {
        return limitImp;
    }

    public void setLimitImp(int limitImp) {
        this.limitImp = limitImp;
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

    public List<Imp> getImps() {
        return imps;
    }

    public void summonImp() {
        if (getImps().size() >= getLimitImp()) {
            slowConsole.imprimirDevagar("Você só pode ter "+getLimitImp()+" imps lutando ao mesmo tempo");
        } else {
            if (this.mana >= 15) { // Exemplo de custo de mana
                Imp imp = new Imp();
                imp.setName(imp.getName() +" "+(getImps().size() + 1));
                imps.add(imp);
                this.mana -= 15; // Reduz a mana usada para invocar o Imp
                slowConsole.imprimirDevagar("Um Imp foi invocado para lutar ao seu lado!");
            } else {
                slowConsole.imprimirDevagar("Mana insuficiente para invocar um Imp.");
            }
        }
    }

    @Override
    public void attack(Attributes enemy) {
        int damage = this.getAttack();
        slowConsole.imprimirDevagar(this.getName() + " lançou uma caveira voadora em " + enemy.getName() + " causando " + damage + " de dano!");
        enemy.takeDamage(damage);
        if (!enemy.isAlive()) {
            slowConsole.imprimirDevagar("Vida total de " + enemy.getName() + " é 0");
            slowConsole.imprimirDevagar(enemy.getName() + " foi derrotado!");
            slowConsole.imprimirDevagar("Você ganhou " + enemy.getExp() + " de EXP!");
            gainExp(enemy.getExp()); // Ganha experiência baseada no nível do inimigo
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
        slowConsole.imprimirDevagar("Arma: " + getWeapon().getName() + " (" + getWeapon().getAttack() + ") de dano!");
    }

    @Override
    public void gainExp(int expGain) {
        setExp(getExp() + expGain); // Adiciona a experiência ganha

        // Verifica se o personagem subiu de nível
        while (getExp() >= getLevel() * 10) {
            int levelsGained = getExp() / (getLevel() * 10); // Quantos níveis foram ganhos
            setExp(getExp() % (getLevel() * 7)); // Experiência restante após subir de nível
            setLevel(getLevel() + levelsGained);
            slowConsole.imprimirDevagar(getName() + " upou de nível! Nível atual é: " + getLevel());
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

    public void attackWithSpecial(Attributes enemy) {
        summonImp();
        if (!enemy.isAlive()) {
            slowConsole.imprimirDevagar("Vida total de " + enemy.getName() + " é 0");
            slowConsole.imprimirDevagar(enemy.getName() + " foi derrotado!");
            slowConsole.imprimirDevagar("Você ganhou " + enemy.getExp() + " de EXP!");
            gainExp(enemy.getExp()); // Ganha experiência baseada no nível do inimigo
        }
    }
}