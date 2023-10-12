package ooad.arcane.Manager;

import ooad.arcane.Event;
import ooad.arcane.GameEngine;
import ooad.arcane.Adventurer.*;
import ooad.arcane.Adventurer.Treasure.Treasure;
import ooad.arcane.Creature.Creature;
import ooad.arcane.Floor.Floor;
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

    /* Method used to compare the attack of the adventurer and the creature it's attacking.
    * Return value indicates damage to be done to the health of the adventurer.
    * Interfaces with the creature manager class. */
    public int compareDamage(Creature creature, int attack, float dodge, int damage) {
        int creatureAttack = Dice.rollD6s() + creature.getAttackBonus();

        int damageTaken = 0;

        // Check which won the dice roll
        if (attack > creatureAttack) {
            creature.isDead = true;

            Event event = new Event(this.getClass() + " defeated creature" );
            GameEngine.getEvents().addEvent(event);
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
        adventurer.FightCreature(creature);
    }

    public void Despawn(Adventurer adventurer, String floor, int[] location) {
        // Remove the adventurer from the list
        //adventurers.remove(adventurer);

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
        ArrayList<Adventurer> temp = new ArrayList<>(adventurers);

        for (Adventurer adventurer : temp) {
            if (adventurer.getHealth() <= 0)
                Despawn(adventurer, adventurer.getFloor(), adventurer.getLocation());
        }
    }

}
