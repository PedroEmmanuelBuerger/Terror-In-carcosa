package rpg.itens.Armors;

public class DemonCarcass implements Armor {

    @Override
    public int armor() {
        return 10; // Defesa alta
    }

    @Override
    public String getName() {
        return "Carcaça Demoníaca";
    }

    @Override
    public int getPrice() {
        return 150;
    }
}
