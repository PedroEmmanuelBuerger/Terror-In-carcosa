package rpg.Classes;
import rpg.Utils.CriticChance;
import rpg.Utils.SlowConsole;
import rpg.itens.Specials.EvilForces;
import rpg.itens.Specials.Imp;
import rpg.itens.Weapons.Initials.Ring;
import rpg.itens.Weapons.Weapon;
import java.util.ArrayList;
import java.util.List;

public class Necromancer extends Attributes {
    private int mana;
    private int maxMana;
    private int limitImp = 3;
    SlowConsole slowConsole = new SlowConsole();
    private Weapon weapon = new Ring(0);
    private List<Imp> imps = new ArrayList<>();
    private EvilForces spellBook; // Lista para armazenar os Imps invocados

    public Necromancer(String name, int healthbar, int mana, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        this.mana = mana;
        this.maxMana = mana;
        this.setClasses("Necromante");
        setWeapon(weapon);
        this.spellBook = new EvilForces();
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
            slowConsole.imprimirDevagar("Você só pode ter " + getLimitImp() + " esqueletos lutando ao mesmo tempo");
        } else {
            if (this.mana >= 15) { // Exemplo de custo de mana
                Imp imp = new Imp();
                imp.setName(imp.getName() + " " + (getImps().size() + 1));
                imps.add(imp);
                this.mana -= 15; // Reduz a mana usada para invocar o Imp
                slowConsole.imprimirDevagar("Um Esqueleto foi invocado para lutar ao seu lado!");
                slowConsole.imprimirDevagar("Gastou 15 de mana, mana restante: " + this.getMana());
            } else {
                slowConsole.imprimirDevagar("Mana insuficiente para invocar um esqueleto.");
            }
        }
    }

    @Override
    public void attack(Attributes enemy) {
        int damage = this.getAttack();
        CriticChance criticChance = new CriticChance(damage);
        damage = criticChance.chanceCritic();
        slowConsole.imprimirDevagar(this.getName() + " lançou uma caveira voadora em " + enemy.getName() + " causando " + damage + " de dano!");
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

    public void selectSpell(Attributes enemy) {
        int damageSpeel = spellBook.selectSpell(this);
        CriticChance criticChance = new CriticChance(damageSpeel);
        damageSpeel = criticChance.chanceCritic();
        if (damageSpeel != 0) {
            enemy.takeDamage(this.getSpecial() + damageSpeel);
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

        // Adiciona a verificação dos imps vivos
        if (!getImps().isEmpty()) {
            slowConsole.imprimirDevagar("Imps Invocados:");
            for (Imp imp : getImps()) {
                if (imp.getHealthbar() > 0) {
                    slowConsole.imprimirDevagar("- " + imp.getName() + " (Vida: " + imp.getHealthbar() + ")");
                }
            }
        } else {
            slowConsole.imprimirDevagar("Nenhum Imp invocado.");
        }
    }


    @Override
    public void gainExp(int expGain) {
        setExp(getExp() + expGain); // Adiciona a experiência ganha

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

    @Override
    public void attackWithSpecial(Attributes enemy) {
        summonImp();
        if (!enemy.isAlive()) {
            slowConsole.imprimirDevagar("Vida total de " + enemy.getName() + " é 0");
            slowConsole.imprimirDevagar(enemy.getName() + " foi derrotado!");
            slowConsole.imprimirDevagar(enemy.getName() + " Derrubou " + enemy.getGold() + " De Ouro!");
            gainGold(enemy.getGold());
        }
    }
}