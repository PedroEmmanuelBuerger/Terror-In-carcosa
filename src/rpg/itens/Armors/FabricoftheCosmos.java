package rpg.itens.Armors;

public class FabricoftheCosmos implements Armor {

    @Override
    public int armor() {
        return 12; // Defesa muito alta
    }

    @Override
    public String getName() {
        return "Fabrico do Cosmos";
    }

    @Override
    public int getPrice() {
        return 200;
    }
}
