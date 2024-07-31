package rpg.itens;

import rpg.Classes.Attributes;

public abstract class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void UseItem(Attributes player) {
    }
}
