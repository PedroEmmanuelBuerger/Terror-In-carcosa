package rpg.Classes;

public class Healer extends Attributes {
    int mana;

    public Healer(String name, int healthbar, int Mana, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        this.mana = Mana;
        this.setClasses("Healer");
    }

    @Override
    public void attackWithSpecial(Attributes enemy) {
        int holyDamage = this.getSpecial() / 2;
        System.out.println(this.getName() + " conjurou um feitiço sagrado, Causando a " + enemy.getName() + "  " + holyDamage + " de dano!");
        mana = mana - 15;
        System.out.println(this.getName() + " gastou 15 de mana, ficando com " + mana + " Restante");
        enemy.takeDamage(holyDamage);
    }

    public void heal(Attributes ally) {
        int healAmount = getSpecial() + ally.getHealthbar();
        if (ally.getMaxHealthInitial() < healAmount) {
            ally.setHealthbar(ally.getMaxHealthInitial());
            System.out.println(getName() + " curou toda a vida de " + ally.getName() + "!");
            GetHealth(ally);
        } else {
            ally.setHealthbar(healAmount);
            System.out.println(getName() + " curou " + getSpecial() + " de vida de " + ally.getName() + "!");
            GetHealth(ally);
        }
    }

    public void ressurection(Attributes ally) {
        ally.setHealthbar(ally.getMaxHealthInitial() / 2);
        System.out.println(ally.getName() + " foi ressuscitado! Sua vida atual é " + ally.getHealthbar());
        mana -= 35;
        System.out.println(getName() + " gastou 35 de mana, ficando com " + mana + " restante.");
    }
}
