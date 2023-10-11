package ooad.arcane.Adventurer.Behaviors;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Creature.Creature;
import ooad.arcane.Utility.Dice;

public class NoviceCombat implements CombatBehavior {
    /* Method used to compare the attack of the adventurer and the creature it's attacking.
     * Return value indicates damage to be done to the health of the adventurer.
     * Interfaces with the creature manager class. */
    public int Fight(Adventurer adventurer, Creature creature, int attack, float dodge, int damage) {
        int creatureAttack = Dice.rollD6s() + creature.getAttackBonus();
        int damageTaken = 0;

        // Check which won the dice roll
        if (attack > creatureAttack) {
            creature.isDead = true;
            adventurer.UpgradeCombat();
        }
        else if (attack < creatureAttack) {
            /* Generate number between 1 and 100 inclusive. If number is less
             * than or equal to dodge * 100, dodge was successful. Doing this gets
             * probability matching dodge as a probability. */
            int dodgeRoll = Dice.rollCustom(100);

            if (dodgeRoll > dodge * 100)
                damageTaken += damage;
        }

        return damageTaken;
    }
}
