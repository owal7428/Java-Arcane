package ooad.test;

import ooad.arcane.Adventurer.Treasure.Decorators.Armor;
import ooad.arcane.Adventurer.Treasure.Decorators.Elixir;
import ooad.arcane.Adventurer.Treasure.Decorators.Ether;
import ooad.arcane.Adventurer.Treasure.Decorators.Gem;
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
}
