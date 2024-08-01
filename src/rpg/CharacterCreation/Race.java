package rpg.CharacterCreation;

public enum Race {
    HUMAN("Humano", 0, 0, 0, 10),
    ORC("Orc", 10, 0, 0, 0), // Bônus de 5 ao ataque
    ELF("Elfo", 0, 5, 10, 0), // Bônus de 3 ao ataque
    DWARF("Anão", 5, 0, 0, 5); // Bônus de 2 ao ataque

    private final String name;
    private final int bonusHealth;
    private final int bonusMana;
    private final int bonusSpecial;
    private final int bonusAttack; // Novo atributo para bônus de ataque

    Race(String name, int bonusHealth, int bonusMana, int bonusSpecial, int bonusAttack) {
        this.name = name;
        this.bonusHealth = bonusHealth;
        this.bonusMana = bonusMana;
        this.bonusSpecial = bonusSpecial;
        this.bonusAttack = bonusAttack;
    }

    public String getName() {
        return name;
    }

    public int getBonusHealth() {
        return bonusHealth;
    }

    public int getBonusSpecial() {
        return bonusSpecial;
    }

    public int getBonusAttack() {
        return bonusAttack;
    }
}
