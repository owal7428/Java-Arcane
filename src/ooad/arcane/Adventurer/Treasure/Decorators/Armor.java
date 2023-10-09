package ooad.arcane.Adventurer.Treasure.Decorators;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Adventurer.Treasure.Treasure;
import ooad.arcane.Adventurer.Treasure.TreasureDecorator;
import ooad.arcane.Creature.Creature;

public class Armor extends TreasureDecorator {
    public Armor(Treasure treasure) {
        super(treasure);
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
