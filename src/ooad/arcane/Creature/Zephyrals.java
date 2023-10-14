package ooad.arcane.Creature;

import ooad.arcane.Floor.Room;
import ooad.arcane.Manager.CreatureManager;
import ooad.arcane.Utility.Dice;

import java.util.ArrayList;
import java.util.Arrays;

public class Zephyrals extends Creature {

    //Constructor (spawn method)
    public Zephyrals(CreatureManager manager) {
        super(manager, "AirFloor");
    }

    //zephyrals move randomly
    @Override
    public void Move(ArrayList<Room> adjacentRooms) {
        // Don't move if an adventurer is on the floor
        if (manager.checkFloor(floor))
            return;

        int nextRoom = Dice.rollCustom(adjacentRooms.size());

        int[] newLocation = adjacentRooms.get(nextRoom).getCoordinates();

        // Update the creature list for the current room
        manager.updateRoomAndFloorLists(this, floor, location, newLocation);

        this.location = newLocation;

        notifyObservers(getType(this) + " moved to " + floor + ": "
                + Arrays.toString(location) + ".");
    }

}
