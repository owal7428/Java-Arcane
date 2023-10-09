package ooad.arcane.Adventurer.Treasure.Decorators;

import ooad.arcane.Adventurer.Treasure.Treasure;
import ooad.arcane.Adventurer.Treasure.TreasureDecorator;

public class Elixir extends TreasureDecorator {
    public Elixir(Treasure treasure) {
        super(treasure);
    }

    @Override
    public int getNumTreasures() {
        return super.getNumTreasures() + 1;
    }

    @Override
    public int getValue() {
        int value = 500;
        return super.getValue() + value;
    }
}
