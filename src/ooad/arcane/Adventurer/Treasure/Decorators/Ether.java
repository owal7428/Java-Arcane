package ooad.arcane.Adventurer.Treasure.Decorators;

import ooad.arcane.Adventurer.Treasure.Treasure;
import ooad.arcane.Adventurer.Treasure.TreasureDecorator;

import java.util.ArrayList;
import java.util.List;

public class Ether extends TreasureDecorator {
    public Ether(Treasure treasure) {
        super(treasure);
    }

    @Override
    public List<String> getTreasures() {
        List<String> newList = new ArrayList<>(super.getTreasures());
        newList.add("Ether");
        return newList;
    }

    @Override
    public int getNumTreasures() {
        return super.getNumTreasures() + 1;
    }

    @Override
    public int getValue() {
        int value = 900;
        return super.getValue() + value;
    }
}
