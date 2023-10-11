package ooad.arcane.Adventurer.Behaviors;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Creature.Creature;
import ooad.arcane.Manager.AdventurerManager;

public interface CombatBehavior {
    public int Fight(Adventurer adventurer, Creature creature,
                     int attack, float dodge, int damage);
}
