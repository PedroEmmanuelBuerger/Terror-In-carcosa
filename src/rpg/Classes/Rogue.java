package rpg.Classes;
import java.util.Random;

public class Rogue  extends  Attributes{

    public Rogue(String name, int healthbar, int attack, int special, String quote) {
        super(name, healthbar, attack, special, quote);
        this.setClasses("Rogue");
    }
    Double dodgeSkills = 25.0;
    Random random = new Random();

    @Override
    public void takeDamage(int damage) {
        double randomNumber = random.nextDouble() * 100.0;
        int currentHealth = this.getHealthbar();
        if (randomNumber <= dodgeSkills) {
            System.out.println(getName() + " Desviou!");
        } else {
            this.setHealthbar(currentHealth - damage);
            System.out.println(this.getName() + " recebeu " + damage + " de dano!");
            GetHealth(this);
            if (this.getHealthbar() <= 0) {
                System.out.println(this.getName() + " foi derrotado!");
            }
        }
    }
}
