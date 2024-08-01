package rpg.itens.Weapons.Initials;

import rpg.itens.Weapons.Weapon;

/**
 * @param attack Atributo para armazenar o valor de ataque
 */
public record Ring(int attack) implements Weapon {

    @Override
    public int getPrice() {
        return 15;
    }
    // Inicializa o valor de ataque

    @Override
    public String getName() {
        return "Anel";
    }
}
