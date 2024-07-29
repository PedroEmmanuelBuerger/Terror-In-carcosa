package rpg.itens.Specials;

import rpg.Classes.Attributes;
import rpg.Utils.SlowConsole;

public class Imp extends Attributes {
    public Imp() {
    super("imp", 10, 5, 0, "I am the Imp!");
    }

    SlowConsole slowConsole = new SlowConsole();

    public void ImpAttack(Attributes enemy, Attributes personagem) {
        int damage = personagem.getSpecial() / 2;
        slowConsole.imprimirDevagar(this.getName() + " atacou " + enemy.getName() + " causando " + damage + " de dano!");
        enemy.takeDamage(damage);

        if (!enemy.isAlive()) {
            slowConsole.imprimirDevagar("Vida total de " + enemy.getName() + " é 0");
            slowConsole.imprimirDevagar(enemy.getName() + " foi derrotado!");
            slowConsole.imprimirDevagar("Você ganhou " + enemy.getExp() + " de EXP!");
            personagem.gainExp(enemy.getExp());
        }
    }
}