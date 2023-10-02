package ooad.arcane.Floor;

import ooad.arcane.Creature.Creature;
import ooad.arcane.Adventurer.Adventurer;

import java.util.ArrayList;

/* This class is an example of cohesion because it sticks to a very narrow, cohesive role. It's also an example
* of encapsulation because it's internal data structures are hidden and only accessed via methods. */

public class Room {
    private final int[] coordinates;
    private final ArrayList<Room> adjacentRooms = new ArrayList<Room>();
    private final ArrayList<Creature> creatures = new ArrayList<Creature>();
    private final ArrayList<Adventurer> adventurers = new ArrayList<Adventurer>();

    public Room(int x, int y) {
        this.coordinates = new int[] {x, y};
    }

    // Methods here are fairly self-explanatory
    public int[] getCoordinates() {
        return coordinates;
    }

    public ArrayList<Room> getAdjacentRooms() {
        return adjacentRooms;
    }

    public void addAdjacentRooms(Room room) {
        this.adjacentRooms.add(room);
    }

    public ArrayList<Adventurer> getAdventurers() {
        return adventurers;
    }

    public void addAdventurers(Adventurer newAdventurer) {
        adventurers.add(newAdventurer);
    }

    public void removeAdventurers(Adventurer adventurer) {
        adventurers.remove(adventurer);
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public void addCreatures(Creature newCreature) {
        creatures.add(newCreature);
    }

    public void removeCreatures(Creature creature) {
        creatures.remove(creature);
    }
}
