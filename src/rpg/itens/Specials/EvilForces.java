package rpg.itens.Specials;

import rpg.Character.Classes.Necromancer;
import rpg.Utils.ManaAdm;
import rpg.Utils.SlowConsole;
import rpg.Utils.InputUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EvilForces {
    private final Scanner scanner;
    private final SlowConsole slowConsole = new SlowConsole();
    private final List<Spell> spells = new ArrayList<>();

    public EvilForces() {
        this.scanner = new Scanner(System.in);
        initializeSpells();
    }

    private void initializeSpells() {
        spells.add(new Spell("Ritual de Invocação das Sombras", 15, 20));
        spells.add(new Spell("Explosão Sombria", 20, 25));
        spells.add(new Spell("Maldição de Desolação", 30, 40));
    }

    public int selectSpell(Necromancer necromancer) {
        slowConsole.imprimirDevagar("Escolha um feitiço sombrio:");
        for (int i = 0; i < spells.size(); i++) {
            Spell spell = spells.get(i);
            slowConsole.imprimirDevagar((i + 1) + " - " + spell.name() + " (Custo de mana: " + spell.manaCost() + ")");
        }
        boolean manaRes;
        int escolha;
        int damage = 0;
        do {
            escolha = InputUtils.lerInteiro(scanner, "Escolha inválida. Por favor, escolha uma opção válida.");

            if (escolha >= 1 && escolha <= spells.size()) {
                Spell selectedSpell = spells.get(escolha - 1);
                manaRes = new ManaAdm().costMana(necromancer.getMana(), selectedSpell.manaCost(), necromancer.getName());
                if (!manaRes) {
                    slowConsole.imprimirDevagar("Você selecionou: " + selectedSpell.name());
                    damage = selectedSpell.damage();
                    necromancer.setMana(necromancer.getMana() - selectedSpell.manaCost());
                    slowConsole.imprimirDevagar(necromancer.getName() + " gastou " + selectedSpell.manaCost() + " de energia sombria, ficando com " + necromancer.getMana() + " restante.");
                }
            } else {
                slowConsole.imprimirDevagar("Escolha inválida. Por favor, escolha uma opção válida.");
            }
        } while (escolha < 1 || escolha > spells.size());
        return damage;
    }

    private record Spell(String name, int manaCost, int damage) {
    }
}
