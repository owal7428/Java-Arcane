package ooad.arcane.Manager;

import ooad.arcane.Adventurer.Treasure.Decorators.*;
import ooad.arcane.Adventurer.Treasure.Treasure;
import ooad.arcane.Adventurer.Treasure.TreasureBag;
import ooad.arcane.Floor.ElementalFloor;
import ooad.arcane.Floor.Floor;
import ooad.arcane.Floor.StartingFloor;
import ooad.arcane.Utility.Dice;

import java.util.ArrayList;
import java.util.Objects;

public class FloorManager {
    private final ArrayList<Floor> floors = new ArrayList<>(5);

    /* This constructor method initializes all the rooms and floors;
     * These are all set by project specifications so no need for modularity here. */
    public FloorManager() {
        // Create floors
        Floor startFloor = new StartingFloor("StartFloor", new ArrayList<>());
        Floor fireFloor = new ElementalFloor("FireFloor", new ArrayList<>());
        Floor waterFloor = new ElementalFloor("WaterFloor", new ArrayList<>());
        Floor airFloor = new ElementalFloor("AirFloor", new ArrayList<>());
        Floor earthFloor = new ElementalFloor("EarthFloor", new ArrayList<>());

        // Populate the list of floors
        floors.add(startFloor);
        floors.add(fireFloor);
        floors.add(waterFloor);
        floors.add(airFloor);
        floors.add(earthFloor);

        // Initialize every floor
        for (Floor floor : floors) {
            floor.Init();
        }

        // Add treasures to random locations
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                Treasure newTreasure = switch(i) {
                    case 0 -> new Armor(new TreasureBag());
                    case 1 -> new Elixir(new TreasureBag());
                    case 2 -> new Ether(new TreasureBag());
                    case 3 -> new Portal(new TreasureBag());
                    case 4 -> new Potion(new TreasureBag());
                    case 5 -> new Sword(new TreasureBag());
                    default -> throw new IllegalStateException("Unexpected value: " + i);
                };

                int floor_num = Dice.rollD4();
                int x = Dice.rollD2_Include0();
                int y = Dice.rollD2_Include0();

                String newFloor = switch (floor_num) {
                    case 1 -> "FireFloor";
                    case 2 -> "WaterFloor";
                    case 3 -> "AirFloor";
                    case 4 -> "EarthFloor";
                    default -> throw new IllegalStateException("Unexpected value: " + floor_num);
                };

                getFloor(newFloor).addTreasuresToRoom(new int[]{x,y}, newTreasure);
            }
        }

        // Add gems to random locations
        for (int i = 0; i < 15; i++) {
            Treasure newTreasure = new Gem(new TreasureBag());

            int floor_num = Dice.rollD4();
            int x = Dice.rollD2_Include0();
            int y = Dice.rollD2_Include0();

            String newFloor = switch (floor_num) {
                case 1 -> "FireFloor";
                case 2 -> "WaterFloor";
                case 3 -> "AirFloor";
                case 4 -> "EarthFloor";
                default -> throw new IllegalStateException("Unexpected value: " + floor_num);
            };

            getFloor(newFloor).addTreasuresToRoom(new int[]{x,y}, newTreasure);
        }
    }

    public Floor getFloor(String name) {
        for (Floor floor : floors) {
            if (Objects.equals(floor.getName(), name))
                return floor;
        }

        throw new RuntimeException("Floor " + name + " couldn't be found.");
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }
}

