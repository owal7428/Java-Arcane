package ooad.arcane.Manager;

import ooad.arcane.Adventurer.*;
import ooad.arcane.Adventurer.Treasure.Treasure;
import ooad.arcane.Creature.Creature;
import ooad.arcane.Floor.Floor;
import ooad.arcane.Floor.Room;

import java.util.ArrayList;
import java.util.Objects;

public class AdventurerManager {
    // ArrayLists that hold the current active adventurers
    private final ArrayList<Adventurer> livingAdventurers = new ArrayList<>();
    private final ArrayList<Adventurer> allAdventurers;

    // Managers necessary for communication between game objects
    private final CreatureManager creatureManager;
    private final FloorManager floorManager;

    public AdventurerManager(CreatureManager creatureManager, FloorManager floorManager) {
        this.creatureManager = creatureManager;
        this.floorManager = floorManager;

        // Add adventurers
        livingAdventurers.add(new EmberKnight(this));
        livingAdventurers.add(new MistWalker(this));
        livingAdventurers.add(new TerraVoyager(this));
        livingAdventurers.add(new ZephyrRogue(this));

        allAdventurers = new ArrayList<>(livingAdventurers);
    }

    public ArrayList<Adventurer> getLivingAdventurers() {
        return new ArrayList<>(livingAdventurers);
    }

    public ArrayList<Adventurer> getAllAdventurers() {
        return new ArrayList<>(allAdventurers);
    }

    public int getTotalTreasures() {
        int temp = 0;
        for (Adventurer adventurer : allAdventurers)
            temp += adventurer.getNumTreasures();

        return temp;
    }

    public int getTotalValue() {
        int temp = 0;
        for (Adventurer adventurer : allAdventurers)
            temp += adventurer.getTreasureValue();

        return temp;
    }

    public ArrayList<Creature> getCreaturesInCurrentRoom(String floor, int[] location) {
        Floor currentFloor = floorManager.getFloor(floor);
        return currentFloor.getCreaturesInRoom(location);
    }

    public ArrayList<Treasure> getTreasuresInCurrentRoom(String floor, int[] location) {
        Floor currentFloor = floorManager.getFloor(floor);
        return currentFloor.getTreasuresInRoom(location);
    }

    public void removeTreasureFromRoom(Treasure treasure, String floor, int[] location) {
        Floor currentFloor = floorManager.getFloor(floor);
        currentFloor.removeTreasuresFromRoom(location, treasure);
    }

    public ArrayList<Room> getCurrentAdjacentRooms(String floor, int[] location) {
        Floor currentFloor = floorManager.getFloor(floor);
        return currentFloor.getAdjacentRooms(location);
    }

    public void updateRoomAndFloorLists(Adventurer adventurer,String oldFloorName, int[] oldLocation, String newFloorName, int[] newLocation) {
        Floor oldFloor = floorManager.getFloor(oldFloorName);
        Floor newFloor = floorManager.getFloor(newFloorName);

        oldFloor.removeAdventurersFromRoom(oldLocation, adventurer);
        newFloor.addAdventurersToRoom(newLocation, adventurer);

        // Update the counter for number of adventurers in a floor
        if (!Objects.equals(oldFloor, newFloor)) {
            oldFloor.decreaseNumAdventurers();
            newFloor.increaseNumAdventurers();
        }
    }

    public void spawnInitRoom(Adventurer adventurer, String floor, int[] location) {
        Floor currentFloor = floorManager.getFloor(floor);
        currentFloor.addAdventurersToRoom(location, adventurer);
    }

    public void respondToFight(Adventurer adventurer, Creature creature) {
        adventurer.FightCreature(creature);
    }

    public void Despawn(Adventurer adventurer, String floor, int[] location) {
        // Remove the adventurer from the list
        livingAdventurers.remove(adventurer);

        // Decrement the counter for number of adventurers on floor
        Floor currentFloor = floorManager.getFloor(floor);
        currentFloor.decreaseNumAdventurers();

        // Remove from current room
        currentFloor.removeAdventurersFromRoom(location, adventurer);

    }

    public void signalReap() {
        creatureManager.reap();
    }

    public void reap() {
        ArrayList<Adventurer> temp = new ArrayList<>(livingAdventurers);

        for (Adventurer adventurer : temp) {
            if (adventurer.getHealth() <= 0)
                Despawn(adventurer, adventurer.getFloor(), adventurer.getLocation());
        }
    }

}
