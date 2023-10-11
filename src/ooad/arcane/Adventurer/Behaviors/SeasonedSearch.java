package ooad.arcane.Adventurer.Behaviors;

public class SeasonedSearch implements SearchBehavior {
    @Override
    public boolean searchRoll(int initialRoll) {
        return initialRoll + 1 < 15;
    }
}
