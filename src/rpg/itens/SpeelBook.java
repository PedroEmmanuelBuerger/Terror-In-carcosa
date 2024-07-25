package rpg.itens;

import Utils.ManaAdm;
import Utils.SlowConsole;
import Utils.InputUtils;
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
        slowConsole.imprimirDevagar("1 - Tormenta de fogo (Custo de mana: 15)");
        slowConsole.imprimirDevagar("2 - Jato Super comprimido de água (Custo de mana: 11)");
        slowConsole.imprimirDevagar("3 - Chuva de pedras (Custo de mana: 20)");
        slowConsole.imprimirDevagar("4 - Tempestade Ártica (Custo de mana: 25)");
        boolean manaRes;
        ManaAdm manaAdm = new ManaAdm();

        int escolha;
        int damage = 0;
        do {
            escolha = InputUtils.lerInteiro(scanner, "Escolha inválida. Por favor, escolha uma opção válida.");

            switch (escolha) {
                case 1:
                    manaRes = manaAdm.costMana(mage.getMana(), 15, mage.getName());
                    if (!manaRes) {
                        slowConsole.imprimirDevagar("Você selecionou: Tormenta de fogo");
                        damage = 15;
                        mage.setMana(mage.getMana() - 15);
                        slowConsole.imprimirDevagar(mage.getName() + " gastou 15 de mana, ficando com " + mage.getMana() + " restante.");
                    }
                    break;
                case 2:
                    manaRes = manaAdm.costMana(mage.getMana(), 11, mage.getName());
                    if (!manaRes) {
                        slowConsole.imprimirDevagar("Você selecionou: Jato massiso de água");
                        damage = 12;
                        mage.setMana(mage.getMana() - 11);// Chamar costMana na instância criada
                        slowConsole.imprimirDevagar(mage.getName() + " gastou 11 de mana, ficando com " + mage.getMana() + " restante.");
                    }
                    break;
                case 3:
                    manaRes = manaAdm.costMana(mage.getMana(), 20, mage.getName());
                    if (!manaRes) {
                        slowConsole.imprimirDevagar("Você selecionou: Chuva de pedras");
                        mage.setMana(mage.getMana() - 20);
                        slowConsole.imprimirDevagar(mage.getName() + " gastou 20 de mana, ficando com " + mage.getMana() + " restante.");// Chamar costMana na instância criada
                        damage = 18;
                    }
                    break;
                case 4:
                    manaRes = manaAdm.costMana(mage.getMana(), 25, mage.getName());
                    if (!manaRes) {
                        slowConsole.imprimirDevagar("Você selecionou: Tempestade Ártica");
                        mage.setMana(mage.getMana() - 25);
                        slowConsole.imprimirDevagar(mage.getName() + " gastou 25 de mana, ficando com " + mage.getMana() + " restante.");// Chamar costMana na instância criada
                        damage = 30;
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
