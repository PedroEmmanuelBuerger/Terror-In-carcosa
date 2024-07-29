package rpg.itens.Weapons;

public class StickOfTruth implements Weapon {
    private int attack; // Atributo para armazenar o valor de ataque
    private String Name = "Cajado da verdade";

    public StickOfTruth(int attack) {
        this.attack = attack; // Inicializa o valor de ataque
    }

    @Override
    public int getAttack() {
        return attack; // Retorna o valor de ataque
    }

    @Override
    public String getName() {
        return Name;
    }
}
