package rpg.Events;

import rpg.Character.Classes.*;
import rpg.itens.BagItens.ManaPotion;
import rpg.itens.BagItens.Potion;
import rpg.itens.Item;
import rpg.itens.Weapons.*;
import rpg.Utils.SlowConsole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Shooper implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();
    private final Scanner scanner = new Scanner(System.in);

    // Lista de todos os itens possíveis
    private final List<Item> allItems;

    // Listas de itens e armas disponíveis para venda
    private final List<Item> itemsForSale;
    private final List<Weapon> weaponsForSale;

    // Construtor
    public Shooper() {
        allItems = new ArrayList<>();
        allItems.add(new Potion("Poção de Vida Menor", 15, 50));
        allItems.add(new Potion("Poção de Vida Média", 25, 100));
        allItems.add(new Potion("Poção de Vida Maior", 50, 200));
        allItems.add(new ManaPotion("Poção de Mana Menor", 20, 30));
        allItems.add(new ManaPotion("Poção de Mana Média", 35, 60));
        allItems.add(new ManaPotion("Poção de Mana Maior", 60, 120));

        List<Weapon> allWeapons = new ArrayList<>();
        allWeapons.add(new Zheiwender()); // Exemplo de arma
        allWeapons.add(new SwordOfThousandTruths()); // Exemplo de arma
        allWeapons.add(new Bayoneta()); // Exemplo de arma
        allWeapons.add(new Crossbow()); // Exemplo de arma
        allWeapons.add(new Necronomicon()); // Exemplo de arma
        allWeapons.add(new Bible()); // Exemplo de arma
        allWeapons.add(new StickOfTruth()); // Exemplo de arma
        allWeapons.add(new Wand()); // Exemplo de arma
        allWeapons.add(new DaggersOfSouls()); // Exemplo de arma
        allWeapons.add(new DemonHearth()); // Exemplo de arma

        // Inicializa as listas de venda com itens e armas disponíveis
        itemsForSale = new ArrayList<>(allItems);
        weaponsForSale = new ArrayList<>(allWeapons);
    }

    @Override
    public void executeEvent(Attributes personagem) {
        boolean shopping = true;
        while (shopping) {
            // Exibe o ouro atual do jogador
            slowConsole.imprimirDevagar("Você tem " + personagem.getGold() + " Ouro.");

            // Inicializa as listas de itens e armas à venda
            List<Item> availableItems = getRandomItems(personagem);
            List<Weapon> availableWeapons = getWeaponsForSale(personagem);

            // Exibe o vendedor e os itens à venda
            slowConsole.imprimirDevagar("=== Você encontrou um vendedor! ===");
            slowConsole.imprimirDevagar("Escolha o tipo de item para visualizar:");
            slowConsole.imprimirDevagar("1. Poções");
            slowConsole.imprimirDevagar("2. Armas");
            slowConsole.imprimirDevagar("3. Voltar ao Jogo");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            if (choice == 3) {
                shopping = false; // Sai do loop para voltar ao jogo
            } else if (choice == 1) {
                // Exibe poções
                displayItems(availableItems);
                // Processa a compra de poções
                int potionChoice = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner
                if (potionChoice > 0 && potionChoice <= availableItems.size()) {
                    buyItem(personagem, availableItems.get(potionChoice - 1));
                } else {
                    slowConsole.imprimirDevagar("Opção inválida. Volte ao menu principal.");
                }
            } else if (choice == 2) {
                // Exibe armas
                displayWeapons(availableWeapons);
                // Processa a compra de armas
                int weaponChoice = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner
                if (weaponChoice > 0 && weaponChoice <= availableWeapons.size()) {
                    buyWeapon(personagem, availableWeapons.get(weaponChoice - 1));
                } else {
                    slowConsole.imprimirDevagar("Opção inválida. Volte ao menu principal.");
                }
            } else {
                slowConsole.imprimirDevagar("Opção inválida. Tente novamente.");
            }
        }
    }

    private List<Item> getRandomItems(Attributes personagem) {
        List<Item> itemsForSale = new ArrayList<>();

        // Adiciona itens condicionais baseados na classe do personagem
        if (personagem instanceof Necromancer || personagem instanceof Mage || personagem instanceof Healer) {
            for (Item item : allItems) {
                if (item instanceof ManaPotion) {
                    itemsForSale.add(item);
                }
            }
        }

        // Adiciona itens não condicionais
        for (Item item : allItems) {
            if (!(item instanceof ManaPotion)) {
                itemsForSale.add(item);
            }
        }

        // Embaralha e limita a 3 itens
        Collections.shuffle(itemsForSale);
        return itemsForSale.subList(0, Math.min(3, itemsForSale.size()));
    }

    private List<Weapon> getWeaponsForSale(Attributes personagem) {
        List<Weapon> weaponsForSale = new ArrayList<>();
        // Adiciona armas disponíveis baseadas na classe do personagem
        Collections.addAll(weaponsForSale, getAvailableWeapons(personagem));

        // Embaralha e limita a 3 armas
        Collections.shuffle(weaponsForSale);
        return weaponsForSale.subList(0, Math.min(3, weaponsForSale.size()));
    }

    private void displayItems(List<Item> items) {
        slowConsole.imprimirDevagar("=== " + "Poções" + " à venda ===");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            slowConsole.imprimirDevagar((i + 1) + ". " + item.getName() + " - " + item.getPrice() + " Ouro");
        }
        slowConsole.imprimirDevagar((items.size() + 1) + ". Voltar ao menu principal");
    }

    private void displayWeapons(List<Weapon> weapons) {
        slowConsole.imprimirDevagar("=== " + "Armas" + " à venda ===");
        for (int i = 0; i < weapons.size(); i++) {
            Weapon weapon = weapons.get(i);
            slowConsole.imprimirDevagar((i + 1) + ". " + weapon.getName() + " - " + weapon.attack() + " de dano");
        }
        slowConsole.imprimirDevagar((weapons.size() + 1) + ". Voltar ao menu principal");
    }

    private void buyItem(Attributes personagem, Item item) {
        if (personagem.getBag().size() >= 5) {
            slowConsole.imprimirDevagar("Limite de 5 itens alcançados...");
        } else {
            if (personagem.getGold() >= item.getPrice()) {
                personagem.setGold(personagem.getGold() - item.getPrice());
                personagem.addItemToBag(item);
                slowConsole.imprimirDevagar("Você comprou " + item.getName() + " por " + item.getPrice() + " Ouro.");

                // Remove o item da lista de itens disponíveis
                itemsForSale.remove(item);
            } else {
                slowConsole.imprimirDevagar("Ouro insuficiente para comprar " + item.getName() + ".");
            }
        }
    }

    private void buyWeapon(Attributes personagem, Weapon weapon) {
        if (personagem.getGold() >= weapon.getPrice()) {
            personagem.setGold(personagem.getGold() - weapon.getPrice());
            personagem.setWeapon(weapon);
            slowConsole.imprimirDevagar("Você comprou " + weapon.getName() + " por " + weapon.getPrice() + " Ouro e equipou a arma.");

            // Remove a arma da lista de armas disponíveis
            weaponsForSale.remove(weapon);
        } else {
            slowConsole.imprimirDevagar("Ouro insuficiente para comprar " + weapon.getName() + ".");
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
