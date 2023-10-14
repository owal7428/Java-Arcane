package ooad.arcane.Utility;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Creature.Creature;
import java.util.ArrayList;

// Concrete observer object; part of observer pattern
public class Tracker implements Observer {
    @Override
    public void Update(String event) {
        // This does nothing because Tracker doesn't need update messages
    }

    public void printStatus(int turn, int totalTreasureValue, ArrayList<Adventurer> adventurers, ArrayList<Creature> creatures) {
        System.out.println("Tracker: Turn " + turn);
        System.out.println();
        System.out.println("Total Treasure Value: " + totalTreasureValue);
        System.out.println();
        System.out.println("Total Active Adventurers: " + adventurers.size());

        // Print out formatted strings that are left aligned; makes it look clean
        System.out.printf("%-18s %-20s %-10s %-34s %-10s \n", "Adventurers", "Room", "Health", "Treasure", "Value");

        for (Adventurer adventurer : adventurers) {
            String name = adventurer.getType(adventurer);
            String room = adventurer.getFloor() + ": " + adventurer.getLocation()[0] + "," + adventurer.getLocation()[1];
            int health = adventurer.getHealth();
            String treasures = adventurer.getTreasures();
            int value = adventurer.getTreasureValue();

            // Prints out formatted data to match headers
            System.out.printf("%-18s %-20s %-10d %-34s %-10d \n", name, room, health, treasures, value);
        }

        System.out.println();
        System.out.println("Elemental Resonance:");

        for (Adventurer adventurer : adventurers) {
            if (adventurer.getHasResonance())
                System.out.println(adventurer.getType(adventurer));
        }

        System.out.println();
        System.out.println("Elemental Discord:");

        for (Adventurer adventurer : adventurers) {
            if (adventurer.getHasDiscord())
                System.out.println(adventurer.getType(adventurer));
        }

        System.out.println();
        System.out.println("Total Active Creatures: " + creatures.size());
        System.out.printf("%-18s %-18s \n", "Creatures", "Room");

        for (Creature creature : creatures) {
            String name = creature.getType(creature);
            String room = creature.getFloor() + ": " + creature.getLocation()[0] + "," + creature.getLocation()[1];
            System.out.printf("%-18s %-18s \n", name, room);
        }

        System.out.println();
        System.out.println();
    }
}
