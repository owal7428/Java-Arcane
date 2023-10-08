package ooad.arcane.Manager;

import ooad.arcane.Floor.ElementalFloor;
import ooad.arcane.Floor.Floor;
import ooad.arcane.Floor.StartingFloor;

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

