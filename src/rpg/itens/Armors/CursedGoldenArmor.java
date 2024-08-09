package rpg.itens.Armors;

public class CursedGoldenArmor implements Armor {

    @Override
    public int armor() {
        return 15; // Defesa muito alta
    }

    @Override
    public String getName() {
        return "Armadura Dourada Amaldiçoada";
    }

    @Override
    public int getPrice() {
        return 300;
    }
}
