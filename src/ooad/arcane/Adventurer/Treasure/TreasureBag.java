package ooad.arcane.Adventurer.Treasure;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Creature.Creature;

public class TreasureBag implements Treasure {
    @Override
    public int getNumTreasures() {
        return 0;
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public void applyBonus(Adventurer adventurer, Creature creature) {

    }
}
