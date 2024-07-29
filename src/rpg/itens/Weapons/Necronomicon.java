package rpg.itens.Weapons;

public class Necronomicon implements Weapon {
    private int attack; // Atributo para armazenar o valor de ataque
    private String Name = "Livro dos mortos: Necronomicon";

    public Necronomicon(int attack) {
        this.attack = attack; // Inicializa o valor de ataque
    }

    @Override
    public int getAttack() {
        return attack; // Retorna o valor de ataque
    }

    @Override
    public String getName() {
        return Name;
    }
}
