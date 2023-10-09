package ooad.arcane.Adventurer.Treasure;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Creature.Creature;

public class TreasureDecorator implements Treasure {
    private final TreasureBag treasureBag;

    public TreasureDecorator(TreasureBag treasureBag) {
        this.treasureBag = treasureBag;
    }
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
