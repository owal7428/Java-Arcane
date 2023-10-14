package ooad.test;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Adventurer.Treasure.Decorators.*;
import ooad.arcane.Adventurer.Treasure.Treasure;
import ooad.arcane.Adventurer.Treasure.TreasureBag;
import ooad.arcane.Manager.AdventurerManager;
import ooad.arcane.Manager.CreatureManager;
import ooad.arcane.Manager.FloorManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DecoratorTest {
    @Test
    public void testNumTreasures() {
        Treasure inventory = new Armor(new Elixir(new Ether(new Gem(new TreasureBag()))));
        int numTreasures = inventory.getNumTreasures();
        assert (numTreasures == 3);
    }

    @Test
    public void testGetValue() {
        Treasure inventory = new Ether(new Elixir(new Gem(new Portal(new TreasureBag()))));
        int totalValue = inventory.getValue();
        assert (totalValue == 3600);
    }

    @Test
    public void testGetTreasures() {
        Treasure inventory = new Ether(new Sword(new Armor(new Gem(new TreasureBag()))));
        List<String> list = new ArrayList<>();
        list.add("Gem");
        list.add("Armor");
        list.add("Sword");
        list.add("Ether");

        assert (Objects.equals(list, inventory.getTreasures()));
    }

    @Test
    public void testGetTreasuresFromAdventurer() {
        FloorManager floorManager = new FloorManager();
        CreatureManager creatureManager = new CreatureManager(floorManager);
        AdventurerManager adventurerManager = new AdventurerManager(creatureManager, floorManager);

        creatureManager.setAdventurerManager(adventurerManager);

        Adventurer emberKnight = adventurerManager.getAllAdventurers().get(0);

        for (int i = 0; i < 20; i++)
            emberKnight.Turn();

        String treasures = emberKnight.getTreasures();

        System.out.println(treasures);

        assert (!Objects.equals(treasures, ""));
    }
}
