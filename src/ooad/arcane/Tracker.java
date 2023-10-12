package ooad.arcane;
import ooad.arcane.Adventurer.*;
import ooad.arcane.Creature.*;
import ooad.arcane.Floor.Floor;
import ooad.arcane.Floor.Room;
import ooad.arcane.Manager.AdventurerManager;
import ooad.arcane.Manager.CreatureManager;
import ooad.arcane.Manager.FloorManager;

import java.util.ArrayList;

// Tracker.java
public class Tracker {
    private int turn;
    private int totalTreasureValue = 0;
    private ArrayList<Adventurer> adventurersList = new ArrayList<>();
    private ArrayList<Creature> creaturesList = new ArrayList<>();



    public Tracker(int turn) {
        this.turn = turn;
    }

    public void increment() {
        this.turn++;
    }

    public void display(int totalvalue, int numAdventurers, ArrayList<Adventurer> adventurers, FloorManager floormanager) {
        System.out.println("Tracker: Turn " + this.turn);
        System.out.println("Total Treasure Value: " + totalvalue);
        System.out.println("Total Active Adventurers: " + numAdventurers);
        System.out.println("Adventurers\t\t\tRoom]\t\t\tHealth\t\t\tTreasure\t\t\tTreasure Value");
        /*for(Adventurer a: adventurers) {
            System.out.println(a.getName() + "\t\t\t"+"room" +"\t\t\t" +a.getHealth()+"\t\t\t"+"treasure"+"\t\t\t"+"treasurevalue");
        }*/
        System.out.println("Elemental Resonance");
        for(Floor floor : floormanager.getFloors()) {
           for (Room room : floor.getFloorMap()) {
            if (room.getAdventurers().size() > 0) {
                for (Adventurer currentAdventurer : room.getAdventurers()) {
                    System.out.println(currentAdventurer.getName() + "\t\t\t" + floor.getName() + " " + room.getCoordinates()[0] + "-" + room.getCoordinates()[1] + "\t\t\t" + currentAdventurer.getHealth() + "\t\t\t" + currentAdventurer.getTreasures() + "\t\t\t" + currentAdventurer.getTreasureValue());
                }
            }
           }
        }
        System.out.println("Elemental Discord");
    }

    public void printSummary() {
        System.out.println("Tracker: Turn " + turn);
        System.out.println("Total Treasure Value: " + totalTreasureValue);
        System.out.println("Total Active Adventurers: " + adventurersList.size());
        System.out.println("Adventurers     Room     Health     Treasure     Treasure Value");

        for (Adventurer adv : adventurersList) {
            // Assuming you have methods to get room, health, treasure, and treasure value
            System.out.println(adv.getName() + "     " + adv.getRoom() + "     " + adv.getHealth() + "     " + adv.getTreasures() + "     " + adv.getTreasureValue());
        }

        System.out.println("Total Active Creatures: " + creaturesList.size());
        System.out.println("Creatures     Room");
        for (Creature creature : creaturesList) {
            // Assuming you have a method to get room
            System.out.println(creature.getName() + "     " + creature.getRoom());
        }
    }
}







