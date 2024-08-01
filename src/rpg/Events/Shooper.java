package rpg.Events;

import rpg.Character.Classes.Attributes;
import rpg.Character.Classes.Healer;
import rpg.Character.Classes.Mage;
import rpg.Character.Classes.Necromancer;
import rpg.itens.BagItens.ManaPotion;
import rpg.itens.BagItens.Potion;
import rpg.itens.Item;
import rpg.Utils.SlowConsole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Shooper implements NonCombatEvent {
    private SlowConsole slowConsole = new SlowConsole();
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    // Lista de todos os itens possíveis
    private List<Item> allItems;

    // Construtor
    public Shooper() {
        allItems = new ArrayList<>();
        allItems.add(new Potion("Poção de Vida Menor", 15, 50));
        allItems.add(new Potion("Poção de Vida Média", 25, 100));
        allItems.add(new Potion("Poção de Vida Maior", 50, 200));
        allItems.add(new ManaPotion("Poção de Mana Menor", 20, 30));
        allItems.add(new ManaPotion("Poção de Mana Média", 35, 60));
        allItems.add(new ManaPotion("Poção de Mana Maior", 60, 120));
    }

    @Override
    public void executeEvent(Attributes personagem) {
        boolean shopping = true;
        while (shopping) {
            // Exibe o ouro atual do jogador
            slowConsole.imprimirDevagar("Você tem " + personagem.getGold() + " Ouro.");

            // Inicializa a lista de itens à venda
            List<Item> itemsForSale = getRandomItems(personagem);

            // Exibe o vendedor e os itens à venda
            slowConsole.imprimirDevagar("=== Você encontrou um vendedor! ===");
            slowConsole.imprimirDevagar("=== Itens à venda ===");
            for (int i = 0; i < itemsForSale.size(); i++) {
                Item item = itemsForSale.get(i);
                slowConsole.imprimirDevagar((i + 1) + ". " + item.getName() + " - " + item.getPrice() + " Ouro");
            }
            slowConsole.imprimirDevagar((itemsForSale.size() + 1) + ". Voltar ao Jogo");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            if (choice == itemsForSale.size() + 1) {
                shopping = false; // Sai do loop para voltar ao jogo
            } else if (choice > 0 && choice <= itemsForSale.size()) {
                // Processa a compra do item selecionado
                Item selectedItem = itemsForSale.get(choice - 1);
                buyItem(personagem, selectedItem);
                // Atualiza a exibição dos itens após a compra
                if (itemsForSale.isEmpty()) {
                    slowConsole.imprimirDevagar("Não há mais itens à venda.");
                    shopping = false; // Sai do loop se não há mais itens à venda
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

    private void buyItem(Attributes personagem, Item item) {
        if (personagem.getBag().size() >= 5) {
            slowConsole.imprimirDevagar("Limite de 5 itens alcançados...");
        } else {
            if (personagem.getGold() >= item.getPrice()) {
                personagem.setGold(personagem.getGold() - item.getPrice());
                personagem.addItemToBag(item);
                slowConsole.imprimirDevagar("Você comprou " + item.getName() + " por " + item.getPrice() + " Ouro.");
            } else {
                slowConsole.imprimirDevagar("Ouro insuficiente para comprar " + item.getName() + ".");
            }
        }
    }
}
