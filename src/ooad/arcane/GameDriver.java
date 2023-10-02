package ooad.arcane;


import ooad.arcane.Adventurer.*;
import ooad.arcane.Creature.*;
import ooad.arcane.Manager.AdventurerManager;
import ooad.arcane.Manager.CreatureManager;
import ooad.arcane.Manager.FloorManager;

import java.util.ArrayList;


public class GameDriver {
    static int turn = 0;
    static int numTreasures = -1;
    static int numCreatures = -1;
    static int numAdventurers = -1;

    static int numAdventurerWins = 0;
    static int numCreatureWins = 0;

    static boolean shouldRender;

    /* This whole main function is an example of abstraction because all of the complexity behind the internals
    * of the game have been abstracted away so only a few function calls are necessary to run the game. */

    public static void main(String[] args) {

        // shouldRender = true;
        shouldRender = false;

        // Run 30 times
        for (int i = 0; i < 30; i++)
        {
            // Instantiate manager objects
            FloorManager floorManager = new FloorManager();

            floorManager.Init();

            CreatureManager creatureManager = new CreatureManager(floorManager);
            AdventurerManager adventurerManager = new AdventurerManager(creatureManager, floorManager);
            creatureManager.setAdventurerManager(adventurerManager);

            BoardRenderer renderer = new BoardRenderer();

            /* This is an example of identity because each instance of the creature classes is
            * distinct from one another. */
            for (int j = 0; j < 4; j++)
            {
                creatureManager.addCreatures(new Aquarids(creatureManager));
                creatureManager.addCreatures(new Fireborns(creatureManager));
                creatureManager.addCreatures(new Terravores(creatureManager));
                creatureManager.addCreatures(new Zephyrals(creatureManager));
            }

            for (Creature creature : creatureManager.getLivingCreatures())
                creature.Spawn();

            adventurerManager.addAdventurers(new EmberKnight(adventurerManager));
            adventurerManager.addAdventurers(new MistWalker(adventurerManager));
            adventurerManager.addAdventurers(new TerraVoyager(adventurerManager));
            adventurerManager.addAdventurers(new ZephyrRogue(adventurerManager));

            // Main loop
            while(numTreasures != 50 && numAdventurers != 0 && numCreatures != 0) {
                turn++;

                // Reset values
                numTreasures = 0;
                numCreatures = 0;
                numAdventurers = 0;

                ArrayList<Adventurer> adventurers = adventurerManager.getAdventurers();

                for (Adventurer adventurer : adventurers) {
                    numAdventurers++;
                    adventurer.Turn();
                    numTreasures += adventurer.getNumTreasures();
                }

                ArrayList<Creature> creatures = creatureManager.getLivingCreatures();

                for (Creature creature : creatures) {
                    numCreatures++;
                    creature.Turn();
                }
                if (shouldRender)
                    renderer.Render(turn, floorManager);
            }

            System.out.println("...");

            if (numCreatures == 0) {
                System.out.println("Adventurers won ... All creatures killed");
                numAdventurerWins++;
            }
            else if (numTreasures == 50) {
                System.out.println("Adventurers won ... All treasures found");
                numAdventurerWins++;
            }
            else {
                System.out.println("Creatures won ... All adventurers killed");
                numCreatureWins++;
            }

            // Reset values
            numTreasures = -1;
            numAdventurers = -1;
            numCreatures = -1;

            if (shouldRender)
                break;
        }

        if (!shouldRender) {
            System.out.println("The adventurers won " + numAdventurerWins + " times");
            System.out.println("The creatures won " + numCreatureWins + " times");
        }

        System.out.println("Game Finished.");
    }

}
