package rpg.Classes;

import rpg.Utils.SlowConsole;

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
    SlowConsole slowConsole = new SlowConsole();
    int levelDungeon = 1;

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

    public int getLevelDungeon() {
        return levelDungeon;
    }

    public void setLevelDungeon(int levelDungeon) {
        this.levelDungeon = levelDungeon;
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
        // Reduz a saúde do inimigo pelo valor do ataque do personagem atual
        int damage = this.getAttack();
        slowConsole.imprimirDevagar(this.getName() + " atacou " + enemy.getName() + " causando " + damage + " de dano!");
        enemy.takeDamage(damage);

        // Verifica se o inimigo foi derrotado
        if (!enemy.isAlive()) {
            slowConsole.imprimirDevagar("Vida total de " + enemy.getName() + " é 0");
            slowConsole.imprimirDevagar(enemy.getName() + " foi derrotado!");
            slowConsole.imprimirDevagar("Você ganhou " + enemy.getExp() + " de EXP!");
            gainExp(enemy.getExp()); // Ganha experiência baseada no nível do inimigo
        }
    }

    public void attackWithSpecial(Attributes enemy) {

        int damage = this.getSpecial();
        slowConsole.imprimirDevagar(this.getName() + " atacou " + enemy.getName() + " com um ataque especial, causando " + damage + " de dano!");
        enemy.takeDamage(damage);
        if (!enemy.isAlive()) {
            slowConsole.imprimirDevagar("Vida total de " + enemy.getName() + " é 0");
            slowConsole.imprimirDevagar(enemy.getName() + " foi derrotado!");
            gainExp(enemy.getExp()); // Ganha experiência baseada no nível do inimigo
        }
    }

    public void takeDamage(int damage) {
        // Reduz a saúde do personagem pelo valor do dano recebido
        int currentHealth = this.getHealthbar();
        this.setHealthbar(currentHealth - damage);
        slowConsole.imprimirDevagar(this.getName() + " recebeu " + damage + " de dano!");
        getHealth(this);
    }

    public void getHealth(Attributes creature) {
        if (creature.getHealthbar() > 0) {
            slowConsole.imprimirDevagar("Vida total de " + creature.getName() + " é " + creature.getHealthbar());
        } else {
            creature.alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMaxHealthInitial(int maxHealthInitial) {
        this.maxHealthInitial = maxHealthInitial;
    }

    public void gainExp(int expGain) {
        exp += expGain; // Adiciona a experiência ganha

        // Verifica se o personagem subiu de nível
        while (exp >= level * 7){
            exp -= level * 7; // Subtrai o máximo de experiência para o próximo nível
            level++; // Aumenta o nível do personagem
            slowConsole.imprimirDevagar(getName() + " upou de nível! Nível atual é: " + getLevel());
            setAttack(getAttack() + 5);
            slowConsole.imprimirDevagar(getName() + " Aumentou 5 de ataque!");
            setHealthbar(getHealthbar() + 5);
            setMaxHealthInitial(getMaxHealthInitial() + 5);
            slowConsole.imprimirDevagar(getName() + " Aumentou 5 de vida!");
            setSpecial(getSpecial() + 5);
            slowConsole.imprimirDevagar(getName() + " Aumentou 5 de Ataque Especial!");
        }
    }

    public int getLevel() {
        return level;
    }

    public void getTechnicalInfo() {
        slowConsole.imprimirDevagar("Nome: " + getName());
        slowConsole.imprimirDevagar("Classe: " + getClasses());
        slowConsole.imprimirDevagar("Vida: " + getHealthbar());
        slowConsole.imprimirDevagar("Ataque: " + getAttack());
        slowConsole.imprimirDevagar("Ataque Especial: " + getSpecial());
        slowConsole.imprimirDevagar("Frase: " + getQuote());
    }

    public int getMaxHealthInitial() {
        return maxHealthInitial;
    }
}
