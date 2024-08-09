package rpg.Campaign.Events;

import rpg.Character.Classes.Attributes;

public interface NonCombatEvent {
    void executeEvent(Attributes personagem);
}
