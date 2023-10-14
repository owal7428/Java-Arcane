package ooad.arcane.Utility;

import ooad.arcane.Adventurer.*;
import ooad.arcane.Creature.Aquarids;
import ooad.arcane.Creature.Fireborns;
import ooad.arcane.Creature.Terravores;
import ooad.arcane.Creature.Zephyrals;

import java.util.ArrayList;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String event);

    default String getType(Subject subject) {
        return switch(subject) {
            case EmberKnight ignored -> "EmberKnight";
            case MistWalker ignored -> "MistWalker";
            case TerraVoyager ignored -> "TerraVoyager";
            case ZephyrRogue ignored -> "ZephyrRogue";
            case Aquarids ignored -> "Aquarid";
            case Fireborns ignored -> "Fireborn";
            case Terravores ignored -> "Terravore";
            case Zephyrals ignored -> "Zephyral";
            default -> throw new IllegalStateException("Unexpected value: " + subject);
        };
    }
}
