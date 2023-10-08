package ooad.arcane.Adventurer;

import ooad.arcane.Creature.Creature;
import ooad.arcane.Floor.Room;
import ooad.arcane.Manager.AdventurerManager;
import ooad.arcane.Utility.Dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public abstract class Adventurer {
    private int health;
    protected int damageTaken;
    protected float dodge;
    protected int diceBonusCombat;
    protected int diceBonusTreasure;
    private int numTreasures;
    private int[] location;
    private String floor;

    /* The manager is here to send signals to other managers so that
    * the adventurers can communicate with the other game elements */
    AdventurerManager manager;

    protected Adventurer(int health, float dodge, AdventurerManager manager) {
        this.health = health;
        this.damageTaken = 2;
        this.dodge = dodge;
        this.diceBonusCombat = 0;
        this.diceBonusTreasure = 0;
        this.numTreasures = 0;
        this.location = new int[] {0,0};
        this.floor = "StartFloor";
        this.manager = manager;
    }

    public void Turn() {
        /* Get creatures in room; If there aren't look for treasure.
         * Else, fight the creatures. */
        ArrayList<Creature> creatures = manager.getCreaturesInCurrentRoom(floor, location);

        if (creatures.isEmpty()) {
            // Move if there are no creatures
            ArrayList<Room> adjacentRooms = manager.getCurrentAdjacentRooms(floor, location);
            Move(adjacentRooms);
            ApplyDiscordOrResonance();

            creatures = manager.getCreaturesInCurrentRoom(floor, location);

            if (creatures.isEmpty()) {
                // Check if in elemental floor and in center
                if (!Objects.equals(floor, "StartFloor") && Arrays.equals(location, new int[]{1, 1})) {
                    manager.updateRoomAndFloorLists(this,floor,location,"StartFloor",new int[]{0,0});
                    this.floor = "StartFloor";
                    this.location = new int[]{0,0};
                }
                /* Check if we're on the starting floor. If so, check which elemental tile we're on
                 * and move to that floor at position {1,1} which is the center. */
                else if (Objects.equals(floor, "StartFloor")) {
                    String newFloor = "";
                    if (Arrays.equals(location, new int[]{0, 1}))
                        newFloor = "AirFloor";
                    else if (Arrays.equals(location, new int[]{1, 0}))
                        newFloor = "EarthFloor";
                    else if (Arrays.equals(location, new int[]{0, -1}))
                        newFloor = "FireFloor";
                    else if (Arrays.equals(location, new int[]{-1, 0}))
                        newFloor = "WaterFloor";

                    manager.updateRoomAndFloorLists(this,floor,location,newFloor,new int[]{1,1});
                    this.floor = newFloor;
                    this.location = new int[]{1, 1};
                }
                else FindTreasure();
            }
            else {
                Fight();
            }
        }
        else {
            Fight();
        }
    }

    private void Move(ArrayList<Room> adjacentRooms) {
        int nextRoom = Dice.rollCustom(adjacentRooms.size());

        int[] newLocation = adjacentRooms.get(nextRoom).getCoordinates();

        // Update the adventurer list for the current room
        manager.updateRoomAndFloorLists(this, floor, location, floor, newLocation);

        this.location = newLocation;
    }

    private void FindTreasure() {
        int roll = Dice.rollD6s();

        if (roll + diceBonusTreasure >= 11)
            this.numTreasures++;
    }

    protected void ApplyDiscordOrResonance() {
        // Each adventurer should implement their own version of this.
    }

    private void Fight() {
        ArrayList<Creature> creatures = manager.getCreaturesInCurrentRoom(floor, location);

        for (Creature enemy : creatures) {
            RespondToFight(enemy);

            // Stop fighting if dead
            if (health <= 0)
                break;
        }

        manager.signalReap();
    }

    public void RespondToFight(Creature enemy) {
        int attack = Dice.rollD6s() + diceBonusCombat;
        health -= manager.compareDamage(enemy, attack, dodge, damageTaken);
    }

    public String getFloor() {
        return floor;
    }

    public int[] getLocation() {
        return location;
    }

    public int getNumTreasures() {
        return numTreasures;
    }

    public int getHealth() {
        return health;
    }
}
