package rpg.Classes;

public class Mage extends Attributes {
    private int mana;
    public Mage(String name, int healthbar, int Mana, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        this.mana = Mana;
        this.setClasses("Mage");
    }
    @Override
    public void attack(Attributes enemy) {
        int damage = this.getAttack();
        System.out.println(this.getName() + " lançou uma bola de fogo em " + enemy.getName() + " causando " + damage + " de dano!");
        enemy.takeDamage(damage);
    }

    @Override
    public void attackWithSpecial(Attributes enemy) {
        int damage = this.getSpecial();
        System.out.println(this.getName() + " conjurou um feitiço poderoso em " + enemy.getName() + " causando " + getSpecial() + " de dano!");
        mana = mana - 25;
        System.out.println(this.getName() + " gastou 25 de mana, ficando com " + mana + " Restante");
        enemy.takeDamage(damage);
    }
}
