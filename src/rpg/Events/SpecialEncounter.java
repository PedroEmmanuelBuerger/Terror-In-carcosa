package rpg.Events;

import rpg.Classes.*;
import rpg.Utils.SlowConsole;
import rpg.itens.Specials.SpeelBook;

public class SpecialEncounter implements NonCombatEvent {
    private static boolean mageEncounterOccurred = false;
    private static boolean warriorEncounterOccurred = false;
    private static boolean rogueEncounterOccurred = false;
    private static boolean healerEncounterOccurred = false;
    private static boolean NecromancerEncounterOcurred = false;

    private SlowConsole slowConsole = new SlowConsole();
    private SpeelBook speelBook;

    public SpecialEncounter(SpeelBook speelBook) {
        this.speelBook = speelBook;
    }

    @Override
    public void executeEvent(Attributes personagem) {
        if (personagem instanceof Mage) {
            Mage mage = (Mage) personagem;
            if (!mageEncounterOccurred) {
                mage.setSpecial(mage.getSpecial() + 5);
                slowConsole.imprimirDevagar("Você encontrou um mago conhecido na dungeon! Aprendeu um espaço novo de magia!");

                String spellName = "Tempestade de Raios";
                if (!speelBook.hasSpell(spellName)) {
                    // Adiciona a nova magia ao SpeelBook
                    speelBook.addNewSpell(spellName, 20, 33); // Nome, custo de mana, dano
                } else {
                    slowConsole.imprimirDevagar("Você já conhece a magia " + spellName + ".");
                }

                mageEncounterOccurred = true; // Marca o evento como ocorrido
            }
        } else if (personagem instanceof Warrior) {
            Warrior warrior = (Warrior) personagem;
            if (!warriorEncounterOccurred) {
                warrior.setDefese(true);
                warrior.setMaxHealthInitial(warrior.getMaxHealthInitial() + 10);
                warrior.setHealthbar(warrior.getHealthbar() + 10);
                slowConsole.imprimirDevagar("Você encontrou um companheiro guerreiro na dungeon! Poliu sua armadura e entrou em modo de defesa, ganhando também mais 10 de vida!");

                warriorEncounterOccurred = true; // Marca o evento como ocorrido
            }
        } else if (personagem instanceof Rogue) {
            Rogue rogue = (Rogue) personagem;
            if (!rogueEncounterOccurred) {
                rogue.setDodgeSkills(rogue.getDodgeSkills() * 2);
                slowConsole.imprimirDevagar("Você encontrou alguém que te lembra das ruas... Seus instintos melhoraram, dobrando sua evasão!");

                rogueEncounterOccurred = true; // Marca o evento como ocorrido
            }
        } else if (personagem instanceof Healer) {
            Healer healer = (Healer) personagem;
            if (!healerEncounterOccurred) {
                healer.setSpecial(healer.getSpecial() * 2);
                slowConsole.imprimirDevagar("Você encontrou uma estátua de seu deus! Seu poder guiado pela sua fé é dobrado!");

                healerEncounterOccurred = true; // Marca o evento como ocorrido
            }
        } else if (personagem instanceof Necromancer) {
            Necromancer necromancer = (Necromancer) personagem;
            if (!NecromancerEncounterOcurred) {
                necromancer.setLimitImp(necromancer.getLimitImp() + 2);
                necromancer.setMaxMana(necromancer.getMaxMana() + 10);
                necromancer.setMana(necromancer.getMana() + 10);
                slowConsole.imprimirDevagar("Você encontrou um culto sobre seu deus mesopotanico, quantidade de esqueletos aumentou para " + necromancer.getLimitImp() + " e recuperou 10 de mana!");
                NecromancerEncounterOcurred = true; // Marca o evento como ocorrido
            }
        }
    }
}