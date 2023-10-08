package ooad.arcane.Floor;

import java.util.ArrayList;

public class StartingFloor extends Floor {

    public StartingFloor(String name, ArrayList<Room> floorMap) {
        super(name, floorMap);
    }

    @Override
    public void Init() {
        // Initialize all the rooms for the starting floor
        Room Start = new Room(0,0);
        Room AirStart = new Room(0,1);
        Room EarthStart = new Room(1,0);
        Room FireStart = new Room(0, -1);
        Room WaterStart = new Room(-1, 0);

        // Add rooms
        addRoom(Start);
        addRoom(AirStart);
        addRoom(EarthStart);
        addRoom(FireStart);
        addRoom(WaterStart);

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
}
