package ooad.arcane.Adventurer.Treasure.Decorators;

import ooad.arcane.Adventurer.Treasure.TreasureBag;
import ooad.arcane.Adventurer.Treasure.TreasureDecorator;

public class Elixir extends TreasureDecorator {
    int value = 500;

    public Elixir(TreasureBag treasureBag) {
        super(treasureBag);
    }

    @Override
    public int getNumTreasures() {
        return super.getNumTreasures() + 1;
    }
}
