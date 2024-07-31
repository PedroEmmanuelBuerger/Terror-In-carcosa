package rpg.Classes;

import rpg.CharacterCreation.Race;
import rpg.Utils.CriticChance;
import rpg.Utils.SlowConsole;
import rpg.itens.Item;
import rpg.itens.Weapons.Initials.Axe;
import rpg.itens.Weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

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
    private int exp;
    private int levelDungeon = 1; // Inicialize o nível da dungeon
    SlowConsole slowConsole = new SlowConsole();
    private Weapon weapon = new Axe(0);
    private Race race;
    private List<Item> bag = new ArrayList<>(); // Lista de itens na bag

    public Attributes(String name, int healthbar, int attack, int special, String quote) {
        this.name = name;
        this.healthbar = healthbar;
        this.attack = attack + weapon.getAttack();
        this.special = special;
        this.quote = quote;
        this.maxHealthInitial = healthbar;
        this.alive = true;
        this.level = 1; // Inicializa o nível como 1
        this.exp = 0; // Inicializa a experiência como 0
    }

    // Métodos existentes

    public void setRace(Race race) {
        this.race = race;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void applyRaceBonuses() {
        if (race != null) {
            this.healthbar += race.getBonusHealth();
            this.special += race.getBonusSpecial();
            this.attack += race.getBonusAttack(); // Aplica o bônus de ataque
        }
    }

    public Race getRace() {
        return race;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getHealthbar() {
        return healthbar;
    }

    public void setHealthbar(int healthbar) {
        this.healthbar = healthbar;
    }

    public void setLevelDungeon(int levelDungeon) {
        this.levelDungeon = levelDungeon;
    }

    public int getLevelDungeon() {
        return levelDungeon;
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
        CriticChance criticChance = new CriticChance(this.getAttack());
        int damage = criticChance.chanceCritic(); // Corrigir chamada ao método chanceCritic
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
        CriticChance criticChance = new CriticChance(this.getSpecial());
        int damage = criticChance.chanceCritic(); // Corrigir chamada ao método chanceCritic
        slowConsole.imprimirDevagar(this.getName() + " atacou " + enemy.getName() + " com um ataque especial, causando " + damage + " de dano!");
        enemy.takeDamage(damage);
        if (!enemy.isAlive()) {
            slowConsole.imprimirDevagar("Vida total de " + enemy.getName() + " é 0");
            slowConsole.imprimirDevagar(enemy.getName() + " foi derrotado!");
            slowConsole.imprimirDevagar("Você ganhou " + enemy.getExp() + " de EXP!");
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
        while (exp >= level * 10) {
            exp -= level * 10; // Subtrai o máximo de experiência para o próximo nível
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
        slowConsole.imprimirDevagar("Arma: " + getWeapon().getName());
        slowConsole.imprimirDevagar("Raça: " + getRace().getName());

        // Adiciona a exibição dos itens na bag
        if (bag.isEmpty()) {
            slowConsole.imprimirDevagar("A bag está vazia.");
        } else {
            slowConsole.imprimirDevagar("Itens na bag:");
            for (Item item : bag) {
                slowConsole.imprimirDevagar(item.getName());
            }
        }
    }

    public int getMaxHealthInitial() {
        return maxHealthInitial;
    }

    public List<Item> getBag() {
        return bag;
    }

    public void addItemToBag(Item item) {
        bag.add(item);
        slowConsole.imprimirDevagar("Adicionou " + item.getName() + " à bag.");
    }

    public void removeItemFromBag(Item item) {
        bag.remove(item);
    }

    public void useItem(Item item) {
        if (bag.contains(item)) {
            item.UseItem(this);
            slowConsole.imprimirDevagar("Usou " + item.getName() + ".");
            removeItemFromBag(item); // Remove o item após o uso
        } else {
            slowConsole.imprimirDevagar(item.getName() + " não está na bag.");
        }
    }
}
