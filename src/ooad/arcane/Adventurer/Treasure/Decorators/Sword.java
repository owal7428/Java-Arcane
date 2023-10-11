package ooad.arcane.Adventurer.Treasure.Decorators;

import ooad.arcane.Adventurer.Treasure.Treasure;
import ooad.arcane.Adventurer.Treasure.TreasureDecorator;

import java.util.ArrayList;
import java.util.List;

public class Sword extends TreasureDecorator {
    public Sword(Treasure treasure) {
        super(treasure);
    }

    @Override
    public List<String> getTreasures() {
        List<String> newList = new ArrayList<>(super.getTreasures());
        newList.add("Sword");
        return newList;
    }

    @Override
    public int getNumTreasures() {
        return super.getNumTreasures() + 1;
    }

    @Override
    public int getValue() {
        int value = 1100;
        return super.getValue() + value;
    }
}
