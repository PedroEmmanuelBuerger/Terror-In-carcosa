package Utils;

public class ManaAdm {
    SlowConsole slowConsole = new SlowConsole();

    public boolean costMana(int mana, int discount, String Name) {
        int value = mana - discount;
        if (value < 0) {
            slowConsole.imprimirDevagar(Name + " não tem mana suficiente para conjurar o feitiço.");
            slowConsole.imprimirDevagar("Você possui " + mana + " de mana restante...");
            return true;
        } else return false;
    }
}
