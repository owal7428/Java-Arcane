package ooad.arcane.Adventurer.Behaviors;

public class NoviceSearch implements SearchBehavior {
    @Override
    public boolean searchRoll(int initialRoll) {
        return initialRoll < 15;
    }
}
