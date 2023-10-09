package ooad.arcane.Adventurer.Treasure;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Creature.Creature;

public interface Treasure {
    public int getNumTreasures();
    public int getValue();
    public void applyBonus(Adventurer adventurer, Creature creature);
}
