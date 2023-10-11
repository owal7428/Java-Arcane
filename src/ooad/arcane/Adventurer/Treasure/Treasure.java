package ooad.arcane.Adventurer.Treasure;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Creature.Creature;

import java.util.List;

public interface Treasure {
    public List<String> getTreasures();
    public int getNumTreasures();
    public int getValue();
}
