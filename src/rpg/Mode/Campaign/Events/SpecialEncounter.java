package rpg.Mode.Campaign.Events;

import rpg.Character.Classes.*;
import rpg.Utils.SlowConsole;
import rpg.itens.Specials.SpeelBook;

public class SpecialEncounter implements NonCombatEvent {
    private static boolean mageEncounterOccurred = false;
    private static boolean warriorEncounterOccurred = false;
    private static boolean rogueEncounterOccurred = false;
    private static boolean healerEncounterOccurred = false;
    private static boolean NecromancerEncounterOcurred = false;

    private final SlowConsole slowConsole = new SlowConsole();
    private final SpeelBook speelBook;

    public SpecialEncounter(SpeelBook speelBook) {
        this.speelBook = speelBook;
    }

    @Override
    public void executeEvent(Attributes personagem) {
        if (personagem instanceof Mage mage) {
            if (!mageEncounterOccurred) {
                mage.setSpecial(mage.getSpecial() + 5);
                slowConsole.imprimirDevagar("Nas ruínas de uma biblioteca esquecida de Carcosa, você encontra um mago que murmura segredos arcanos. Você aprende um novo feitiço, gravado na sua mente como um pesadelo.");

                String spellName = "Tempestade de Raios";
                if (speelBook.hasSpell(spellName)) {
                    speelBook.addNewSpell(spellName, 20, 33); // Nome, custo de mana, dano
                } else {
                    slowConsole.imprimirDevagar("Os segredos já conhecidos da Tempestade de Raios ecoam na sua mente.");
                }

                mageEncounterOccurred = true;
            }
        } else if (personagem instanceof Warrior warrior) {
            if (!warriorEncounterOccurred) {
                warrior.setDefese(true);
                warrior.setMaxHealthInitial(warrior.getMaxHealthInitial() + 10);
                warrior.setHealthbar(warrior.getHealthbar() + 10);
                slowConsole.imprimirDevagar("Em uma arena oculta sob as estrelas negras de Carcosa, um guerreiro fantasmagórico aprimora sua armadura, enchendo-o com uma determinação sobrenatural. Você ganha 10 de vida e entra em modo de defesa.");

                warriorEncounterOccurred = true;
            }
        } else if (personagem instanceof Rogue rogue) {
            if (!rogueEncounterOccurred) {
                rogue.setDodgeSkills(rogue.getDodgeSkills() * 2);
                slowConsole.imprimirDevagar("Nas sombras de Carcosa, uma figura espectral lhe sussurra segredos antigos. Seus instintos se afiam, dobrando sua habilidade de evasão.");

                rogueEncounterOccurred = true;
            }
        } else if (personagem instanceof Healer healer) {
            if (!healerEncounterOccurred) {
                healer.setSpecial(healer.getSpecial() * 2);
                slowConsole.imprimirDevagar("Perante um altar profano dedicado a deidades esquecidas, você sente seu poder sagrado amplificado. Sua fé e poder são dobrados.");

                healerEncounterOccurred = true;
            }
        } else if (personagem instanceof Necromancer necromancer) {
            if (!NecromancerEncounterOcurred) {
                necromancer.setLimitImp(necromancer.getLimitImp() + 2);
                necromancer.setMaxMana(necromancer.getMaxMana() + 10);
                necromancer.setMana(necromancer.getMana() + 10);
                slowConsole.imprimirDevagar("Em um ritual proibido nas profundezas de Carcosa, você convoca horrores além da compreensão. A quantidade de esqueletos que pode controlar aumenta para " + necromancer.getLimitImp() + " e você recupera 10 de mana.");

                NecromancerEncounterOcurred = true;
            }
        } else if (personagem instanceof Paladin paladin) {
            if (!NecromancerEncounterOcurred) {
                paladin.setDefese(true);
                paladin.setAttack(paladin.getAttack() + 5);
                paladin.setSpecial(paladin.getSpecial() + 5);
                paladin.setHealthbar(paladin.getHealthbar() + 5);
                paladin.setMaxHealthInitial(paladin.getMaxHealthInitial() + 5);
                slowConsole.imprimirDevagar("Em um encontro com um grupo de paladinos antigos e obscuros seu discernimento se fortalece... entrou em modo de defesa e aumentou em 5 seu ataque, seu ataque especial e sua vida maxima.");
                NecromancerEncounterOcurred = true;
            }
        }
    }
}
