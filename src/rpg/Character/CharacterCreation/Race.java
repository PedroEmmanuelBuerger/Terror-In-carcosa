package rpg.Character.CharacterCreation;

public enum Race {
    HUMAN("Humano", 0, 0, 10),
    ORC("Orc", 10, 0, 0), // Bônus de 5 ao ataque
    ELF("Elfo", 0, 10, 0), // Bônus de 3 ao ataque
    DWARF("Anão", 5, 0, 5); // Bônus de 2 ao ataque

    private final String name;
    private final int bonusHealth;
    private final int bonusSpecial;
    private final int bonusAttack;

    Race(String name, int bonusHealth, int bonusSpecial, int bonusAttack) {
        this.name = name;
        this.bonusHealth = bonusHealth;
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
