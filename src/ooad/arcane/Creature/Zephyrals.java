package ooad.arcane.Creature;

import ooad.arcane.Event;
import ooad.arcane.GameEngine;
import ooad.arcane.Floor.Room;
import ooad.arcane.Manager.CreatureManager;
import ooad.arcane.Utility.Dice;

import java.util.ArrayList;

public class Zephyrals extends Creature {

    //Constructor (spawn method)
    public Zephyrals(CreatureManager manager) {
        super(manager, "AirFloor", "Zephyral");
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
        Event event = new Event(this.getName() + " has entered the room " + "[" + newLocation[0] + ", " + newLocation[1] + "]");
        GameEngine.getEvents().addEvent(event);
    }

}
