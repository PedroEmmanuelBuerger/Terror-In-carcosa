package rpg.itens.Specials;

import rpg.Character.Classes.Attributes;
import rpg.Utils.SlowConsole;

public class Imp extends Attributes {
    public Imp() {
        super("Imp das Trevas", 15, 13, 0, "Eu sou o Imp das Trevas!");
    }

    private final SlowConsole slowConsole = new SlowConsole();

    public void impAttack(Attributes enemy, Attributes personagem) {
        int damage = (personagem.getLevel() + this.getAttack() + 2);
        slowConsole.imprimirDevagar(this.getName() + " atacou " + enemy.getName() + ", causando " + damage + " de dano sombrio!");
        enemy.takeDamage(damage);

        if (!enemy.isAlive()) {
            slowConsole.imprimirDevagar("A vida de " + enemy.getName() + " chegou a 0.");
            slowConsole.imprimirDevagar(enemy.getName() + " foi consumido pelas sombras!");
            slowConsole.imprimirDevagar("Você ganhou " + enemy.getExp() + " de EXP!");
            slowConsole.imprimirDevagar(enemy.getName() + " deixou cair " + enemy.getGold() + " de Ouro!");
            gainGold(enemy.getGold());
            personagem.gainExp(enemy.getExp());
        }
    }

    @Override
    public void takeDamage(int damage) {
        int currentHealth = this.getHealthbar();
        this.setHealthbar(currentHealth - damage);
        slowConsole.imprimirDevagar(this.getName() + " recebeu " + damage + " de dano sombrio!");
        getHealth(this);
        if (getHealthbar() <= 0) {
            slowConsole.imprimirDevagar(this.getName() + " foi consumido pela escuridão!");
        }
    }
}
