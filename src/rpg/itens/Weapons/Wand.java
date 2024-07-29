package rpg.itens.Weapons;

public class Wand implements Weapon {
    private int attack; // Atributo para armazenar o valor de ataque
    private String Name = "Varinha";

    public Wand(int attack) {
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
