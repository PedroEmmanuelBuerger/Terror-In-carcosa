package Utils;

import java.util.Scanner;

public class InputUtils {

    public static int lerInteiro(Scanner scanner, String mensagemErro) {
        while (!scanner.hasNextInt()) {
            System.out.println(mensagemErro);
            scanner.next(); // Limpa a entrada inválida
        }
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha pendente
        return escolha;
    }
}
