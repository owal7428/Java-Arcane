package ooad.arcane.Manager;

import ooad.arcane.Adventurer.*;
import ooad.arcane.Creature.Creature;
import ooad.arcane.Floor.ElementalFloor;
import ooad.arcane.Floor.Room;
import ooad.arcane.Utility.Dice;

import java.util.ArrayList;
import java.util.Objects;

public class AdventurerManager {
    // ArrayLists that hold the current active adventurers
    private final ArrayList<Adventurer> adventurers = new ArrayList<>();

    // Managers necessary for communication between game objects
    private final CreatureManager creatureManager;
    private final FloorManager floorManager;

    public AdventurerManager(CreatureManager creatureManager, FloorManager floorManager) {
        this.creatureManager = creatureManager;
        this.floorManager = floorManager;
    }

    public void addAdventurers(Adventurer adventurer) {
        adventurers.add(adventurer);
    }

    public ArrayList<Adventurer> getAdventurers() {
        return adventurers;
    }

    // Private helper function
    private Room getCurrentRoom(String floor, int[] location) {
        int x = location[0];
        int y = location[1];
        ElementalFloor currentFloor = floorManager.getFloor(floor);
        return currentFloor.searchCoordinates(x, y);
    }

    public ArrayList<Creature> getCreaturesInCurrentRoom(String floor, int[] location) {
        Room currentRoom = getCurrentRoom(floor, location);
        return currentRoom.getCreatures();
    }

    public ArrayList<Room> getCurrentAdjacentRooms(String floor, int[] location) {
        Room currentRoom = getCurrentRoom(floor, location);
        return currentRoom.getAdjacentRooms();
    }

    public void updateRoomAndFloorLists(Adventurer adventurer,String oldFloor, int[] oldLocation, String newFloor, int[] newLocation) {
        Room oldRoom = getCurrentRoom(oldFloor, oldLocation);
        Room newRoom = getCurrentRoom(newFloor, newLocation);

        oldRoom.removeAdventurers(adventurer);
        newRoom.addAdventurers(adventurer);

        // Update the counter for number of adventurers in a floor
        if (!Objects.equals(oldFloor, newFloor)) {
            floorManager.getFloor(oldFloor).decreaseNumAdventurers();
            floorManager.getFloor(newFloor).increaseNumAdventurers();
        }
    }

    /* Method used to compare the attack of the adventurer and the creature it's attacking.
    * Return value indicates damage to be done to the health of the adventurer.
    * Interfaces with the creature manager class. */
    public int compareDamage(Creature creature, int attack, float dodge, int damage) {
        int creatureAttack = Dice.rollD6s();

        int damageTaken = 0;

        // Check which won the dice roll
        if (attack > creatureAttack) {
            creature.isDead = true;
        }
        else if (attack < creatureAttack) {
            /* Generate number between 1 and 100 inclusive. If number is less
            * than or equal to dodge * 100, dodge was successful. Doing this gets
            * probability matching dodge as a probability. */
            int dodgeRoll = Dice.rollCustom(100);

            if (dodgeRoll > dodge * 100)
                damageTaken += damage;
        }

        return damageTaken;
    }

    public void respondToFight(Adventurer adventurer, Creature creature) {
        adventurer.RespondToFight(creature);
    }

    public void Despawn(Adventurer adventurer, String floor, int[] location) {
        // Remove the adventurer from the list
        adventurers.remove(adventurer);

        // Decrement the counter for number of adventurers on floor
        ElementalFloor currentFloor = floorManager.getFloor(floor);
        currentFloor.decreaseNumAdventurers();

        // Remove from current room
        Room currentRoom = getCurrentRoom(floor, location);
        currentRoom.removeAdventurers(adventurer);

    }

    public void signalReap() {
        creatureManager.reap();
    }

    public void reap() {
        ArrayList<Adventurer> temp = (ArrayList<Adventurer>) adventurers.clone();

        for (Adventurer adventurer : temp) {
            if (adventurer.getHealth() <= 0)
                Despawn(adventurer, adventurer.getFloor(), adventurer.getLocation());
        }

    }

}
