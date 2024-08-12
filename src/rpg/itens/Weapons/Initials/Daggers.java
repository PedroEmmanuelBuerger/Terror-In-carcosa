package rpg.itens.Weapons.Initials;

import rpg.itens.Weapons.Weapon;

/**
 * @param attack
 */
public record Daggers(int attack) implements Weapon {

    @Override
    public int getPrice() {
        return 15;
    }

    @Override
    public String getName() {
        return "Adagas";
    }
}
