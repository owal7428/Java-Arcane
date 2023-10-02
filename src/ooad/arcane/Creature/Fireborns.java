package ooad.arcane.Creature;

import ooad.arcane.Floor.Room;
import ooad.arcane.Manager.CreatureManager;

import java.util.ArrayList;
import java.util.Random;

public class Fireborns extends Creature {


    //Constructor (spawn method)
    public Fireborns(CreatureManager manager) {
        super(manager, "FireFloor");
    }

    @Override
    public void Spawn() {
        // Randomly choose x and y
        Random random = new Random();
        int x = random.nextInt(0,3);
        int y = random.nextInt(0,3);

        // Make sure it doesn't spawn in the middle
        if (x == 1 && y == 1)
            x = 2;

        this.location = new int[]{x,y};
    }

    // fireborns move clockwise
    @Override
    public void Move(ArrayList<Room> adjacentRooms) {
        // Don't move if an adventurer is on the floor
        if (manager.checkFloor(floor))
            return;

        int[] newLocation = new int[]{0,0};
        newLocation[0] = location[0];
        newLocation[1] = location[1];

        if (location[0] == 0 && location[1] == 0)
            newLocation[0] = 1;

        else if (location[0] == 0 && location[1] == 1)
            newLocation[1] = 0;

        else if (location[0] == 0 && location[1] == 2)
            newLocation[1] = 1;

        else if (location[0] == 1 && location[1] == 0)
            newLocation[0] = 2;

        else if (location[0] == 1 && location[1] == 2)
            newLocation[0] = 0;

        else if (location[0] == 2 && location[1] == 0)
            newLocation[1] = 1;

        else if (location[0] == 2 && location[1] == 1)
            newLocation[1] = 2;

        else
            newLocation[0] = 1;

        manager.updateRoomAndFloorLists(this, floor, location, newLocation);

        this.location = newLocation;
    }

}
