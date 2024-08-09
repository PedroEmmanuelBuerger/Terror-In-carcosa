package rpg.Mode.Coops.Dugeon;

import rpg.Character.Classes.Attributes;

import java.util.List;
import java.util.Scanner;

public interface DungeonCoop {
    void startCombat(Scanner scanner, List<Attributes> personagens);
}