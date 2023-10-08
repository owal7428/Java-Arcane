package ooad.arcane.Creature;

import ooad.arcane.Floor.*;
import ooad.arcane.Manager.CreatureManager;
import ooad.arcane.Utility.Dice;

import java.util.ArrayList;

public class Aquarids extends Creature {

    //Constructor (spawn method)
    public Aquarids(CreatureManager manager) {
        super(manager, "WaterFloor");
    }

    //aquarids move to adjacent room
    @Override
    public void Move(ArrayList<Room> adjacentRooms) {
        // Go to adjacent room if adventurer is there
        for (Room room : adjacentRooms) {
            if (!room.getAdventurers().isEmpty()) {
                int[] newLocation = room.getCoordinates();
                manager.updateRoomAndFloorLists(this, floor, location, newLocation);
                this.location = newLocation;
                return;
            }
        }

        // Don't move if an adventurer is on the floor
        if (manager.checkFloor(floor))
            return;

        int nextRoom = Dice.rollCustom(adjacentRooms.size());

        int[] newLocation = adjacentRooms.get(nextRoom).getCoordinates();

        // Update the creature list for the current room
        manager.updateRoomAndFloorLists(this, floor, location, newLocation);

        this.location = newLocation;
    }

}
