package ooad.arcane.Adventurer.Treasure;

import java.util.ArrayList;
import java.util.List;

// Concrete component of the decorator pattern
public class TreasureBag implements Treasure {
    @Override
    public List<String> getTreasures() {
        return new ArrayList<>();
    }

    @Override
    public int getNumTreasures() {
        return 0;
    }

    @Override
    public int getValue() {
        return 0;
    }
}
