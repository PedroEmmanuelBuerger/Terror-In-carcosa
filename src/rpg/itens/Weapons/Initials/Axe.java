package rpg.itens.Weapons.Initials;

import rpg.itens.Weapons.Weapon;

public class Axe implements Weapon {
    private int attack; // Atributo para armazenar o valor de ataque
    private String Name = "Machado";
    public Axe(int attack) {
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