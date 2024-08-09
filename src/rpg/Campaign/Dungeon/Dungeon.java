package rpg.Campaign.Dungeon;
import rpg.Character.Classes.Attributes;

import java.util.Scanner;

public interface Dungeon {
    void startCombat(Scanner scanner, Attributes personagem);
}
