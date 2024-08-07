package rpg.Character.Classes;

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
    private final SlowConsole slowConsole = new SlowConsole();
    private final List<Imp> imps = new ArrayList<>();
    private final EvilForces spellBook;

    public Necromancer(String name, int healthbar, int mana, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        this.mana = mana;
        this.maxMana = mana;
        this.setClasses("Necromante das Sombras");
        Weapon weapon = new Ring(0);
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
            slowConsole.imprimirDevagar("Você já invocou o número máximo de Imps. Liberte alguns antes de invocar mais.");
        } else {
            if (this.mana >= 15) {
                Imp imp = new Imp();
                imp.setName(imp.getName() + " " + (getImps().size() + 1));
                imps.add(imp);
                this.mana -= 15;
                slowConsole.imprimirDevagar("Um Imp das Trevas foi chamado para seu auxílio!");
                slowConsole.imprimirDevagar("Mana restante: " + this.getMana());
            } else {
                slowConsole.imprimirDevagar("Energia sombria insuficiente para convocar um Imp.");
            }
        }
    }

    @Override
    public void attack(Attributes enemy) {
        int damage = this.getAttack();
        CriticChance criticChance = new CriticChance(damage);
        damage = criticChance.chanceCritic();
        slowConsole.imprimirDevagar(this.getName() + " lançou um feitiço sombrio em " + enemy.getName() + ", causando " + damage + " de dano!");
        enemy.takeDamage(damage);
        if (!enemy.isAlive()) {
            slowConsole.imprimirDevagar("A vida de " + enemy.getName() + " chegou a 0.");
            slowConsole.imprimirDevagar(enemy.getName() + " foi consumido pelas sombras!");
            slowConsole.imprimirDevagar("Você ganhou " + enemy.getExp() + " de EXP!");
            slowConsole.imprimirDevagar(enemy.getName() + " deixou cair " + enemy.getGold() + " de Ouro!");
            gainGold(enemy.getGold());
            gainExp(enemy.getExp());
        }
    }

    public void selectSpell(Attributes enemy) {
        int damageSpell = spellBook.selectSpell(this);
        CriticChance criticChance = new CriticChance(damageSpell);
        damageSpell = criticChance.chanceCritic();
        if (damageSpell != 0) {
            enemy.takeDamage(this.getSpecial() + damageSpell);
            if (!enemy.isAlive()) {
                slowConsole.imprimirDevagar("A vida de " + enemy.getName() + " chegou a 0.");
                slowConsole.imprimirDevagar(enemy.getName() + " foi consumido pelas sombras!");
                slowConsole.imprimirDevagar("Você ganhou " + enemy.getExp() + " de EXP!");
                slowConsole.imprimirDevagar(enemy.getName() + " deixou cair " + enemy.getGold() + " de Ouro!");
                gainGold(enemy.getGold());
                gainExp(enemy.getExp());
            }
        }
    }

    @Override
    public void getTechnicalInfo() {
        super.getTechnicalInfo();
        slowConsole.imprimirDevagar("Mana: " + getMana());
        if (!getImps().isEmpty()) {
            slowConsole.imprimirDevagar("Imps Invocados:");
            for (Imp imp : getImps()) {
                if (imp.getHealthbar() > 0) {
                    slowConsole.imprimirDevagar("- " + imp.getName() + " (Vida: " + imp.getHealthbar() + ")");
                }
            }
        } else {
            slowConsole.imprimirDevagar("Nenhum Imp invocado no momento.");
        }
    }

    @Override
    public void gainExp(int expGain) {
        setExp(getExp() + expGain);

        while (getExp() >= getLevel() * 10) {
            int levelsGained = getExp() / (getLevel() * 10);
            setExp(getExp() % (getLevel() * 7));
            setLevel(getLevel() + levelsGained);
            slowConsole.imprimirDevagar(getName() + " ascendeu a um novo nível! Nível atual: " + getLevel());
            setSpecial(getSpecial() + 5 * levelsGained);
            slowConsole.imprimirDevagar(getName() + " aumentou seu poder sombrio em " + (5 * levelsGained) + "!");
            setHealthbar(getHealthbar() + 5 * levelsGained);
            slowConsole.imprimirDevagar(getName() + " aumentou sua vida em " + (5 * levelsGained) + "!");
            setMana(getMana() + 5 * levelsGained);
            setMaxMana(getMaxMana() + 5);
            slowConsole.imprimirDevagar(getName() + " aumentou sua energia sombria em " + (5 * levelsGained) + "!");
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
            slowConsole.imprimirDevagar("A vida de " + enemy.getName() + " chegou a 0.");
            slowConsole.imprimirDevagar(enemy.getName() + " foi consumido pelas sombras!");
            slowConsole.imprimirDevagar(enemy.getName() + " deixou cair " + enemy.getGold() + " de Ouro!");
            gainGold(enemy.getGold());
        }
    }
}
