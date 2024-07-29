package rpg.itens.Weapons;

public class SwordOfThousandTruths implements Weapon {
    private int attack; // Atributo para armazenar o valor de ataque
    private String Name = "Espada de mil verdades";

    public SwordOfThousandTruths(int attack) {
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
