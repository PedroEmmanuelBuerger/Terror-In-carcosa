package exer2.rpg.Classes;

public abstract class Attributes {
    private String name = "";
    private int healthbar = 0;
    private int attack = 0;
    private int special = 0;
    private String quote = "";
    private String classes = "";
    private int maxHealthInitial;

    public Attributes(String name, int healthbar, int attack, int special, String quote) {
        this.name = name;
        this.healthbar = healthbar;
        this.attack = attack;
        this.special = special;
        this.quote = quote;
        this.maxHealthInitial = healthbar;
    }


    public int getHealthbar() {
        return healthbar;
    }

    public void setHealthbar(int healthbar) {
        this.healthbar = healthbar;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public int getSpecial() {
        return special;
    }

    public void setSpecial(int special) {
        this.special = special;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public void attack(Attributes enemy) {
        // Reduz a saúde do inimigo pelo valor do ataque do guerreiro atual
        int damage = this.getAttack();
        System.out.println(this.getName() + " atacou " + enemy.getName() + " causando " + damage + " de dano!");
        enemy.takeDamage(damage);
    }
    public void attackWithSpecial(Attributes enemy) {
        int damage = this.getSpecial();
        System.out.println(this.getName() + " atacou " + enemy.getName() + " Com um ataque Especial... causando " + damage + " de dano!");
        enemy.takeDamage(damage);
    }
    public void takeDamage(int damage) {
        // Reduz a saúde do guerreiro atual pelo valor do dano recebido
        int currentHealth = this.getHealthbar();
            this.setHealthbar(currentHealth - damage);
            System.out.println(this.getName() + " recebeu " + damage + " de dano!");
            GetHealth(this);
        if (this.getHealthbar() <= 0) {
            System.out.println(this.getName() + " foi derrotado!");
        }
    }
    public void GetHealth(Attributes creature) {
        System.out.println("vida total de " + creature.getName() + " é " + creature.getHealthbar());
    }
    public int getMaxHealthInitial() {
        return maxHealthInitial;
    }
}
