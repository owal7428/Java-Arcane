package ooad.arcane.Adventurer;

import ooad.arcane.Floor.Room;
import ooad.arcane.Manager.AdventurerManager;

import java.util.Objects;

public class EmberKnight extends Adventurer {
    public EmberKnight(AdventurerManager manager) {
        super(5, 0.2f, manager);
    }

    @Override
    public void ApplyDiscordOrResonance() {
        // Check resonance
        if (Objects.equals(getFloor(), "FireFloor")) {
            this.diceBonusCombat = 2;
            this.hasResonance = true;
            notifyObservers(getType(this) + " has fire resonance.");
        }
        // Check discord
        else if (Objects.equals(getFloor(), "WaterFloor")) {
            this.diceBonusCombat = -2;
            this.hasDiscord = true;
            notifyObservers(getType(this) + " has water discord.");
        }
        // Reset to defaults
        else {
            this.diceBonusCombat = 0;
            this.hasResonance = false;
            this.hasDiscord = false;
        }
    }

}
