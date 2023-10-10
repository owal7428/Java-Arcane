package ooad.arcane;


import ooad.arcane.Adventurer.*;
import ooad.arcane.Creature.*;
import ooad.arcane.Manager.AdventurerManager;
import ooad.arcane.Manager.CreatureManager;
import ooad.arcane.Manager.FloorManager;
import ooad.arcane.Tracker;

import java.util.ArrayList;

public class GameEngine {
    private int turn = 0;
    private int numTreasures = -1;
    private int numCreatures = -1;
    private int numAdventurers = -1;
    private final boolean shouldRender;
    private Tracker tracker;

    FloorManager floorManager = new FloorManager();
    CreatureManager creatureManager = new CreatureManager(floorManager);
    AdventurerManager adventurerManager = new AdventurerManager(creatureManager, floorManager);
    GameBoard renderer = new GameBoard();

    public GameEngine(boolean shouldRender) {
        this.shouldRender = shouldRender;

        creatureManager.setAdventurerManager(adventurerManager);

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
    }

    // This method returns 1 if adventurers win, 0 if creatures win
    public int Simulate() {
        // Main loop
        tracker = new Tracker(); 
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

            
            tracker.printSummary(); 
        }

        System.out.println("...");

        if (numCreatures == 0) {
            System.out.println("Adventurers won ... All creatures killed");
            return 1;
        }
        else if (numTreasures == 50) {
            System.out.println("Adventurers won ... All treasures found");
            return 1;
        }
        else {
            System.out.println("Creatures won ... All adventurers killed");
            return 0;
        }
    }

}
