package rpg.Utils.Messages;

import rpg.Utils.SlowConsole;

public class Start {
    public static void startApp() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   Bem-vindo à Yellow City, aventureiro!             *
                *                                                     *
                * Você acorda em um lugar sombrio e úmido, com        *
                * as correntes de uma prisão antiga ao seu redor.     *
                * O ar é denso e frio, e o som distante de gotas      *
                * de água ecoa pelas paredes de pedra.                *
                *                                                     *
                * À sua frente, uma porta de ferro enferrujada        *
                * se ergue como o único caminho em meio à escuridão.  *
                *                                                     *
                * Você percebe que está em um complexo de dungeons,   *
                * uma série de andares interligados, cada um repleto  *
                * de perigos e enigmas. A única saída é descer mais   *
                * profundo, enfrentando os desafios que cada andar    *
                * tem a oferecer. A liberdade está ao alcance, mas    *
                * será necessário coragem e destreza para escapar.    *
                *                                                     *
                * Prepare-se, pois a jornada que se inicia não é      *
                * para os fracos de coração. Suavemente, você ouve    *
                * a voz de um guardião antigo sussurrar em seus       *
                * ouvidos: "A verdadeira aventura começa aqui."       *
                *                                                     *
                * Boa sorte, aventureiro. Que seus passos sejam       *
                * firmes e sua mente aguçada.                         *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void FinishFirstBoss() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   Vitória sobre Ghazkull!                            *
                *                                                      *
                * Com um rugido final, o gigantesco Ghazkull           *
                * caiu por terra, derrotado por sua bravura e          *
                * habilidade. A batalha foi árdua, mas sua             *
                * determinação e coragem prevaleceram.                 *
                *                                                      *
                * À medida que o imenso corpo do Ogro começa a         *
                * desaparecer, você sente a energia da dungeon         *
                * mudando ao seu redor. As paredes se movem, e         *
                * uma nova passagem se abre, revelando um caminho      *
                * para o segundo andar.                                *
                *                                                      *
                * Um estrondo ressoa pelos corredores enquanto uma     *
                * porta antiga se ergue à sua frente, convidando-o     *
                * para o próximo desafio. O ar está carregado de       *
                * uma sensação de antecipação e mistério, prometendo   *
                * novos enigmas e inimigos mais temíveis.              *
                *                                                      *
                * Prepare-se para desbravar o segundo andar, onde novos*
                * perigos e recompensas aguardam. A aventura está      *
                * longe de terminar, e a verdadeira prova está apenas  *
                * começando.                                           *
                *                                                      *
                * Boa sorte, aventureiro. Que seus passos sejam        *
                * firmes e sua coragem inabalável.                     *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void FinishDungeon() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   Vitória sobre o Lorde Rei Dragão!                   *
                *                                                       *
                * Com um último rugido de fúria e uma explosão de       *
                * fogo que iluminou a dungeon, o Lorde Rei Dragão       *
                * caiu derrotado. Sua imponente figura, agora desfeita, *
                * é um testemunho da força e coragem que você           *
                * demonstrou ao longo de sua jornada.                   *
                *                                                       *
                * Enquanto o corpo do dragão se transforma em cinzas,   *
                * uma abertura se revela no teto da caverna, banhada    *
                * pela luz da liberdade. O caminho para a saída se      *
                * torna claro, e um brilho de esperança ilumina seu     *
                * caminho.                                              *
                *                                                       *
                * Com o dragão derrotado, você sente um alívio profundo *
                * e a sensação de realização. A jornada chegou ao       *
                * seu clímax, mas uma nova vida fora da dungeon está    *
                * ao seu alcance.                                       *
                *                                                       *
                * A liberdade está diante de você, e com um último      *
                * olhar para os escombros da antiga prisão, você avança *
                * para a saída, pronto para novas aventuras no mundo    *
                * exterior.                                             *
                *                                                       *
                * Parabéns, aventureiro. Você conquistou sua liberdade  *
                * e escreveu seu nome nas lendas da Yellow City.        *
                *                                                       *
                * Que seus futuros desafios sejam tão grandiosos quanto *
                * sua vitória sobre o Lorde Rei Dragão.                 *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void EncounterGhazkull() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   O Encontro com Ghazkull!                            *
                *                                                       *
                * À medida que você avança pelas profundezas da dungeon,*
                * o ar torna-se mais denso e as sombras mais            *
                * opressivas. Uma sensação de dread paira no ar,        *
                * como se algo terrível estivesse à espreita.           *
                *                                                       *
                * Finalmente, você chega a uma vasta câmara. No centro, *
                * um imenso trono de ossos ergue-se em um brilho        *
                * sinistro. Sentado nele, o temível Ghazkull, o senhor  *
                * das trevas, aguarda com um olhar gélido e ameaçador.  *
                *                                                       *
                * Seu rugido ecoa pelas paredes enquanto ele se levanta,*
                * demonstrando o poder que detém sobre este domínio.    *
                * O momento da verdade chegou.                          *
                *                                                       *
                * Prepare-se para enfrentar o temível Ghazkull!         *
                *                                                       *
                * O destino da dungeon está em suas mãos agora.         *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }

    public static void EncounterLordDragonKing() {
        SlowConsole slowConsole = new SlowConsole();
        slowConsole.imprimirDevagar("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   O Encontro com o Lorde Rei Dragão!                  *
                *                                                       *
                * Você segue adentrando as profundezas da dungeon,      *
                * onde o ar torna-se escaldante e a atmosfera           *
                * carregada de tensão. O chão treme sob seus pés,       *
                * e o som de um rugido estrondoso reverbera pelas       *
                * paredes.                                              *
                *                                                       *
                * À medida que você se aproxima, uma câmara colossal    *
                * revela-se diante de você. No centro, o Lorde Rei      *
                * Dragão ergue-se em toda sua glória aterrorizante.     *
                * Suas escamas reluzem em um brilho dourado e suas      *
                * asas imensas se estendem, lançando sombras            *
                * sinistras sobre o chão.                               *
                *                                                       *
                * O dragão encarará você com olhos flamejantes,         *
                * demonstrando que é o mestre absoluto desta dungeon.   *
                *                                                       *
                * Esta é a batalha final. Prepare-se para o confronto   *
                * mais épico de sua jornada!                            *
                *                                                       *
                * A liberdade e a vitória estão ao seu alcance.         *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
    }
}
