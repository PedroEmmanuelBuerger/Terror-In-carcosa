package rpg.itens;

import rpg.Utils.ManaAdm;
import rpg.Utils.SlowConsole;
import rpg.Utils.InputUtils;
import rpg.CharacterCreation.CreatePlayer; // Importe a classe CreatePlayer corretamente
import rpg.Classes.Mage;

import java.util.Scanner;

public class SpeelBook {

    private Scanner scanner;
    private SlowConsole slowConsole = new SlowConsole();
    private CreatePlayer createPlayer = new CreatePlayer(); // Crie uma instância de CreatePlayer se necessário

    public SpeelBook() {
        this.scanner = new Scanner(System.in); // Inicializa o Scanner para entrada padrão
    }

    public int selectSpell(Mage mage) {
        slowConsole.imprimirDevagar("Selecione Sua Magia:");
        slowConsole.imprimirDevagar("1 - Tormenta de fogo (Custo de mana: 5)");
        slowConsole.imprimirDevagar("2 - Jato Super comprimido de água (Custo de mana: 7)");
        slowConsole.imprimirDevagar("3 - Chuva de pedras (Custo de mana: 13)");
        slowConsole.imprimirDevagar("4 - Tempestade Ártica (Custo de mana: 17)");
        boolean manaRes;
        ManaAdm manaAdm = new ManaAdm();

        int escolha;
        int damage = 0;
        do {
            escolha = InputUtils.lerInteiro(scanner, "Escolha inválida. Por favor, escolha uma opção válida.");

            switch (escolha) {
                case 1:
                    manaRes = manaAdm.costMana(mage.getMana(), 5, mage.getName());
                    if (!manaRes) {
                        slowConsole.imprimirDevagar("Você selecionou: Tormenta de fogo");
                        damage = 8;
                        mage.setMana(mage.getMana() - 5);
                        slowConsole.imprimirDevagar(mage.getName() + " gastou 5 de mana, ficando com " + mage.getMana() + " restante.");
                    }
                    break;
                case 2:
                    manaRes = manaAdm.costMana(mage.getMana(), 7, mage.getName());
                    if (!manaRes) {
                        slowConsole.imprimirDevagar("Você selecionou: Jato massiso de água");
                        damage = 12;
                        mage.setMana(mage.getMana() - 7);// Chamar costMana na instância criada
                        slowConsole.imprimirDevagar(mage.getName() + " gastou 7 de mana, ficando com " + mage.getMana() + " restante.");
                    }
                    break;
                case 3:
                    manaRes = manaAdm.costMana(mage.getMana(), 13, mage.getName());
                    if (!manaRes) {
                        slowConsole.imprimirDevagar("Você selecionou: Chuva de pedras");
                        mage.setMana(mage.getMana() - 13);
                        slowConsole.imprimirDevagar(mage.getName() + " gastou 13 de mana, ficando com " + mage.getMana() + " restante.");// Chamar costMana na instância criada
                        damage = 15;
                    }
                    break;
                case 4:
                    manaRes = manaAdm.costMana(mage.getMana(), 17, mage.getName());
                    if (!manaRes) {
                        slowConsole.imprimirDevagar("Você selecionou: Tempestade Ártica");
                        mage.setMana(mage.getMana() - 17);
                        slowConsole.imprimirDevagar(mage.getName() + " gastou 17 de mana, ficando com " + mage.getMana() + " restante.");// Chamar costMana na instância criada
                        damage = 26;
                    }
                    break;
                default:
                    slowConsole.imprimirDevagar("Escolha inválida. Por favor, escolha uma opção válida.");
            }
        } while (escolha < 1 || escolha > 4);
        return damage;
    }

    // Método para fechar o Scanner se necessário
    public void fecharScanner() {
        scanner.close();
    }
}
