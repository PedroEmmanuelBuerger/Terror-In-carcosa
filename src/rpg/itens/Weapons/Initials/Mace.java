package rpg.itens.Weapons.Initials;

import rpg.itens.Weapons.Weapon;

public class Mace implements Weapon {
    private int price = 15;

    @Override
    public int getPrice() {
        return price;
    }
    private int attack; // Atributo para armazenar o valor de ataque
    private String Name = "Maça";
    public Mace(int attack) {
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
