package rpg.itens.Specials;

import rpg.Classes.Necromancer;
import rpg.Utils.ManaAdm;
import rpg.Utils.SlowConsole;
import rpg.Utils.InputUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EvilForces {
    private Scanner scanner;
    private SlowConsole slowConsole = new SlowConsole();
    private List<Spell> spells = new ArrayList<>(); // Lista de feitiços

    public EvilForces() {
        this.scanner = new Scanner(System.in);
        initializeSpells(); // Inicializa com feitiços padrão
    }

    private void initializeSpells() {
        // Adiciona feitiços padrão ao iniciar
        spells.add(new Spell("Ritual de Invocação", 15, 20));
        spells.add(new Spell("Explosão Sombria", 20, 25));
        spells.add(new Spell("Maldicão de Desolação", 30, 40));
    }

//    public void addNewSpell(String name, int manaCost, int damage) {
//        if (!hasSpell(name)) {
//            spells.add(new Spell(name, manaCost, damage));
//            slowConsole.imprimirDevagar("Nova magia adicionada: " + name);
//        } else {
//            slowConsole.imprimirDevagar("A magia " + name + " já está no seu livro de magias.");
//        }
//    }

    public boolean hasSpell(String name) {
        for (Spell spell : spells) {
            if (spell.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public int selectSpell(Necromancer necromancer) {
        slowConsole.imprimirDevagar("Selecione Sua Magia:");
        for (int i = 0; i < spells.size(); i++) {
            Spell spell = spells.get(i);
            slowConsole.imprimirDevagar((i + 1) + " - " + spell.getName() + " (Custo de mana: " + spell.getManaCost() + ")");
        }
        boolean manaRes;
        int escolha;
        int damage = 0;
        do {
            escolha = InputUtils.lerInteiro(scanner, "Escolha inválida. Por favor, escolha uma opção válida.");

            if (escolha >= 1 && escolha <= spells.size()) {
                Spell selectedSpell = spells.get(escolha - 1);
                manaRes = new ManaAdm().costMana(necromancer.getMana(), selectedSpell.getManaCost(), necromancer.getName());
                if (!manaRes) {
                    slowConsole.imprimirDevagar("Você selecionou: " + selectedSpell.getName());
                    damage = selectedSpell.getDamage();
                    necromancer.setMana(necromancer.getMana() - selectedSpell.getManaCost());
                    slowConsole.imprimirDevagar(necromancer.getName() + " gastou " + selectedSpell.getManaCost() + " de mana, ficando com " + necromancer.getMana() + " restante.");
                }
            } else {
                slowConsole.imprimirDevagar("Escolha inválida. Por favor, escolha uma opção válida.");
            }
        } while (escolha < 1 || escolha > spells.size());
        return damage;
    }

    private static class Spell {
        private String name;
        private int manaCost;
        private int damage;

        public Spell(String name, int manaCost, int damage) {
            this.name = name;
            this.manaCost = manaCost;
            this.damage = damage;
        }

        public String getName() {
            return name;
        }

        public int getManaCost() {
            return manaCost;
        }

        public int getDamage() {
            return damage;
        }
    }
}
