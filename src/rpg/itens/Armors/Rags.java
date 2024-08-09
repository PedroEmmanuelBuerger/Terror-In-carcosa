package rpg.itens.Armors;

public class Rags implements Armor {

    @Override
    public int armor() {
        return 1;
    }

    @Override
    public String getName() {
        return "Trapos";
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
