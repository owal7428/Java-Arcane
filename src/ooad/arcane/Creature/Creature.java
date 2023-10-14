package ooad.arcane.Creature;

import ooad.arcane.Utility.Dice;
import ooad.arcane.Utility.Observer;
import ooad.arcane.Utility.Subject;
import ooad.arcane.Manager.CreatureManager;
import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Floor.Room;

import java.util.ArrayList;

/* This is an example of inheritance because this class never gets instantiated but all the creature
* subclasses will inherit its methods which cover the functionality all the creatures share. */

public abstract class Creature implements Subject {
    public boolean isDead;
    protected String floor;
    protected int[] location;
    private int attackBonus = 0;

    protected CreatureManager manager;

    private final ArrayList<Observer> observers = new ArrayList<>();

    //Constructor (spawn method)
    public Creature(CreatureManager manager, String floor) {
        this.isDead = false;
        this.location = new int[]{0,0};
        this.floor = floor;
        this.manager = manager;

        Spawn();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String event) {
        for (Observer observer : observers)
            observer.Update(event);
    }

    public void Turn() {
        // Check if there are adventurers in the current room
        if (manager.getAdventurersInRoom(floor, location).isEmpty()) {
            Move(manager.getCurrentAdjacentRooms(floor, location));

            if (!manager.getAdventurersInRoom(floor, location).isEmpty())
                Fight();
        }
        else {
            Fight();
        }
    }

    /* This is an example of polymorphism because each subclass implements this method differently.  */
    protected void Move(ArrayList<Room> adjacentRooms) {
        // Implemented on a class-by-class basis.
    }
    private void Fight() {
        ArrayList<Adventurer> Adventurers = manager.getAdventurersInRoom(floor, location);

        for (Adventurer enemy : Adventurers) {
            notifyObservers(getType(this) + " is now fighting " + getType(enemy) + ".");

            manager.callAttack(enemy, this);

            // Stop fighting if dead
            if (!manager.checkAlive(this)) {
                notifyObservers(getType(this) + " has died.");
                break;
            }
        }

        manager.signalReap();
    }

    public void Spawn() {
        // Randomly choose x and y
        int x = Dice.rollD2_Include0();
        int y = Dice.rollD2_Include0();

        this.location = new int[]{x,y};

        manager.spawnInitRoom(this, floor, location);
    }

    public String getFloor() {
        return floor;
    }

    public int[] getLocation() {
        return location;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(int bonus) {
        attackBonus = bonus;
    }
}
