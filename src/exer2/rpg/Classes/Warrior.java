package exer2.rpg.Classes;

public class Warrior extends Attributes {
    private boolean defense = false;
    public Warrior(String name,int healthbar, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        setClasses("Warrior");
    }

    public boolean isDefese() {
        return defense;
    }

    public void setDefese(boolean defese) {
        this.defense = defese;
    }

    public void defend() {
        setDefese(true);
        System.out.println(getName() + " entrou em posição de defesa!");
    }

    @Override
    public void takeDamage(int damage) {
        // Reduz a saúde do guerreiro atual pelo valor do dano recebido
        int currentHealth = this.getHealthbar();
        if(this.isDefese() == true) {
            this.setHealthbar(currentHealth - (damage / 2));
            System.out.println(this.getName() + " defendeu, recebendo " + damage / 2 + " de dano!");
            this.setDefese(false);
            GetHealth(this);
        }
        else {
            this.setHealthbar(currentHealth - damage);
            System.out.println(this.getName() + " recebeu " + damage + " de dano!");
            GetHealth(this);
        }
    }
}
