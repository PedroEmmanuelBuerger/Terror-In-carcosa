package rpg.Monsters.Bosses;

import rpg.Utils.SlowConsole;
import rpg.Character.Classes.Attributes;

public class KingDragon extends Attributes {
    SlowConsole slowConsole = new SlowConsole();
    private final String portrait;

    public KingDragon(String name, int healthbar, int attack, int specialAttack, String battleCry, String portrait) {
        super(name, healthbar, attack, specialAttack, battleCry);
        this.setExp(70);
        this.portrait = portrait;
    }

    @Override
    public void attack(Attributes target) {
        int damage = this.getAttack();
        slowConsole.imprimirDevagar(this.getName() + " ataca " + target.getName() + " com força, causando " + damage + " de dano!");
        target.takeDamage(damage);
    }

    @Override
    public void attackWithSpecial(Attributes target) {
        int damage = this.getSpecial();
        slowConsole.imprimirDevagar(this.getName() + " usa um ataque especial contra " + target.getName() + " causando " + damage + " de dano!!");
        target.takeDamage(damage);
    }

    public void generateTechinicalInfo() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar(portrait);
        slowConsole.imprimirDevagar("Nome: " + this.getName());
        slowConsole.imprimirDevagar("Vida: " + this.getHealthbar());
        slowConsole.imprimirDevagar("Ataque: " + this.getAttack());
        slowConsole.imprimirDevagar("Ataque Especial: " + this.getSpecial());
        slowConsole.imprimirDevagar("Exp: " + this.getExp());
        slowConsole.imprimirDevagar("Gold: " + this.getGold());
    }
}
