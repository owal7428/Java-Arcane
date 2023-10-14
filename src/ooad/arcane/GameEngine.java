package ooad.arcane;


import ooad.arcane.Adventurer.*;
import ooad.arcane.Creature.*;
import ooad.arcane.Manager.AdventurerManager;
import ooad.arcane.Manager.CreatureManager;
import ooad.arcane.Manager.FloorManager;
import ooad.arcane.Utility.Logger;
import ooad.arcane.Utility.Observer;
import ooad.arcane.Utility.Tracker;
import ooad.arcane.Utility.Subject;
import java.util.ArrayList;

public class GameEngine implements Subject {
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

    private final ArrayList<Observer> observers = new ArrayList<>();

    Logger logger = new Logger();
    Tracker tracker = new Tracker();

    public GameEngine(boolean shouldRender) {
        this.shouldRender = shouldRender;
        creatureManager.setAdventurerManager(adventurerManager);

        addObserver(logger);
        addObserver(tracker);

        for (Adventurer adventurer : adventurerManager.getAdventurers()) {
            adventurer.addObserver(logger);
            adventurer.addObserver(tracker);
        }

        for (Creature creature : creatureManager.getLivingCreatures()) {
            creature.addObserver(logger);
            creature.addObserver(tracker);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String event) {
        for (Observer observer : observers)
            observer.Update(event);
    }

    // This method returns 1 if adventurers win, 0 if creatures win
    public int Simulate() {
        // Main loop
        while(numTreasures < 24 && totalValue < 15000  && numAdventurers != 0 && numCreatures != 0) {
            turn++;

            if (shouldRender)
                renderer.Render(turn, floorManager, adventurerManager, creatureManager);

            // Reset values
            numTreasures = 0;
            totalValue = 0;
            numCreatures = 0;
            numAdventurers = 0;

            ArrayList<Adventurer> adventurers = adventurerManager.getAdventurers();

            for (Adventurer adventurer : adventurers) {
                numAdventurers++;
                adventurer.Turn();
            }

            numTreasures += adventurerManager.getTotalTreasures();
            totalValue += adventurerManager.getTotalValue();

            ArrayList<Creature> creatures = creatureManager.getLivingCreatures();

            for (Creature creature : creatures) {
                numCreatures++;
                creature.Turn();
            }

            if (shouldRender)
                logger.writeEvents(turn);
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
