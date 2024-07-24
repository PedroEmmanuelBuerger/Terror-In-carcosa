package rpg.Classes;

public abstract class Attributes {
    private String name;
    private int healthbar;
    private int attack;
    private int special;
    private String quote;
    private String classes;
    private int maxHealthInitial;
    private boolean alive;
    private int level;
    private int exp; // Variável de experiência

    public Attributes(String name, int healthbar, int attack, int special, String quote) {
        this.name = name;
        this.healthbar = healthbar;
        this.attack = attack;
        this.special = special;
        this.quote = quote;
        this.maxHealthInitial = healthbar;
        this.alive = true;
        this.level = 1; // Inicializa o nível como 1
        this.exp = 0; // Inicializa a experiência como 0
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
        // Verifica se o inimigo está vivo antes de atacar
        if (!enemy.isAlive()) {
            System.out.println(enemy.getName() + " já foi derrotado!");
            return;
        }

        // Reduz a saúde do inimigo pelo valor do ataque do personagem atual
        int damage = this.getAttack();
        System.out.println(this.getName() + " atacou " + enemy.getName() + " causando " + damage + " de dano!");
        enemy.takeDamage(damage);

        // Verifica se o inimigo foi derrotado
        if (!enemy.isAlive()) {
            System.out.println("Vida total de " + enemy.getName() + " é 0");
            System.out.println(enemy.getName() + " foi derrotado!");
            gainExp(enemy.getLevel() * 10); // Ganha experiência baseada no nível do inimigo
        }
    }

    public void attackWithSpecial(Attributes enemy) {
        // Verifica se o inimigo está vivo antes de atacar com especial
        if (!enemy.isAlive()) {
            System.out.println(enemy.getName() + " já foi derrotado!");
            return;
        }

        int damage = this.getSpecial();
        System.out.println(this.getName() + " atacou " + enemy.getName() + " com um ataque especial, causando " + damage + " de dano!");
        enemy.takeDamage(damage);
        if (!enemy.isAlive()) {
            System.out.println("Vida total de " + enemy.getName() + " é 0");
            System.out.println(enemy.getName() + " foi derrotado!");
            gainExp(enemy.getLevel() * 10); // Ganha experiência baseada no nível do inimigo
        }
    }

    public void takeDamage(int damage) {
        // Reduz a saúde do personagem pelo valor do dano recebido
        int currentHealth = this.getHealthbar();
        this.setHealthbar(currentHealth - damage);
        System.out.println(this.getName() + " recebeu " + damage + " de dano!");
        getHealth(this);
    }

    public void getHealth(Attributes creature) {
        if(creature.getHealthbar() > 0) {
            System.out.println("Vida total de " + creature.getName() + " é " + creature.getHealthbar());
        }
        else {
            creature.alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void gainExp(int expGain) {
        exp += expGain; // Adiciona a experiência ganha

        // Verifica se o personagem subiu de nível
        while (exp >= level * 10) {
            exp -= level * 10; // Subtrai o máximo de experiência para o próximo nível
            level++; // Aumenta o nível do personagem
            System.out.println(getName() + " upou de nível! Nível atual é: " + getLevel());
        }
    }

    public int getLevel() {
        return level;
    }

    public void getTechnicalInfo() {
        System.out.println("Nome: " + getName());
        System.out.println("Classe: " + getClasses());
        System.out.println("Vida: " + getHealthbar());
        System.out.println("Ataque: " + getAttack());
        System.out.println("Ataque Especial: " + getSpecial());
        System.out.println("Frase: " + getQuote());
    }

    public int getMaxHealthInitial() {
        return maxHealthInitial;
    }
}
