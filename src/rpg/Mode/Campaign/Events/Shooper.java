package rpg.Mode.Campaign.Events;

import rpg.Character.Classes.*;
import rpg.itens.BagItens.ManaPotion;
import rpg.itens.BagItens.Potion;
import rpg.itens.Item;
import rpg.Utils.SlowConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shooper implements NonCombatEvent {
    private final SlowConsole slowConsole = new SlowConsole();
    private final Scanner scanner = new Scanner(System.in);

    private final List<Item> itemsForSale;

    public Shooper() {
        itemsForSale = new ArrayList<>();
        itemsForSale.add(new Potion("Poção de Vida Menor", 15, 50));
        itemsForSale.add(new Potion("Poção de Vida Média", 25, 100));
        itemsForSale.add(new Potion("Poção de Vida Maior", 50, 200));
    }

    @Override
    public void executeEvent(Attributes personagem) {
        slowConsole.imprimirDevagar("=== Você encontrou um vendedor! ===");
        boolean shopping = true;
        if (personagem instanceof Necromancer || personagem instanceof Mage || personagem instanceof Healer) {
            itemsForSale.add(new ManaPotion("Poção de Mana Menor", 20, 30));
            itemsForSale.add(new ManaPotion("Poção de Mana Média", 35, 60));
            itemsForSale.add(new ManaPotion("Poção de Mana Maior", 60, 120));
        }

        while (shopping) {
            slowConsole.imprimirDevagar("Você tem " + personagem.getGold() + " Ouro.");
            slowConsole.imprimirDevagar("=== Itens à venda ===");

            for (int i = 0; i < itemsForSale.size(); i++) {
                Item item = itemsForSale.get(i);
                slowConsole.imprimirDevagar((i + 1) + ". " + item.getName() + " - " + item.getPrice() + " Ouro");
            }
            slowConsole.imprimirDevagar((itemsForSale.size() + 1) + ". Voltar ao Jogo");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == itemsForSale.size() + 1) {
                shopping = false;
            } else if (choice > 0 && choice <= itemsForSale.size()) {
                Item selectedItem = itemsForSale.get(choice - 1);
                buyItem(personagem, selectedItem);
                if (itemsForSale.isEmpty()) {
                    slowConsole.imprimirDevagar("Não há mais itens à venda.");
                    shopping = false;
                }
            } else {
                slowConsole.imprimirDevagar("Opção inválida. Tente novamente.");
            }
        }
    }

    private void buyItem(Attributes personagem, Item item) {
        if (personagem.getGold() >= item.getPrice()) {
            personagem.setGold(personagem.getGold() - item.getPrice());
            personagem.addItemToAbyssalInventory(item);
            itemsForSale.remove(item);
            slowConsole.imprimirDevagar("Você comprou " + item.getName() + " por " + item.getPrice() + " Ouro.");
        } else {
            slowConsole.imprimirDevagar("Ouro insuficiente para comprar " + item.getName() + ".");
        }
    }
}
