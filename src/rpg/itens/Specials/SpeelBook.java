package rpg.itens.Specials;

import rpg.Utils.ManaAdm;
import rpg.Utils.SlowConsole;
import rpg.Utils.InputUtils;
import rpg.Character.Classes.Mage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpeelBook {
    private final Scanner scanner;
    private final SlowConsole slowConsole = new SlowConsole();
    private final List<Spell> spells = new ArrayList<>(); // Lista de feitiços

    public SpeelBook() {
        this.scanner = new Scanner(System.in);
        initializeSpells(); // Inicializa com feitiços padrão
    }

    private void initializeSpells() {
        // Adiciona feitiços padrão ao iniciar
        spells.add(new Spell("Chama de Oblivion", 5, 8));
        spells.add(new Spell("Fluxo de Águas Abissais", 7, 12));
        spells.add(new Spell("Chuva de Fragmentos Cósmicos", 13, 15));
        spells.add(new Spell("Tempestade Glacial", 17, 26));
    }

    public void addNewSpell(String name, int manaCost, int damage) {
        if (!hasSpell(name)) {
            spells.add(new Spell(name, manaCost, damage));
            slowConsole.imprimirDevagar("Nova magia adicionada: " + name);
        } else {
            slowConsole.imprimirDevagar("A magia " + name + " já está no seu grimório.");
        }
    }

    public boolean hasSpell(String name) {
        for (Spell spell : spells) {
            if (spell.name().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public int selectSpell(Mage mage) {
        slowConsole.imprimirDevagar("Escolha Seu Feitiço:");
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
                manaRes = new ManaAdm().costMana(mage.getMana(), selectedSpell.manaCost(), mage.getName());
                if (!manaRes) {
                    slowConsole.imprimirDevagar("Você selecionou: " + selectedSpell.name());
                    damage = selectedSpell.damage();
                    mage.setMana(mage.getMana() - selectedSpell.manaCost());
                    slowConsole.imprimirDevagar(mage.getName() + " drenou " + selectedSpell.manaCost() + " de mana, restando " + mage.getMana() + ".");
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
