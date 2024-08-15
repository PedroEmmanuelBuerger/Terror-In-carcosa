package rpg.Monsters.Minions;

import rpg.Character.Classes.Attributes;

public class MindEater extends Mob {
    public MindEater(String name, int healthbar, int attack, int specialAttack, String battleCry, int exp, int gold, String portrait) {
        super(name, healthbar, attack, specialAttack, battleCry, exp, gold, portrait);
    }

    @Override
    public void attackWithSpecial(Attributes target) {
        int damage = this.getAttack();
        slowConsole.imprimirDevagar(this.getName() + " ataca a mente de " + target.getName() + " com suas canções chamativas, causando " + damage + " de dano a mente!");
        target.lostMind(damage);
    }
}
