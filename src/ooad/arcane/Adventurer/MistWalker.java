package ooad.arcane.Adventurer;

import ooad.arcane.Manager.AdventurerManager;

import java.util.Objects;

public class MistWalker extends Adventurer {
    public MistWalker(AdventurerManager manager) {
        super(3, 0.5f, manager);
    }

    @Override
    public void ApplyDiscordOrResonance() {
        // Check resonance
        if (Objects.equals(getFloor(), "WaterFloor")) {
            this.dodge = 0.75f;
            this.hasResonance = true;
            notifyObservers(getType(this) + " has water resonance.");
        }
            // Check discord
        else if (Objects.equals(getFloor(), "AirFloor")) {
            this.dodge = 0.25f;
            this.hasDiscord = true;
            notifyObservers(getType(this) + " has air discord.");
        }
            // Reset to defaults
        else {
            this.dodge = 0.5f;
            this.hasResonance = false;
            this.hasDiscord = false;
        }
    }

}
