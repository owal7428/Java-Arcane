package ooad.arcane.Adventurer.Treasure;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Creature.Creature;

public interface Treasure {
    public int getNumTreasures();
    public void applyBonus(Adventurer adventurer, Creature creature);
}
