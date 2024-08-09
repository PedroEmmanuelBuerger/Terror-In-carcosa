package rpg.Mode.Coops.Dugeon;

import rpg.Character.Classes.Attributes;
import rpg.Monsters.Bosses.Ghazkull;
import rpg.Monsters.Bosses.KingDragon;
import rpg.Monsters.Bosses.KnightOfFear;
import rpg.Utils.SlowConsole;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class DungeonBaseCoop implements DungeonCoop {
    protected Random random = new Random();
    protected SlowConsole slowConsole = new SlowConsole();

    protected abstract Attributes createEnemy(List<Attributes> jogadores);

    protected abstract void onBossDefeated(List<Attributes> jogadores);

    public void startCombat(Scanner scanner, Attributes jogador) {
        throw new UnsupportedOperationException("Use startCombat(List<Attributes> jogadores) para o modo cooperativo.");
    }

    public void startCombat(Scanner scanner, List<Attributes> jogadores) {
        Attributes enemy = createEnemy(jogadores);
        if (enemy == null) return;

        CombatSystemCoop combatSystem = new CombatSystemCoop();
        combatSystem.startCombat(scanner, jogadores, enemy);

        if (enemy.getHealthbar() <= 0 &&
                (enemy instanceof Ghazkull || enemy instanceof KingDragon || enemy instanceof KnightOfFear)) {
            onBossDefeated(jogadores);
        }
    }
}
