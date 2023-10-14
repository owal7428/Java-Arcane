package ooad.arcane.Adventurer;

import ooad.arcane.Manager.AdventurerManager;

import java.util.Objects;

public class TerraVoyager extends Adventurer {
    public TerraVoyager(AdventurerManager manager) {
        super(7, 0.1f, manager);
    }

    @Override
    public void ApplyDiscordOrResonance() {
        // Check resonance
        if (Objects.equals(getFloor(), "EarthFloor")) {
            this.damageTaken = 1;
            this.hasResonance = true;
            notifyObservers(getType(this) + " has earth resonance.");
        }
            // Check discord
        else if (Objects.equals(getFloor(), "FireFloor")) {
            this.damageTaken = 3;
            this.hasDiscord = true;
            notifyObservers(getType(this) + " has fire discord.");
        }
            // Reset to defaults
        else {
            this.damageTaken = 2;
            this.hasResonance = false;
            this.hasDiscord = false;
        }
    }

}
