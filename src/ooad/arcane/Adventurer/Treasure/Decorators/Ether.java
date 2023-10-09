package ooad.arcane.Adventurer.Treasure.Decorators;

import ooad.arcane.Adventurer.Treasure.TreasureBag;
import ooad.arcane.Adventurer.Treasure.TreasureDecorator;

public class Ether extends TreasureDecorator {
    int value = 900;

    public Ether(TreasureBag treasureBag) {
        super(treasureBag);
    }

    @Override
    public int getNumTreasures() {
        return super.getNumTreasures() + 1;
    }
}
