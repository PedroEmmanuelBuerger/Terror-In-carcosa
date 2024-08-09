package rpg.Utils;

import java.util.Scanner;

public class InputUtils {

    public static int lerInteiro(Scanner scanner, String mensagemErro) {
        while (!scanner.hasNextInt()) {
            System.out.println(mensagemErro);
            scanner.next();
        }
        int escolha = scanner.nextInt();
        scanner.nextLine();
        return escolha;
    }
}
