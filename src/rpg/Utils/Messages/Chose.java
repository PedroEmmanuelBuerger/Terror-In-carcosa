package rpg.Utils.Messages;

import rpg.Utils.SlowConsole;

public class Chose {
    public static void ChooseWarrior() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   O Guerreiro Valoroso!                               *
                *                                                       *
                * Com sua força incomparável e habilidades no combate,  *
                * o Guerreiro é a linha de frente da batalha,           *
                * destemido e incansável.                               *
                *                                                       *
                * Prepare-se para enfrentar qualquer desafio com coragem*
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void ChooseMage() {
        SlowConsole slowconsole = new SlowConsole();
        slowconsole.imprimirDevagar(
                """
                        * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                        *   O Mago Poderoso!                                    *
                        *                                                       *
                        * Mestre das artes arcanas, o Mago manipula a magia     *
                        * para lançar feitiços devastadores e controlar o campo *
                        * de batalha com sabedoria.                             *
                        *                                                       *
                        * Prepare-se para conjurar poderosos encantamentos!     *
                        * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                        """
        );
    }

    public static void ChooseRogue() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   O Ladino Astuto!                                    *
                *                                                       *
                * Ágil e sorrateiro, o Bandido usa sua esperteza e      *
                * habilidades furtivas para enganar e dominar seus      *
                * inimigos com precisão.                                *
                *                                                       *
                * Cuidado com suas tramas e truques!                    *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void ChooseHealer() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   O Curandeiro Benevolente!                           *
                *                                                       *
                * Com suas habilidades de cura e apoio, o Curandeiro    *
                * restaura a saúde e dá suporte vital a seus aliados,   *
                * mantendo a equipe forte e resistente.                 *
                *                                                       *
                * Confie na sua capacidade de manter todos saudáveis!   *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void ChooseNecromancer() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   O Necromante Sombrio!                               *
                *                                                       *
                * Mestre das artes obscuras, o Necromante convoca       *
                * criaturas das trevas e manipula a energia da morte    *
                * para subjugar seus inimigos.                          *
                *                                                       *
                * Prepare-se para enfrentar horrores das profundezas!   *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void ChooseOrc() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   O Orc Feroz!                                        *
                *                                                       *
                * Com sua força bruta e natureza selvagem, os Orcs      *
                * são guerreiros temidos, conhecidos por sua ferocidade *
                * e resistência.                                        *
                *                                                       *
                * Prepare-se para uma batalha de força imensa!          *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);

    }

    public static void ChooseElf() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   O Elfo Ágil!                                        *
                *                                                       *
                * Elegantes e ágeis, os Elfos são mestres em arqueria e *
                * têm uma conexão profunda com a natureza, utilizando   *
                * suas habilidades naturais em combate.                 *
                *                                                       *
                * Prepare-se para uma luta rápida e precisa!            *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void ChooseDwarf() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   O Anão Resistente!                                  *
                *                                                       *
                * Com sua resistência e habilidades em mineração,       *
                * os Anões são mestres em combate em ambientes fechados *
                * e têm uma força inabalável.                           *
                *                                                       *
                * Confie na força e resistência dos anões!              *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void ChooseHuman() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   O Humano Versátil!                                  *
                *                                                       *
                * Adaptáveis e resilientes, os Humanos têm uma          *
                * incrível capacidade de aprender e se adaptar a        *
                * qualquer situação, tornando-os aliados valiosos.      *
                *                                                       *
                * Aproveite a versatilidade dos humanos em batalha!     *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }
}
