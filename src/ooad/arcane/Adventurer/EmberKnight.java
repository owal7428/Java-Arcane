package ooad.arcane.Adventurer;

import ooad.arcane.Floor.Room;
import ooad.arcane.Manager.AdventurerManager;

import java.util.Objects;

public class EmberKnight extends Adventurer {
    public EmberKnight(AdventurerManager manager) {
        super(5, 0.2f, manager, "Ember Knight");
    }

    @Override
    public void ApplyDiscordOrResonance() {
        // Check resonance
        if (Objects.equals(getFloor(), "FireFloor"))
            this.diceBonusCombat = 2;
        // Check discord
        else if (Objects.equals(getFloor(), "WaterFloor"))
            this.diceBonusCombat = -2;
        // Reset to defaults
        else
            this.diceBonusCombat = 0;
    }

}
