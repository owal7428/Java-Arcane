package ooad.arcane.Manager;

import ooad.arcane.Adventurer.Adventurer;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import ooad.arcane.Creature.Creature;
import ooad.arcane.Floor.ElementalFloor;
import ooad.arcane.Floor.Room;

public class CreatureManager {
    public final ArrayList<Creature> livingCreatures = new ArrayList<>();

    private AdventurerManager adventurerManager;
    private final FloorManager floorManager;

    public CreatureManager(FloorManager floorManager) {
        // Setup managers
        this.floorManager = floorManager;
    }

    public void addCreatures(Creature creature) {
        livingCreatures.add(creature);
    }

    // This is necessary in order for CreatureManager and AdventureManager to be instantiated
    public void setAdventurerManager(AdventurerManager adventurerManager) {
        this.adventurerManager = adventurerManager;
    }

    public ArrayList<Creature> getLivingCreatures() {
        return livingCreatures;
    }

    // Private helper function
    private Room getCurrentRoom(String floor, int[] location) {
        int x = location[0];
        int y = location[1];
        ElementalFloor currentFloor = floorManager.getFloor(floor);
        return currentFloor.searchCoordinates(x, y);
    }

    public ArrayList<Adventurer> getAdventurersInCurrentRoom(String floor, int[] location) {
        Room currentRoom = getCurrentRoom(floor, location);
        return currentRoom.getAdventurers();
    }

    public ArrayList<Room> getCurrentAdjacentRooms(String floor, int[] location) {
        Room currentRoom = getCurrentRoom(floor, location);
        return currentRoom.getAdjacentRooms();
    }

    public boolean checkFloor(String floor) {
        return floorManager.getFloor(floor).hasAdventurer();
    }

    public void updateRoomAndFloorLists(Creature creature, String floor, int[] oldLocation, int[] newLocation) {
        Room oldRoom = getCurrentRoom(floor, oldLocation);
        Room newRoom = getCurrentRoom(floor, newLocation);

        oldRoom.removeCreatures(creature);
        newRoom.addCreatures(creature);
    }

    public void spawnInitRoom(Creature creature, String floor, int[] location) {
        Room newRoom = getCurrentRoom(floor, location);
        newRoom.addCreatures(creature);
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

    public int creatureAttackResponse()
    {
        // Get random evenly distributed number between 1 and 6
        Random random = new Random();
        int dice1 = random.nextInt(1,7);
        int dice2 = random.nextInt(1,7);

        return dice1 + dice2;
    }


    public void signalReap() {
        adventurerManager.reap();
    }
    public void reap() {
        ArrayList<Creature> temp = (ArrayList<Creature>) livingCreatures.clone();

        for (Creature creature : temp) {
            if (creature.isDead)
                killCreature(creature, creature.getFloor(), creature.getLocation());
        }
    }

    public void killCreature(Creature creature, String floor, int[] location) {
        // Remove the creature from the list
        livingCreatures.remove(creature);

        // Remove from current room
        Room currentRoom = getCurrentRoom(floor, location);
        currentRoom.removeCreatures(creature);
    }
}
