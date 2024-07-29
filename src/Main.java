import rpg.Scenario.Mode.Pve;
import rpg.Utils.Messages.Start;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Start.startApp();
        Pve.startBattle(scanner);

        scanner.close();
    }
}
