package rpg.itens.Armors;

public class LeatherUndefinedColor implements Armor {

    @Override
    public int armor() {
        return 4; // Defesa leve
    }

    @Override
    public String getName() {
        return "Couro de Cor Indefinida";
    }

    @Override
    public int getPrice() {
        return 30;
    }
}
