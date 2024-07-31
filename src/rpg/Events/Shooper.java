package rpg.Events;

import rpg.Classes.Attributes;
import rpg.itens.BagItens.Potion;
import rpg.itens.Item;
import rpg.Utils.SlowConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shooper implements NonCombatEvent {
    private SlowConsole slowConsole = new SlowConsole();
    private Scanner scanner = new Scanner(System.in);

    private List<Item> itemsForSale;

    public Shooper() {
        // Inicializa a lista de itens que o vendedor oferece
        itemsForSale = new ArrayList<>();
        // Adiciona itens para venda
        itemsForSale.add(new Potion("Poção de Vida Menor", 15, 50));
        itemsForSale.add(new Potion("Poção de Vida Média", 25, 100));
        itemsForSale.add(new Potion("Poção de Vida Maior", 50, 200));
    }

    @Override
    public void executeEvent(Attributes personagem) {
        boolean shopping = true;
        while (shopping) {
            // Exibe o ouro atual do jogador
            slowConsole.imprimirDevagar("Você tem " + personagem.getGold() + " Ouro.");

            slowConsole.imprimirDevagar("=== Você encontrou um vendedor! ===");
            slowConsole.imprimirDevagar("=== Itens à venda ===");

            // Exibe os itens disponíveis para venda
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

    private void buyItem(Attributes personagem, Item item) {
        if (personagem.getGold() >= item.getPrice()) {
            personagem.setGold(personagem.getGold() - item.getPrice());
            personagem.addItemToBag(item);
            itemsForSale.remove(item); // Remove o item da lista de itens à venda
            slowConsole.imprimirDevagar("Você comprou " + item.getName() + " por " + item.getPrice() + " Ouro.");
        } else {
            slowConsole.imprimirDevagar("Ouro insuficiente para comprar " + item.getName() + ".");
        }
    }
}
