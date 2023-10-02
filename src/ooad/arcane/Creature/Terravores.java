package ooad.arcane.Creature;

import ooad.arcane.Floor.Room;
import ooad.arcane.Manager.CreatureManager;

import java.util.ArrayList;

public class Terravores extends Creature {


    //Constructor (spawn method)
    public Terravores(CreatureManager manager) {
        super(manager, "EarthFloor");
    }

    //terravores dont move
    @Override
    public void Move(ArrayList<Room> adjacentRooms) {
        return;
    }

}
