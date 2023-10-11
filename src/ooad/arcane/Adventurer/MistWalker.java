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
        if (Objects.equals(getFloor(), "WaterFloor"))
            this.dodge = 0.75f;
            // Check discord
        else if (Objects.equals(getFloor(), "AirFloor"))
            this.dodge = 0.25f;
            // Reset to defaults
        else
            this.dodge = 0.5f;
    }

}
