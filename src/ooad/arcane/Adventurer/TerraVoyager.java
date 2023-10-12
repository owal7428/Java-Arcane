package ooad.arcane.Adventurer;

import ooad.arcane.Manager.AdventurerManager;

import java.util.Objects;

public class TerraVoyager extends Adventurer {
    int health = 7;
    float dodge = 0.1f;
    String affinity = "Earth";

    public TerraVoyager(AdventurerManager manager) {
        super(7, 0.1f, manager);
    }

    @Override
    public void ApplyDiscordOrResonance() {
        // Check resonance
        if (Objects.equals(getFloor(), "EarthFloor"))
            this.damageTaken = 1;
            // Check discord
        else if (Objects.equals(getFloor(), "FireFloor"))
            this.damageTaken = 3;
            // Reset to defaults
        else
            this.damageTaken = 2;
    }

}
