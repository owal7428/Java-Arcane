package ooad.arcane.Adventurer.Behaviors;

public class MasterSearch implements SearchBehavior {
    @Override
    public boolean searchRoll(int initialRoll) {
        return initialRoll + 3 < 15;
    }
}
