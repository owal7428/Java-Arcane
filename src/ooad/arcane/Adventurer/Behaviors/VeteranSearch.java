package ooad.arcane.Adventurer.Behaviors;

public class VeteranSearch implements SearchBehavior {
    @Override
    public boolean searchRoll(int initialRoll) {
        return initialRoll + 2 < 15;
    }
}
