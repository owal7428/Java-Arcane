package ooad.arcane.Manager;

import ooad.arcane.Adventurer.Adventurer;

import java.util.ArrayList;
import java.util.Objects;

import ooad.arcane.Creature.Creature;
import ooad.arcane.Floor.Floor;
import ooad.arcane.Floor.Room;

public class CreatureManager {
    public final ArrayList<Creature> livingCreatures = new ArrayList<>();

    private AdventurerManager adventurerManager;
    private final FloorManager floorManager;

    public CreatureManager(FloorManager floorManager) {
        // Setup managers
        this.floorManager = floorManager;
    }

    // This is necessary in order for CreatureManager and AdventureManager to be instantiated
    public void setAdventurerManager(AdventurerManager adventurerManager) {
        this.adventurerManager = adventurerManager;
    }

    public void addCreatures(Creature creature) {
        livingCreatures.add(creature);
    }

    public ArrayList<Creature> getLivingCreatures() {
        return livingCreatures;
    }

    public ArrayList<Room> getCurrentAdjacentRooms(String floor, int[] location) {
        Floor currentFloor = floorManager.getFloor(floor);
        return currentFloor.getAdjacentRooms(location);
    }

    public ArrayList<Adventurer> getAdventurersInRoom(String floor, int[] location) {
        Floor currentFloor = floorManager.getFloor(floor);
        return currentFloor.getAdventurersInRoom(location);
    }

    public boolean checkFloor(String floor) {
        return floorManager.getFloor(floor).hasAdventurer();
    }

    public void updateRoomAndFloorLists(Creature creature, String floor, int[] oldLocation, int[] newLocation) {
        Floor currentFloor = floorManager.getFloor(floor);

        currentFloor.removeCreatureFromRoom(oldLocation, creature);
        currentFloor.addCreatureToRoom(newLocation, creature);
    }

    public void spawnInitRoom(Creature creature, String floor, int[] location) {
        Floor currentFloor = floorManager.getFloor(floor);
        currentFloor.addCreatureToRoom(location, creature);
    }

    public void callAttack(Adventurer adventurer, Creature creature) {
        adventurerManager.respondToFight(adventurer, creature);
    }

    public boolean checkAlive(Creature creature) {
        boolean found = false;

        for (Creature temp : livingCreatures) {
            if (Objects.equals(temp, creature)) {
                found = true;
                break;
            }
        }

        return found;
    }
    public void killCreature(Creature creature, String floor, int[] location) {
        // Remove the creature from the list
        livingCreatures.remove(creature);

        // Remove from current room
        Floor currentFloor = floorManager.getFloor(floor);
        currentFloor.removeCreatureFromRoom(location, creature);
    }

    public void signalReap() {
        adventurerManager.reap();
    }

    public void reap() {
        ArrayList<Creature> temp = new ArrayList<>(livingCreatures);

        for (Creature creature : temp) {
            if (creature.isDead)
                killCreature(creature, creature.getFloor(), creature.getLocation());
        }
    }
}
