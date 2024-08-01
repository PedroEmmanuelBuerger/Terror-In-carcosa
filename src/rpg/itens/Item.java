package rpg.itens;

import rpg.Character.Classes.Attributes;

public abstract class Item {
    private final String name;
    private final int price; // Preço do item

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public void UseItem(Attributes player) {
    }
}
