package rpg.Mode.Campaign.Events;

import rpg.Utils.SlowConsole;

import java.util.Objects;
import java.util.Scanner;

public class ChoseInitialWeapon {
    private final SlowConsole slowConsole = new SlowConsole();
    Scanner scanner = new Scanner(System.in);
    public void choseWeapon(String personagem) {
        slowConsole.imprimirDevagar("Escolha sua Arma Inicial:");
        if (Objects.equals(personagem, "Mage")) {
            slowConsole.imprimirDevagar("Cajado");
            slowConsole.imprimirDevagar("Globo Mágico");
            scanner.nextLine();
        }
    }
}
