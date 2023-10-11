package ooad.arcane;


import ooad.arcane.Adventurer.*;
import ooad.arcane.Creature.*;
import ooad.arcane.Manager.AdventurerManager;
import ooad.arcane.Manager.CreatureManager;
import ooad.arcane.Manager.FloorManager;

import java.util.ArrayList;

public class GameEngine {
    private int turn = 0;
    private int numTreasures = 0;
    private int totalValue = 0;
    private int numCreatures = -1;
    private int numAdventurers = -1;
    private final boolean shouldRender;

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

        adventurerManager.addAdventurers(new EmberKnight(adventurerManager));
        adventurerManager.addAdventurers(new MistWalker(adventurerManager));
        adventurerManager.addAdventurers(new TerraVoyager(adventurerManager));
        adventurerManager.addAdventurers(new ZephyrRogue(adventurerManager));
    }

    // This method returns 1 if adventurers win, 0 if creatures win
    public int Simulate() {
        // Main loop
        while(numTreasures < 24 && totalValue < 15000  && numAdventurers != 0 && numCreatures != 0) {
            turn++;

            if (shouldRender)
                renderer.Render(turn, floorManager);

            // Reset values
            numTreasures = 0;
            totalValue = 0;
            numCreatures = 0;
            numAdventurers = 0;

            ArrayList<Adventurer> adventurers = adventurerManager.getAdventurers();

            for (Adventurer adventurer : adventurers) {
                if (adventurer.getHealth() > 0) {
                    numAdventurers++;
                    adventurer.Turn();
                }

                numTreasures += adventurer.getNumTreasures();
                totalValue += adventurer.getTreasureValue();

            }

            ArrayList<Creature> creatures = creatureManager.getLivingCreatures();

            for (Creature creature : creatures) {
                numCreatures++;
                creature.Turn();
            }
        }

        System.out.println("...");

        if (numCreatures == 0) {
            System.out.println("Adventurers won ... All creatures killed");
            return 1;
        }
        else if (numTreasures == 24) {
            System.out.println("Adventurers won ... All treasures found");
            return 1;
        }
        else if (totalValue >= 15000) {
            System.out.println("Adventurers won ... Treasures worth 15000 found");
            return 1;
        }
        else {
            System.out.println("Creatures won ... All adventurers killed");
            return 0;
        }
    }

}
