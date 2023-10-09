package ooad.test;

import ooad.arcane.Adventurer.Treasure.Decorators.*;
import ooad.arcane.Adventurer.Treasure.Treasure;
import ooad.arcane.Adventurer.Treasure.TreasureBag;
import org.junit.Test;

public class DecoratorTest {
    @Test
    public void testNumTreasures() {
        Treasure inventory = new Armor(new Elixir(new Ether(new Gem(new TreasureBag()))));
        int numTreasures = inventory.getNumTreasures();
        assert (numTreasures == 4);
    }

    @Test
    public void testGetValue() {
        Treasure inventory = new Ether(new Elixir(new Gem(new Portal(new TreasureBag()))));
        int totalValue = inventory.getValue();
        assert (totalValue == 3600);
    }
}
