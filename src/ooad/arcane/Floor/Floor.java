package ooad.arcane.Floor;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Creature.Creature;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Floor {
    private final String name;
    private final ArrayList<Room> floorMap;

    int numAdventurers = 0;

    public Floor(String name, ArrayList<Room> floorMap) {
        this.name = name;
        this.floorMap = floorMap;
    }

    // To be implemented by inheritors
    public void Init() {

    }

    public String getName() {
        return name;
    }

    public ArrayList<Room> getFloorMap() {
        return floorMap;
    }

    protected void addRoom(Room room) {
        this.floorMap.add(room);
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

    /* Helper function that returns the room that corresponds with given coordinates,
     * else throws runtime exception. Will be printed and rethrown. */
    protected Room searchCoordinates(int[] location) throws RuntimeException {
        for (Room room : floorMap) {
            if (Arrays.equals(room.getCoordinates(), location)) {
                return room;
            }
        }

        throw new RuntimeException("Room couldn't be found: (" + location[0] + "," + location[1] + ").");
    }

    /* All of these methods follow a similar structure in that they will call the
     * helper function to find the room corresponding to the given x,y coordinates.
     * This is done in a try-catch block so any exceptions thrown can be rethrown further up.
     * Then either alter the room's lists or returns the lists to be used. */
    public ArrayList<Creature> getCreaturesInRoom(int[] location) {
        Room room;
        try {
            room = searchCoordinates(location);
        }
        catch(RuntimeException e) {
            System.out.println("Runtime exception thrown: " + e);
            throw e;
        }

        return room.getCreatures();
    }

    public void addCreatureToRoom(int[] location, Creature creature) {
        Room room;
        try {
            room = searchCoordinates(location);
        }
        catch(RuntimeException e) {
            System.out.println("Runtime exception thrown: " + e);
            throw e;
        }

        room.addCreatures(creature);
    }

    public void removeCreatureFromRoom(int[] location, Creature creature) {
        Room room;
        try {
            room = searchCoordinates(location);
        }
        catch(RuntimeException e) {
            System.out.println("Runtime exception thrown: " + e);
            throw e;
        }

        room.removeCreatures(creature);
    }

    public ArrayList<Adventurer> getAdventurersInRoom(int[] location) {
        Room room;
        try {
            room = searchCoordinates(location);
        }
        catch(RuntimeException e) {
            System.out.println("Runtime exception thrown: " + e);
            throw e;
        }

        return room.getAdventurers();
    }

    public void addAdventurersToRoom(int[] location, Adventurer adventurer) {
        Room room;
        try {
            room = searchCoordinates(location);
        }
        catch(RuntimeException e) {
            System.out.println("Runtime exception thrown: " + e);
            throw e;
        }

        room.addAdventurers(adventurer);
    }

    public void removeAdventurersFromRoom(int[] location, Adventurer adventurer) {
        Room room;
        try {
            room = searchCoordinates(location);
        }
        catch(RuntimeException e) {
            System.out.println("Runtime exception thrown: " + e);
            throw e;
        }

        room.removeAdventurers(adventurer);
    }

    public ArrayList<Room> getAdjacentRooms(int[] location) {
        return searchCoordinates(location).getAdjacentRooms();
    }
}
