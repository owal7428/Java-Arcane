package ooad.arcane;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Adventurer.EmberKnight;
import ooad.arcane.Adventurer.MistWalker;
import ooad.arcane.Adventurer.TerraVoyager;
import ooad.arcane.Creature.Aquarids;
import ooad.arcane.Creature.Creature;
import ooad.arcane.Creature.Fireborns;
import ooad.arcane.Creature.Terravores;
import ooad.arcane.Floor.ElementalFloor;
import ooad.arcane.Floor.Room;
import ooad.arcane.Manager.FloorManager;

import java.util.ArrayList;
import java.util.Objects;

public class GameBoard {
    public void Render(int turn, FloorManager floorManager) {
        System.out.println("----------Turn-" + turn  + "----------");

        ArrayList<ElementalFloor> floors = floorManager.getFloors();

        // Print
        for (ElementalFloor floor : floors) {
            System.out.println();
            System.out.println(floor.getName() + ":");

            if (Objects.equals(floor.getName(), "StartFloor")) {
                Room room = floor.getFloorMap().get(0);

                System.out.println("+------------------------------+");
                System.out.print("|             ");

                for (Adventurer adventurer : room.getAdventurers()) {
                    if (adventurer instanceof EmberKnight)
                        System.out.print("EK,");
                    else if (adventurer instanceof MistWalker)
                        System.out.print("MW,");
                    else if (adventurer instanceof TerraVoyager)
                        System.out.print("TV,");
                    else
                        System.out.print("ZR,");
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

                    for (Adventurer adventurer : room.getAdventurers()) {
                        if (adventurer instanceof EmberKnight)
                            System.out.print("EK,");
                        else if (adventurer instanceof MistWalker)
                            System.out.print("MW,");
                        else if (adventurer instanceof TerraVoyager)
                            System.out.print("TV,");
                        else
                            System.out.print("ZR,");
                    }

                    System.out.print(":");

                    for (Creature creature : room.getCreatures()) {
                        if (creature instanceof Aquarids)
                            System.out.print("A,");
                        else if (creature instanceof Fireborns)
                            System.out.print("F,");
                        else if (creature instanceof Terravores)
                            System.out.print("T,");
                        else
                            System.out.print("Z,");
                    }

                    System.out.print("            ");

                    i++;
                }
            }
        }

        System.out.println();
        System.out.println();

    }
}
