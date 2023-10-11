package ooad.arcane.Adventurer.Treasure;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Creature.Creature;

import java.util.List;

public class TreasureDecorator implements Treasure {
    private final Treasure treasure;

    public TreasureDecorator(Treasure treasure) {
        this.treasure = treasure;
    }

    @Override
    public List<String> getTreasures() {
        return treasure.getTreasures();
    }

    @Override
    public int getNumTreasures() {
        return treasure.getNumTreasures();
    }

    @Override
    public int getValue() {
        return treasure.getValue();
    }
}
