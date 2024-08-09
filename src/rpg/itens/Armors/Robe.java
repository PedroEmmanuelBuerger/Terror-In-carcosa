package rpg.itens.Armors;

public class Robe implements Armor {

    @Override
    public int armor() {
        return 5; // Defesa média
    }

    @Override
    public String getName() {
        return "Robe Místico";
    }

    @Override
    public int getPrice() {
        return 50;
    }

    @Override
    public int getMana() {
        return 10; // Adiciona mana
    }
}
