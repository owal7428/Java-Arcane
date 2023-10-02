package ooad.arcane.Manager;

import ooad.arcane.Floor.ElementalFloor;
import ooad.arcane.Floor.Room;

import java.util.ArrayList;
import java.util.Objects;

public class FloorManager {
    // Set up for all the elemental floors
    private final ElementalFloor StartingFloor = new ElementalFloor("StartFloor", new ArrayList<>());
    private final ElementalFloor FireFloor = new ElementalFloor("FireFloor", new ArrayList<>());
    private final ElementalFloor WaterFloor = new ElementalFloor("WaterFloor", new ArrayList<>());
    private final ElementalFloor AirFloor = new ElementalFloor("AirFloor", new ArrayList<>());
    private final ElementalFloor EarthFloor = new ElementalFloor("EarthFloor", new ArrayList<>());

    private final ArrayList<ElementalFloor> floors = new ArrayList<>(5);

    /* This constructor method initializes all the rooms and floors;
     * These are all set by project specifications so no need for modularity here. */
    public FloorManager() {
        // Populate the list of floors
        floors.add(StartingFloor);
        floors.add(FireFloor);
        floors.add(WaterFloor);
        floors.add(AirFloor);
        floors.add(EarthFloor);

        // Initialize all the rooms for the starting floor
        Room Start = new Room(0,0);
        Room AirStart = new Room(0,1);
        Room EarthStart = new Room(1,0);
        Room FireStart = new Room(0, -1);
        Room WaterStart = new Room(-1, 0);

        // Add rooms
        StartingFloor.addRoom(Start);
        StartingFloor.addRoom(AirStart);
        StartingFloor.addRoom(EarthStart);
        StartingFloor.addRoom(FireStart);
        StartingFloor.addRoom(WaterStart);

        // Make floor starting rooms connected to central starting room
        Start.addAdjacentRooms(AirStart);
        Start.addAdjacentRooms(EarthStart);
        Start.addAdjacentRooms(FireStart);
        Start.addAdjacentRooms(WaterStart);

        AirStart.addAdjacentRooms(Start);
        EarthStart.addAdjacentRooms(Start);
        FireStart.addAdjacentRooms(Start);
        WaterStart.addAdjacentRooms(Start);
    }

    public void Init() {
        // Init rest of floors
        for (ElementalFloor floor : floors) {
            if (floor != StartingFloor)
                InitFloor(floor);
        }
    }

    // Init function used by constructor
    private void InitFloor(ElementalFloor floor) {
        // Loops over all 9 of the rooms for each floor and adds them to the ArrayList
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Room room = new Room(i, j);
                floor.addRoom(room);
            }
        }

        ArrayList<Room> temp = floor.getFloorMap();

        // Add the adjacent rooms to each room object
        for (Room room : temp) {
            int[] coordinates = room.getCoordinates();
            int x = coordinates[0];
            int y = coordinates[1];

            // Connect this room to left and right neighbors if not on the outside edge
            if (x != 0)
                room.addAdjacentRooms(floor.searchCoordinates(x - 1, y));
            if (x != 2)
                room.addAdjacentRooms(floor.searchCoordinates(x + 1, y));

            // Do the same for top and bottom neighbors
            if (y != 0)
                room.addAdjacentRooms(floor.searchCoordinates(x, y - 1));
            if (y != 2)
                room.addAdjacentRooms(floor.searchCoordinates(x, y + 1));

        }
    }

    public ElementalFloor getFloor(String name) {
        for (ElementalFloor floor : floors) {
            if (Objects.equals(floor.getName(), name))
                return floor;
        }

        throw new RuntimeException("Floor couldn't be found.");
    }

    public ArrayList<ElementalFloor> getFloors() {
        return floors;
    }
}

