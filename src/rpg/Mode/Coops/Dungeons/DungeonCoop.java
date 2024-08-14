package rpg.Mode.Coops.Dungeons;

import rpg.Character.Classes.Attributes;

import java.util.List;
import java.util.Scanner;

public interface DungeonCoop {
    void startCombat(Scanner scanner, List<Attributes> personagens);
}