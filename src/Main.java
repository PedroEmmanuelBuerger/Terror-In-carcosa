import rpg.Mode.Coop;
import rpg.Mode.Pve;
import rpg.Mode.Pvp;
import rpg.Utils.Messages.Start;
import rpg.Utils.SlowConsole;

import java.util.Scanner;

public class Main {
    private static SlowConsole slowConsole = new SlowConsole();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        slowConsole.imprimirDevagar("=== Bem-vindo ao Mundo de RPG das Trevas ===");
        slowConsole.imprimirDevagar("Escolha seu modo de jogo...");
        slowConsole.imprimirDevagar("1 - Campanha");
        slowConsole.imprimirDevagar("2 - Coop");
        slowConsole.imprimirDevagar("3 - PvP");

        int choice = getPlayerChoice();

        switch (choice) {
            case 1:
                startCampaign();
                break;
            case 2:
                startCoop();
                break;
            case 3:
                startPvP();
                break;
            default:
                slowConsole.imprimirDevagar("Escolha inválida. O jogo será encerrado.");
                break;
        }
    }

    private static int getPlayerChoice() {
        slowConsole.imprimirDevagar("Digite o número do modo de jogo desejado:");
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            slowConsole.imprimirDevagar("Entrada inválida. O jogo será encerrado.");
            scanner.nextLine(); // Limpa o buffer
            return -1;
        }
    }

    private static void startCampaign() {
        slowConsole.imprimirDevagar("Você escolheu o modo Campanha!");
        Start.startApp();
        Pve.startBattle(); // Certifique-se de que o método estático startBattle() esteja disponível na classe Pve
    }

    private static void startCoop() {
        slowConsole.imprimirDevagar("Você escolheu o modo Coop!");
        Start.StartCoop();
        Coop.startCoop();
        // Implemente a lógica do modo Coop aqui
    }

    private static void startPvP() {
        slowConsole.imprimirDevagar("Você escolheu o modo PvP!");
        Start.StartPvP();
        Pvp.startBattle();
        // Implemente a lógica do modo PvP aqui
    }
}
