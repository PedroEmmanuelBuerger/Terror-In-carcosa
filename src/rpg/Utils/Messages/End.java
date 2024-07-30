package rpg.Utils.Messages;

import rpg.Utils.SlowConsole;

public class End {
    public static void FinishWarrior() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   Vitória do Guerreiro!                                *
                *                                                        *
                * Após uma batalha feroz e intensa, sua bravura e        *
                * força incomparáveis prevaleceram. Você enfrentou       *
                * e derrotou seus inimigos com uma destreza admirável.   *
                *                                                        *
                * O campo de batalha agora é seu, e a vitória é          *
                * celebrada em meio aos ecos das suas conquistas.        *
                *                                                        *
                * As portas da dungeon se abrem para um novo caminho,    *
                * revelando a próxima fase de sua jornada. O brilho da   *
                * vitória ilumina seu caminho e fortalece seu espírito.  *
                *                                                        *
                * Continue a avançar com coragem e honra. A jornada      *
                * ainda não acabou, e novos desafios esperam por você.   *
                *                                                        *
                * Que a sua força continue a ser sua guia e inspiração.  *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void FinishMage() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   Vitória do Mago!                                     *
                *                                                        *
                * Com uma explosão de magia e um brilho ofuscante,       *
                * suas habilidades arcanas derrotaram seus adversários.  *
                * A energia mística que você dominou agora brilha em     *
                * seu caminho para a vitória.                            *
                *                                                        *
                * O universo se abre diante de você, revelando novos     *
                * mistérios e desafios. A magia e a sabedoria são suas   *
                * aliadas na próxima etapa de sua jornada.               *
                *                                                        *
                * Continue a explorar e a desvendar os segredos ocultos. *
                * A verdadeira prova de seu poder ainda está por vir.    *
                *                                                        *
                * Que sua astúcia e conhecimento conduzam sua jornada.   *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void FinishNecromancer() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   Vitória do Necromante!                                  *
                *                                                           *
                * Com um comando sombrio e o poder das trevas, você         *
                * dominou seus inimigos e prevaleceu. A energia necromântica*
                * que você controla agora reverte a maré da batalha a       *
                * seu favor.                                                *
                *                                                           *
                * O reino das sombras se desvela à sua frente, abrindo      *
                * caminho para novos horrores e conquistas. A escuridão     *
                * é sua aliada, e os segredos das profundezas aguardam.     *
                *                                                           *
                * Continue a reinar sobre as forças do além. Seu domínio    *
                * sobre o oculto ainda está em ascensão.                    *
                *                                                           *
                * Que o poder das trevas guie seu caminho na jornada.       *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void FinishHealer() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   Vitória do Curandeiro!                                *
                *                                                         *
                * Com um toque de cura e uma força de espírito inabalável,*
                * você superou os desafios e trouxe equilíbrio à batalha. *
                * A sua habilidade em curar e proteger fez a diferença,   *
                * garantindo a vitória e a paz.                           *
                *                                                         *
                * À medida que o campo de batalha se acalma, uma nova     *
                * estrada se abre para você. O próximo nível é uma        *
                * oportunidade para continuar sua missão de cura e        *
                * benevolência.                                           *
                *                                                         *
                * Avance com compaixão e esperança. Seu caminho está      *
                * iluminado pela luz da vitória e da cura.                *
                *                                                         *
                * Que sua bondade continue a ser sua força e guia.        *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void FinishRogue() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   Vitória do Bandido!                                  *
                *                                                        *
                * Com astúcia e agilidade, você superou seus inimigos e  *
                * conquistou a vitória. Seus truques e habilidades de    *
                * furtividade foram fundamentais para seu sucesso.       *
                *                                                        *
                * O caminho para o próximo nível agora se revela,        *
                * trazendo novas oportunidades para testar sua esperteza.*
                *                                                        *
                * Continue a se infiltrar e a explorar. A jornada ainda  *
                * guarda muitos segredos e recompensas.                  *
                *                                                        *
                * Que sua astúcia e rapidez sejam sempre seus aliados.   *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }
}
