package rpg.Character.Classes;

import rpg.Character.CharacterCreation.Race;
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
    private final String quote;
    private String classes;
    private int maxHealthInitial;
    private boolean alive;
    private int level;
    private int exp;
    private int levelDungeon = 1; // Inicializa o nível da dungeon
    SlowConsole slowConsole = new SlowConsole();
    private Weapon weapon = new Axe(0);
    private Race race;
    private final List<Item> abyssalInventory = new ArrayList<>();
    private int gold = 0;

    public Attributes(String name, int healthbar, int attack, int special, String quote) {
        this.name = name;
        this.healthbar = healthbar;
        this.attack = attack + weapon.attack();
        this.special = special;
        this.quote = quote;
        this.maxHealthInitial = healthbar;
        this.alive = true;
        this.level = 1; // Inicializa o nível como 1
        this.exp = 0; // Inicializa a experiência como 0
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

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
        slowConsole.imprimirDevagar(this.getName() + " ataca " + enemy.getName() + " com fúria cósmica, causando " + damage + " de dano!");
        enemy.takeDamage(damage);

        // Verifica se o inimigo foi derrotado
        if (!enemy.isAlive()) {
            slowConsole.imprimirDevagar("A saúde total de " + enemy.getName() + " é reduzida a 0");
            slowConsole.imprimirDevagar(enemy.getName() + " foi aniquilado!");
            slowConsole.imprimirDevagar("Você absorveu " + enemy.getExp() + " de EXP do abismo!");
            slowConsole.imprimirDevagar(enemy.getName() + " deixou " + enemy.getGold() + " de Ouro Profano!");
            gainGold(enemy.getGold());
            gainExp(enemy.getExp()); // Ganha experiência baseada no nível do inimigo
        }
    }

    public void attackWithSpecial(Attributes enemy) {
        CriticChance criticChance = new CriticChance(this.getSpecial());
        int damage = criticChance.chanceCritic(); // Corrigir chamada ao método chanceCritic
        slowConsole.imprimirDevagar(this.getName() + " desferiu um ataque especial de terror cósmico contra " + enemy.getName() + ", causando " + damage + " de dano!");
        enemy.takeDamage(damage);
        if (!enemy.isAlive()) {
            slowConsole.imprimirDevagar("A saúde total de " + enemy.getName() + " é reduzida a 0");
            slowConsole.imprimirDevagar(enemy.getName() + " foi aniquilado!");
            slowConsole.imprimirDevagar("Você absorveu " + enemy.getExp() + " de EXP do abismo!");
            slowConsole.imprimirDevagar(enemy.getName() + " deixou " + enemy.getGold() + " de Ouro Profano!");
            gainGold(enemy.getGold());
            gainExp(enemy.getExp()); // Ganha experiência baseada no nível do inimigo
        }
    }

    public void gainGold(int gold) {
        this.setGold(this.getGold() + gold);
    }

    public void takeDamage(int damage) {
        // Reduz a saúde do personagem pelo valor do dano recebido
        int currentHealth = this.getHealthbar();
        this.setHealthbar(currentHealth - damage);
        slowConsole.imprimirDevagar(this.getName() + " sofreu " + damage + " de dano do abismo!");
        getHealth(this);
    }

    public void getHealth(Attributes creature) {
        if (creature.getHealthbar() > 0) {
            slowConsole.imprimirDevagar("A saúde total de " + creature.getName() + " é " + creature.getHealthbar());
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
            slowConsole.imprimirDevagar(getName() + " ascendeu a um novo nível! Nível atual é: " + getLevel());
            setAttack(getAttack() + 5);
            slowConsole.imprimirDevagar(getName() + " absorveu 5 de poder de ataque das trevas!");
            setHealthbar(getHealthbar() + 5);
            setMaxHealthInitial(getMaxHealthInitial() + 5);
            slowConsole.imprimirDevagar(getName() + " teve 5 de vida extra concedida pela escuridão!");
            setSpecial(getSpecial() + 5);
            slowConsole.imprimirDevagar(getName() + " recebeu 5 de poder especial das sombras!");
        }
    }

    public int getLevel() {
        return level;
    }

    public void getTechnicalInfo() {
        slowConsole.imprimirDevagar("Nome: " + getName());
        slowConsole.imprimirDevagar("Classe: " + getClasses());
        slowConsole.imprimirDevagar("Saúde: " + getHealthbar());
        slowConsole.imprimirDevagar("Ataque: " + getAttack());
        slowConsole.imprimirDevagar("Poder Especial: " + getSpecial());
        slowConsole.imprimirDevagar("Frase: " + getQuote());
        slowConsole.imprimirDevagar("Arma: " + getWeapon().getName());
        slowConsole.imprimirDevagar("Raça: " + getRace().getName());
        slowConsole.imprimirDevagar("Ouro Profano: " + getGold());

        // Adiciona a exibição dos itens no inventário abissal
        if (abyssalInventory.isEmpty()) {
            slowConsole.imprimirDevagar("O inventário abissal está vazio.");
        } else {
            slowConsole.imprimirDevagar("Itens no inventário abissal:");
            for (Item item : abyssalInventory) {
                slowConsole.imprimirDevagar(item.getName());
            }
        }
    }

    public int getMaxHealthInitial() {
        return maxHealthInitial;
    }

    public List<Item> getAbyssalInventory() {
        return abyssalInventory;
    }

    public void addItemToAbyssalInventory(Item item) {
        if (this.getAbyssalInventory().size() >= 5) {
            slowConsole.imprimirDevagar("O limite de 5 itens no inventário abissal foi alcançado...");
        } else {
            abyssalInventory.add(item);
            slowConsole.imprimirDevagar("Adicionou " + item.getName() + " ao inventário abissal.");
        }
    }

    public void removeItemFromAbyssalInventory(Item item) {
        abyssalInventory.remove(item);
    }

    public void useItem(Item item) {
        if (abyssalInventory.contains(item)) {
            item.UseItem(this);
            slowConsole.imprimirDevagar("Utilizou " + item.getName() + " dos profundos recessos.");
            removeItemFromAbyssalInventory(item); // Remove o item após o uso
        } else {
            slowConsole.imprimirDevagar(item.getName() + " não está presente no inventário abissal.");
        }
    }
}
