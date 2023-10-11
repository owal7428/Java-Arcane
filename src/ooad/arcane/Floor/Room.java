package ooad.arcane.Floor;

import ooad.arcane.Adventurer.Treasure.Treasure;
import ooad.arcane.Creature.Creature;
import ooad.arcane.Adventurer.Adventurer;

import java.util.ArrayList;

/* This class is an example of cohesion because it sticks to a very narrow, cohesive role. It's also an example
* of encapsulation because it's internal data structures are hidden and only accessed via methods. */

public class Room {
    private final int[] coordinates;
    private final ArrayList<Room> adjacentRooms = new ArrayList<>();
    private final ArrayList<Creature> creatures = new ArrayList<>();
    private final ArrayList<Adventurer> adventurers = new ArrayList<>();
    private final ArrayList<Treasure> treasures = new ArrayList<>();

    public Room(int x, int y) {
        this.coordinates = new int[] {x, y};
    }

    // Methods here are fairly self-explanatory
    public int[] getCoordinates() {
        return coordinates;
    }

    protected ArrayList<Room> getAdjacentRooms() {
        return adjacentRooms;
    }

    protected void addAdjacentRooms(Room room) {
        this.adjacentRooms.add(room);
    }

    protected ArrayList<Adventurer> getAdventurers() {
        return adventurers;
    }

    protected void addAdventurers(Adventurer newAdventurer) {
        adventurers.add(newAdventurer);
    }

    protected void removeAdventurers(Adventurer adventurer) {
        adventurers.remove(adventurer);
    }

    protected ArrayList<Creature> getCreatures() {
        return creatures;
    }

    protected void addCreatures(Creature newCreature) {
        creatures.add(newCreature);
    }

    protected void removeCreatures(Creature creature) {
        creatures.remove(creature);
    }

    protected ArrayList<Treasure> getTreasures() {
        return treasures;
    }

    protected void addTreasures(Treasure newTreasure) {
        treasures.add(newTreasure);
    }

    protected void removeTreasures(Treasure treasure) {
        treasures.remove(treasure);
    }
}
