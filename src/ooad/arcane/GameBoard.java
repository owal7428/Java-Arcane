package ooad.arcane;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Adventurer.EmberKnight;
import ooad.arcane.Adventurer.MistWalker;
import ooad.arcane.Adventurer.TerraVoyager;
import ooad.arcane.Creature.Aquarids;
import ooad.arcane.Creature.Creature;
import ooad.arcane.Creature.Fireborns;
import ooad.arcane.Creature.Terravores;
import ooad.arcane.Floor.Floor;
import ooad.arcane.Floor.Room;
import ooad.arcane.Manager.AdventurerManager;
import ooad.arcane.Manager.CreatureManager;
import ooad.arcane.Manager.FloorManager;

import java.util.ArrayList;
import java.util.Objects;

public class GameBoard {
    public void Render(int turn, FloorManager floorManager, AdventurerManager adventurerManager, CreatureManager creatureManager) {
        System.out.println("----------Turn-" + turn  + "----------");

        ArrayList<Floor> floors = floorManager.getFloors();

        // Print
        for (Floor floor : floors) {
            System.out.println();
            System.out.println(floor.getName() + ":");

            if (Objects.equals(floor.getName(), "StartFloor")) {
                Room room = floor.getFloorMap().get(0);

                System.out.println("+------------------------------+");
                System.out.print("|             ");

                for (Adventurer adventurer : floor.getAdventurersInRoom(room.getCoordinates())) {
                    switch (adventurer) {
                        case EmberKnight ignored -> System.out.print("EK,");
                        case MistWalker ignored -> System.out.print("MW,");
                        case TerraVoyager ignored -> System.out.print("TV,");
                        case null, default -> System.out.print("ZR,");
                    }
                }

                System.out.print(":-");
                System.out.print("            ");
                System.out.println();
                System.out.println("+------------------------------+");
            }
            else {
                System.out.println("+------------------------------+------------------------------+------------------------------+");
                //                 "|             AA:AA            |             AA:AA            |            AA:AA              |"
                int i = 0;
                for (Room room : floor.getFloorMap()) {
                    if (i == 3) {
                        System.out.println();
                        System.out.println("+------------------------------+------------------------------+------------------------------+");
                        i = 0;
                    }

                    System.out.print("|             ");

                    for (Adventurer adventurer : floor.getAdventurersInRoom(room.getCoordinates())) {
                        switch (adventurer) {
                            case EmberKnight ignored -> System.out.print("EK,");
                            case MistWalker ignored -> System.out.print("MW,");
                            case TerraVoyager ignored -> System.out.print("TV,");
                            case null, default -> System.out.print("ZR,");
                        }
                    }

                    System.out.print(":");

                    for (Creature creature : floor.getCreaturesInRoom(room.getCoordinates())) {
                        switch (creature) {
                            case Aquarids ignored -> System.out.print("A,");
                            case Fireborns ignored -> System.out.print("F,");
                            case Terravores ignored -> System.out.print("T,");
                            case null, default -> System.out.print("Z,");
                        }
                    }

                    System.out.print("            ");

                    i++;
                }
            }
        }

        System.out.println();
        System.out.println();

        for (Adventurer adventurer : adventurerManager.getAllAdventurers()) {
            String name = adventurer.getType(adventurer);
            System.out.println(name + " - " + adventurer.getNumTreasures() + " Treasure(s) - "
                    + adventurer.getHealth() + " Health Remaining");
        }

        int numAquarids = 0;
        int numFireborns = 0;
        int numTerravores = 0;
        int numZephyrals = 0;

        for (Creature creature : creatureManager.getLivingCreatures()) {
            String name = creature.getType(creature);

            switch (name) {
                case "Aquarid" -> numAquarids++;
                case "Fireborn" -> numFireborns++;
                case "Terravore" -> numTerravores++;
                case null, default -> numZephyrals++;
            }
        }

        System.out.println("Aquarids - " + numAquarids + " remaining");
        System.out.println("Fireborns - " + numFireborns + " remaining");
        System.out.println("Terravores - " + numTerravores + " remaining");
        System.out.println("Zephyrals - " + numZephyrals + " remaining");

        System.out.println();
        System.out.println();

    }
}
