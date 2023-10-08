package ooad.arcane.Floor;

import java.util.ArrayList;

public class ElementalFloor extends Floor {
    public ElementalFloor(String name, ArrayList<Room> floorMap) {
        super(name, floorMap);
    }

    @Override
    public void Init() {
        // Loops over all 9 of the rooms for each floor and adds them to the ArrayList
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Room room = new Room(i, j);
                addRoom(room);
            }
        }

        // Add the adjacent rooms to each room object
        for (Room room : getFloorMap()) {
            int[] coordinates = room.getCoordinates();
            int x = coordinates[0];
            int y = coordinates[1];

            // Connect this room to left and right neighbors if not on the outside edge
            if (x != 0)
                room.addAdjacentRooms(searchCoordinates(new int[] {x - 1, y}));
            if (x != 2)
                room.addAdjacentRooms(searchCoordinates(new int[] {x + 1, y}));

            // Do the same for top and bottom neighbors
            if (y != 0)
                room.addAdjacentRooms(searchCoordinates(new int[] {x, y - 1}));
            if (y != 2)
                room.addAdjacentRooms(searchCoordinates(new int[] {x, y + 1}));
        }
    }
}
