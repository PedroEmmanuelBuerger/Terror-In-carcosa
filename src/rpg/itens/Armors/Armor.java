package rpg.itens.Armors;

public interface Armor {
    int armor();
    String getName();
    int getPrice();
    default int getMana() {
        return 0;
    }
}
