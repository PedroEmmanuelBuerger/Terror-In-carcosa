package rpg.itens.Armors;

public class UltradimensionalOctopusSkin implements Armor {

    @Override
    public int armor() {
        return 8; // Defesa alta
    }

    @Override
    public String getName() {
        return "Pele de Polvo Ultradimensional";
    }

    @Override
    public int getPrice() {
        return 120;
    }
}
