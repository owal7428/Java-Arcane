package ooad.arcane.Adventurer.Behaviors;

/* Interface used in strategy pattern. The classes that inherit from this
 * are the concrete behaviors. */
public interface SearchBehavior {
    boolean searchRoll(int initialRoll);
}
