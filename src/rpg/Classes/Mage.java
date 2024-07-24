package rpg.Classes;

import Utils.ManaAdm;

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
        boolean manaRes;
        ManaAdm manaAdm = new ManaAdm();
        manaRes = manaAdm.costMana(this.mana, 25, this.getName()); // Chamar costMana na instância criada
        if (!manaRes) {
            int damage = this.getSpecial();
            damage = damage + 15;
            System.out.println(this.getName() + " conjurou um feitiço poderoso em " + enemy.getName() + " causando " + damage+ " de dano!");
            mana = mana - 20;
            System.out.println(this.getName() + " gastou 25 de mana, ficando com " + mana + " Restante");
            enemy.takeDamage(damage);
            if (!enemy.isAlive()) {
                System.out.println("Vida total de " + enemy.getName() + " é 0");
                System.out.println(enemy.getName() + " foi derrotado!");
            }
        }
    }
}
