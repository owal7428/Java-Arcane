package ooad.arcane.Adventurer.Treasure.Decorators;

import ooad.arcane.Adventurer.Treasure.TreasureBag;
import ooad.arcane.Adventurer.Treasure.TreasureDecorator;

public class Sword extends TreasureDecorator {
    int value = 1100;

    public Sword(TreasureBag treasureBag) {
        super(treasureBag);
    }

    @Override
    public int getNumTreasures() {
        return super.getNumTreasures() + 1;
    }
}
