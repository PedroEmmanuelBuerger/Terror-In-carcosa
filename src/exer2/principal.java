package exer2;

import exer2.rpg.Classes.Healer;
import exer2.rpg.Classes.Mage;
import exer2.rpg.Classes.Rogue;
import exer2.rpg.Classes.Warrior;
import exer2.rpg.Monsters.Boss;

public class principal {
    public static void main(String[] args) {
        Warrior Baratheon = new Warrior("Baratheon",130, 15, 15, "GRAAAAAAAAAAA");
        Warrior ned = new Warrior("ned",90, 20, 35, "HMP");
        Mage tyrion = new Mage("Tyrion", 60, 70, 12, 55, "Knoweldge is power!");
        Rogue sombraios = new Rogue("sombraios", 40, 25, 16, "Sneaking in the shadows...");
        Healer tetriz = new Healer("tetriz", 70, 55, 12,30, "Heroes Never Die!");
        Boss ghazkull = new Boss("Ghazkull", 1500, 35, 50, "HAHAHAHAHAHA");
        Baratheon.attack(ghazkull);
        ned.setDefese(true);
        tyrion.attackWithSpecial(ghazkull);
        sombraios.attack(ghazkull);
        tetriz.attackWithSpecial(ghazkull);
        ghazkull.attackWithSpecial(sombraios);
        ghazkull.attack(ned);
        tetriz.heal(ned);
        tetriz.Ressurection(sombraios);
        ned.attack(ghazkull);
        ghazkull.attack(sombraios);
    }
}
