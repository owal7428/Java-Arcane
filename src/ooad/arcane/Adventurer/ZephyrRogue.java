package ooad.arcane.Adventurer;

import ooad.arcane.Manager.AdventurerManager;

import java.util.Objects;

public class ZephyrRogue extends Adventurer {
    public ZephyrRogue(AdventurerManager manager) {
        super(3, 0.25f, manager, "Zephyr Rogue");
    }

    @Override
    public void ApplyDiscordOrResonance() {
        // Check resonance
        if (Objects.equals(getFloor(), "AirFloor"))
            this.diceBonusTreasure= 2;
            // Check discord
        else if (Objects.equals(getFloor(), "EarthFloor"))
            this.diceBonusTreasure = -2;
            // Reset to defaults
        else
            this.diceBonusTreasure = 0;
    }

}
