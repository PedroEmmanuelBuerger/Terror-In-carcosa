package Utils;

public class ManaAdm {
    public boolean costMana(int mana, int discount, String Name) {
     int value = mana - discount;
     if (value < 0){
         System.out.println(Name + " não tem mana suficiente para conjurar o feitiço.");
         return true;
     }
     else return false;
    }
}
