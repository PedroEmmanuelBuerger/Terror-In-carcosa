package rpg.Utils.Messages;

import rpg.Utils.SlowConsole;

public class End {
    public static void FinishWarrior() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   Vitória do Guerreiro do Abismo!                      *
                *                                                        *
                * Sua força e bravura se destacaram diante dos horrores  *
                * das profundezas. Com coragem inabalável, você          *
                * superou os desafios mais terríveis.                    *
                *                                                        *
                * O abismo agora se acalma, e a vitória é sua. A estrada *
                * para novos horrores se abre à sua frente.              *
                *                                                        *
                * Continue a avançar, pois a jornada para enfrentar      *
                * o desconhecido está apenas começando.                  *
                *                                                        *
                * Que sua bravura continue a ser sua guia.               *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void FinishMage() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   Vitória do Mago dos Mistérios Cósmicos!              *
                *                                                        *
                * Com suas habilidades arcanas, você desvendou e         *
                * derrotou horrores cósmicos. Seu poder místico          *
                * dominou as forças além da compreensão.                 *
                *                                                        *
                * O cosmos agora se revela em novas dimensões. Sua       *
                * jornada está longe de terminar, e novos mistérios      *
                * aguardam.                                              *
                *                                                        *
                * Que o poder das artes arcanas continue a guiá-lo.      *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void FinishNecromancer() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   Vitória do Necromante das Profundezas!              *
                *                                                       *
                * Com domínio absoluto sobre as forças da morte e do    *
                * além, você triunfou sobre os horrores das profundezas.*
                * O poder das trevas agora se curva ao seu comando.     *
                *                                                       *
                * Novos desafios e segredos obscuros se desvelam diante *
                * de você. Continue a explorar o desconhecido.          *
                *                                                       *
                * Que o poder das sombras continue a guiá-lo.           *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void FinishHealer() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   Vitória do Curandeiro das Entidades!                  *
                *                                                         *
                * Com sua habilidade de restaurar e proteger, você        *
                * trouxe uma breve pausa ao caos. Sua luz em meio ao      *
                * horror cósmico trouxe esperança e renovação.            *
                *                                                         *
                * A batalha chega a um fim, mas novos horrores aguardam   *
                * sua cura. Continue sua missão com fé e compaixão.       *
                *                                                         *
                * Que a luz das entidades continue a iluminar seu caminho.*
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void FinishRogue() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   Vitória do Ladrão das Sombras!                        *
                *                                                         *
                * Com astúcia e habilidades furtivas, você navegou        *
                * através das teias do horror cósmico e emergiu vitorioso.*
                * Seus truques e manobras foram cruciais na sua           *
                * jornada.                                                *
                *                                                         *
                * O caminho para o próximo nível se desdobra, revelando   *
                * novos enigmas e desafios.                               *
                *                                                         *
                * Que sua astúcia continue a guiá-lo através do caos.     *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }
}
