package ooad.arcane.Adventurer.Behaviors;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Creature.Creature;

/* Interface used in strategy pattern. The classes that inherit from this
* are the concrete behaviors. */
public interface CombatBehavior {
    int Fight(Adventurer adventurer, Creature creature, int attack, float dodge, int damage);
}
