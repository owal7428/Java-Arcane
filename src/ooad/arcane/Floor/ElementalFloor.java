package ooad.arcane.Floor;

import java.util.ArrayList;
import java.util.Arrays;

public class ElementalFloor {
    private final String name;
    private final ArrayList<Room> floorMap;

    private int numAdventurers = 0;

    public ElementalFloor(String name, ArrayList<Room> floorMap) {
        this.name = name;
        this.floorMap = floorMap;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Room> getFloorMap() {
        return floorMap;
    }

    public boolean hasAdventurer() {
        return (numAdventurers > 0);
    }

    public void increaseNumAdventurers() {
        this.numAdventurers++;
    }

    public void decreaseNumAdventurers() {
        this.numAdventurers--;
    }

    public void addRoom(Room room) {
        this.floorMap.add(room);
    }

    /* Helper function that returns the room that corresponds with given coordinates,
     * else throws runtime exception. Will be printed and rethrown. */
    public Room searchCoordinates(int x, int y) throws RuntimeException {
        for (Room room : floorMap) {
            if (Arrays.equals(room.getCoordinates(), new int[]{x, y})) {
                return room;
            }
        }

        throw new RuntimeException("Room couldn't be found: (" + x + "," + y + ").");
    }

    // The following is not needed yet for this project; will use for later project.

    /* All of these methods follow a similar structure in that they will call the
    * helper function to find the room corresponding to the given x,y coordinates.
    * This is done in a try-catch block so any exceptions thrown can be rethrown further up.
    * Then either alter the room's lists or returns the lists to be used. */
    /*
    public ArrayList<Creature> getCreaturesInRoom(int x, int y) {
        Room room;
        try {
            room = searchCoordinates(x, y);
        }
        catch(RuntimeException e) {
            System.out.println("Runtime exception thrown: " + e);
            throw e;
        }

        return room.getCreatures();
    }

    public void addCreatureToRoom(int x, int y, Creature creature) {
        Room room;
        try {
            room = searchCoordinates(x, y);
        }
        catch(RuntimeException e) {
            System.out.println("Runtime exception thrown: " + e);
            throw e;
        }

        room.addCreatures(creature);
    }

    public ArrayList<Adventurer> getAdventurersInRoom(int x, int y) {
        Room room;
        try {
            room = searchCoordinates(x, y);
        }
        catch(RuntimeException e) {
            System.out.println("Runtime exception thrown: " + e);
            throw e;
        }

        return room.getAdventurers();
    }

    public void addAdventurersToRoom(int x, int y, Adventurer adventurer) {
        Room room;
        try {
            room = searchCoordinates(x, y);
        }
        catch(RuntimeException e) {
            System.out.println("Runtime exception thrown: " + e);
            throw e;
        }

        room.addAdventurers(adventurer);
    }*/
}
