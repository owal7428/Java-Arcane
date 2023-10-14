package ooad.arcane.Adventurer;

import ooad.arcane.Manager.AdventurerManager;

import java.util.Objects;

public class ZephyrRogue extends Adventurer {
    public ZephyrRogue(AdventurerManager manager) {
        super(3, 0.25f, manager);
    }

    @Override
    public void ApplyDiscordOrResonance() {
        // Check resonance
        if (Objects.equals(getFloor(), "AirFloor")) {
            this.diceBonusTreasure = 2;
            this.hasResonance = true;
            notifyObservers(getType(this) + " has air resonance.");
        }
            // Check discord
        else if (Objects.equals(getFloor(), "EarthFloor")) {
            this.diceBonusTreasure = -2;
            this.hasDiscord = true;
            notifyObservers(getType(this) + " has earth discord.");
        }
            // Reset to defaults
        else {
            this.diceBonusTreasure = 0;
            this.hasResonance = false;
            this.hasDiscord = false;
        }
    }

}
