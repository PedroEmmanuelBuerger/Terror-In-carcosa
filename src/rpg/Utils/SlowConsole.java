package rpg.Utils;

public class SlowConsole {
    private int delay = 1; // ajuste o valor para controlar a velocidade (200 milissegundos neste exemplo)

    public void imprimirDevagar(String mensagem) {
        for (int i = 0; i < mensagem.length(); i++) {
            System.out.print(mensagem.charAt(i)); // imprime o caractere atual
            try {
                Thread.sleep(delay); // pausa para criar o efeito de lentidão
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(); // pula uma linha após imprimir toda a mensagem
    }
}
