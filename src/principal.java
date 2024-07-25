import rpg.Scenario.Mode.Pve;

import java.util.Scanner;

public class principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Pve.startBattle(scanner);

        scanner.close();
    }
}
