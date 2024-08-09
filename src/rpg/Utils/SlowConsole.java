package rpg.Utils;

public class SlowConsole {

    public void imprimirDevagar(String mensagem) {
        for (int i = 0; i < mensagem.length(); i++) {
            System.out.print(mensagem.charAt(i));
            try {
                int delay = 1;
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}
