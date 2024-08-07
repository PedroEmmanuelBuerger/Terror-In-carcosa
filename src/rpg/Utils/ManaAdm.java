package rpg.Utils;

public class ManaAdm {
    SlowConsole slowConsole = new SlowConsole();

    public boolean costMana(int mana, int discount, String Name) {
        int value = mana - discount;
        if (value < 0) {
            slowConsole.imprimirDevagar(Name + " não possui mana suficiente para conjurar o feitiço. As forças de Carcosa não estão ao seu favor.");
            slowConsole.imprimirDevagar("Você sente a energia se esvaindo... Restante de mana: " + mana + ".");
            return true;
        } else return false;
    }
}
