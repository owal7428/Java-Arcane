package ooad.arcane.Adventurer.Treasure.Decorators;

import ooad.arcane.Adventurer.Treasure.TreasureBag;
import ooad.arcane.Adventurer.Treasure.TreasureDecorator;

public class Portal extends TreasureDecorator {
    int value = 1200;

    public Portal(TreasureBag treasureBag) {
        super(treasureBag);
    }

    @Override
    public int getNumTreasures() {
        return super.getNumTreasures() + 1;
    }
}
