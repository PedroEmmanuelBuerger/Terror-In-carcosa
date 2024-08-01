package rpg.itens.Specials;

import rpg.Character.Classes.Attributes;
import rpg.Utils.SlowConsole;

public class Imp extends Attributes {
    public Imp() {
        super("Esqueleto", 15, 13, 0, "I am the Skeleton!");
    }

    SlowConsole slowConsole = new SlowConsole();

    public void ImpAttack(Attributes enemy, Attributes personagem) {
        int damage = (personagem.getLevel() + this.getAttack() + 2);
        slowConsole.imprimirDevagar(this.getName() + " atacou " + enemy.getName() + " causando " + damage + " de dano!");
        enemy.takeDamage(damage);

        if (!enemy.isAlive()) {
            slowConsole.imprimirDevagar("Vida total de " + enemy.getName() + " é 0");
            slowConsole.imprimirDevagar(enemy.getName() + " foi derrotado!");
            slowConsole.imprimirDevagar("Você ganhou " + enemy.getExp() + " de EXP!");
            slowConsole.imprimirDevagar(enemy.getName() + " Derrubou " + enemy.getGold() + " De Ouro!");
            gainGold(enemy.getGold());
            personagem.gainExp(enemy.getExp());
        }
    }

    @Override
    public void takeDamage(int damage) {
        // Reduz a saúde do personagem pelo valor do dano recebido
        int currentHealth = this.getHealthbar();
        this.setHealthbar(currentHealth - damage);
        slowConsole.imprimirDevagar(this.getName() + " recebeu " + damage + " de dano!");
        getHealth(this);
        if (getHealthbar() <= 0) {
            slowConsole.imprimirDevagar(this.getName() + " Foi derrotado!");
        }
    }
}