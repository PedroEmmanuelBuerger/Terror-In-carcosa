package rpg.Events;

import rpg.Character.Classes.*;
import rpg.itens.Weapons.*;
import rpg.Utils.SlowConsole;

import java.util.Random;
import java.util.Scanner;

public class NewWeapon implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();

    @Override
    public void executeEvent(Attributes personagem) {
        // Cria a lista de armas disponíveis com base no tipo de personagem
        Weapon[] weapons = getAvailableWeapons(personagem);

        // Seleciona uma arma aleatória da lista
        Random random = new Random();
        Weapon newWeapon = weapons[random.nextInt(weapons.length)];

        // Exibe informações sobre a nova arma encontrada
        slowConsole.imprimirDevagar("Você encontrou uma arma antiga e corrompida: " + newWeapon.getName() + " com dano (" + newWeapon.attack() + ").");

        // Exibe informações sobre a arma atual, se houver
        Weapon currentWeapon = personagem.getWeapon();
        if (currentWeapon != null) {
            slowConsole.imprimirDevagar("Sua arma atual é: " + currentWeapon.getName() + " com dano (" + currentWeapon.attack() + ").");
        }

        // Pergunta ao jogador se deseja equipar a nova arma
        slowConsole.imprimirDevagar("Deseja equipar esta arma profana? (s/n)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        // Atualiza a arma do personagem com base na escolha
        if (choice.equalsIgnoreCase("s")) {
            if (currentWeapon != null) {
                // Calcula a diferença de dano entre a nova arma e a arma atual
                int damageDifference = newWeapon.attack() - currentWeapon.attack();

                // Atualiza o ataque ou ataque especial do personagem
                switch (personagem) {
                    case Warrior warrior -> warrior.setAttack(warrior.getAttack() + damageDifference);
                    case Rogue rogue -> rogue.setAttack(rogue.getAttack() + damageDifference);
                    case Healer healer -> healer.setSpecial(healer.getSpecial() + damageDifference);
                    case Mage mage -> mage.setSpecial(mage.getSpecial() + damageDifference);
                    case Necromancer necromancer -> necromancer.setSpecial(necromancer.getSpecial() + damageDifference);
                    default -> {
                    }
                }
            } else {
                // Se o personagem não tem uma arma atual, simplesmente define o valor da nova arma
                switch (personagem) {
                    case Warrior warrior -> warrior.setAttack(newWeapon.attack());
                    case Rogue rogue -> rogue.setAttack(newWeapon.attack());
                    case Healer healer -> healer.setSpecial(newWeapon.attack());
                    case Mage mage -> mage.setSpecial(newWeapon.attack());
                    case Necromancer necromancer -> necromancer.setSpecial(newWeapon.attack());
                    default -> {
                    }
                }
            }

            // Atualiza a arma do personagem
            personagem.setWeapon(newWeapon);

            // Exibe mensagem de sucesso
            slowConsole.imprimirDevagar(personagem.getName() + " empunha agora a nova arma: " + newWeapon.getName() + " com dano " + newWeapon.attack() + ", uma adição sinistra ao seu arsenal.");
        } else {
            slowConsole.imprimirDevagar(personagem.getName() + " decidiu não equipar a arma, como se um pressentimento de mal-estar o tivesse envolvido.");
        }
    }

    private Weapon[] getAvailableWeapons(Attributes personagem) {
        if (personagem instanceof Warrior) {
            return new Weapon[] {
                    new Zheiwender(), // Dano entre 5 e 10
                    new SwordOfThousandTruths() // Dano entre 15 e 20
            };
        } else if (personagem instanceof Rogue) {
            return new Weapon[] {
                    new Bayoneta(), // Dano entre 5 e 10
                    new Crossbow() // Dano entre 10 e 15
            };
        } else if (personagem instanceof Healer) {
            return new Weapon[] {
                    new Necronomicon(), // Dano entre 50 e 100
                    new Bible() // Dano entre 15 e 30
            };
        } else if (personagem instanceof Mage) {
            return new Weapon[] {
                    new StickOfTruth(), // Dano entre 10 e 20
                    new Wand() // Dano entre 20 e 30
            };
        } else if (personagem instanceof Necromancer) {
            return new Weapon[] {
                    new DaggersOfSouls(), // Dano entre 10 e 20
                    new DemonHearth() // Dano entre 20 e 30
            };
        }
        return new Weapon[0]; // Retorna uma lista vazia se o tipo de personagem não for reconhecido
    }
}
