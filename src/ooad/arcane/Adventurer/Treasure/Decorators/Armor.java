package ooad.arcane.Adventurer.Treasure.Decorators;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Adventurer.Treasure.Treasure;
import ooad.arcane.Adventurer.Treasure.TreasureDecorator;
import ooad.arcane.Creature.Creature;

import java.util.ArrayList;
import java.util.List;

public class Armor extends TreasureDecorator {
    public Armor(Treasure treasure) {
        super(treasure);
    }

    @Override
    public List<String> getTreasures() {
        List<String> newList = new ArrayList<>(super.getTreasures());
        newList.add("Armor");
        return newList;
    }

    @Override
    public int getNumTreasures() {
        return super.getNumTreasures() + 1;
    }

    @Override
    public int getValue() {
        int value = 800;
        return super.getValue() + value;
    }

    @Override
    public void applyBonus(Adventurer adventurer, Creature creature) {

    }
}
