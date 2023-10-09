package ooad.arcane.Adventurer.Treasure;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Creature.Creature;

public class TreasureDecorator implements Treasure {
    private final Treasure treasure;

    public TreasureDecorator(Treasure treasure) {
        this.treasure = treasure;
    }
    @Override
    public int getNumTreasures() {
        return treasure.getNumTreasures();
    }

    @Override
    public int getValue() {
        return treasure.getValue();
    }

    @Override
    public void applyBonus(Adventurer adventurer, Creature creature) {

    }
}
