package exer2.rpg.Classes;

public class Healer extends Attributes {
    int mana;
    public Healer(String name, int healthbar,int Mana, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        this.mana = Mana;
        this.setClasses("Healer");
    }
    @Override
    public void attackWithSpecial(Attributes enemy) {
        int holyDamage = this.getSpecial() / 2;
        System.out.println(this.getName() + " conjurou um feitiço sagrado, Causando a "+enemy.getName() +"  "+holyDamage+" de dano!");
        mana = mana - 15;
        System.out.println(this.getName() + " gastou 15 de mana, ficando com " + mana + " Restante");
        enemy.takeDamage(holyDamage);
    }
    public void heal(Attributes Ally) {
        int heal = this.getSpecial() + Ally.getHealthbar();
        if (Ally.getMaxHealthInitial() < heal){
            Ally.setHealthbar(Ally.getMaxHealthInitial());
            System.out.println(this.getName() + " Curou Toda a vida de " + Ally.getName() + "!");
            GetHealth(Ally);
        }
        else {
            Ally.setHealthbar(heal);
            System.out.println(this.getName() + " Curou "+this.getSpecial()+" de vida de " + Ally.getName() + "!");
            GetHealth(Ally);
        }
    }
    public void Ressurection(Attributes Ally) {
        Ally.setHealthbar(Ally.getMaxHealthInitial() / 2);
        System.out.println(Ally.getName() + " Foi ressuscitado! Sua vida atual é de " + Ally.getHealthbar());
        mana = mana - 35;
        System.out.println(this.getName() + " gastou 35 de mana, ficando com " + mana + " Restante");
    }
}
