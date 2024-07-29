package rpg.itens.Weapons;

public class DemonHearth implements Weapon {
    private int attack; // Atributo para armazenar o valor de ataque
    private String Name = "Coração de demonio";

    public DemonHearth(int attack) {
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
