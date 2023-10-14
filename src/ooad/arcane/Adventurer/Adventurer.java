package ooad.arcane.Adventurer;

import ooad.arcane.Adventurer.Behaviors.*;
import ooad.arcane.Adventurer.Treasure.Treasure;
import ooad.arcane.Adventurer.Treasure.TreasureBag;
import ooad.arcane.Creature.Creature;
import ooad.arcane.Floor.Room;
import ooad.arcane.Manager.AdventurerManager;
import ooad.arcane.Utility.Dice;
import ooad.arcane.Adventurer.Treasure.Decorators.*;
import ooad.arcane.Utility.Observer;
import ooad.arcane.Utility.Subject;

import java.util.*;

public abstract class Adventurer implements Subject {
    private int health;
    private String floor;
    private int[] location;

    protected int diceBonusCombat;
    protected int diceBonusTreasure;
    protected int damageTaken;
    protected float dodge;

    private int healthBuff = 0;
    private int combatBuff = 0;
    private int treasureBuff = 0;
    private int creatureBuff = 0;
    private float dodgeBuff = 0;

    private int combatLevel = 0;
    private int searchLevel = 0;

    private CombatBehavior combatBehavior;
    private SearchBehavior searchBehavior;

    private boolean canTeleport = false;
    protected boolean hasResonance = false;
    protected boolean hasDiscord = false;

    /* The manager is here to send signals to other managers so that
    * the adventurers can communicate with the other game elements */
    AdventurerManager manager;
    Treasure inventory;

    private final ArrayList<Observer> observers = new ArrayList<>();

    protected Adventurer(int health, float dodge, AdventurerManager manager) {
        this.health = health;
        this.floor = "StartFloor";
        this.location = new int[] {0,0};
        this.diceBonusCombat = 0;
        this.diceBonusTreasure = 0;
        this.damageTaken = 2;
        this.dodge = dodge;
        this.combatBehavior = new NoviceCombat();
        this.searchBehavior = new NoviceSearch();
        this.manager = manager;
        this.inventory = new TreasureBag();

        manager.spawnInitRoom(this, floor, location);
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
        /* Get creatures in room; If there aren't look for treasure.
         * Else, fight the creatures. */
        ArrayList<Creature> creatures = manager.getCreaturesInCurrentRoom(floor, location);

        if (creatures.isEmpty()) {
            // Move if there are no creatures
            ArrayList<Room> adjacentRooms = manager.getCurrentAdjacentRooms(floor, location);
            Move(adjacentRooms);

            // notify observers of this adventurer's new location
            notifyObservers(getType(this) + " moved to " + floor + ": "
                    + Arrays.toString(location) + ".");

            creatures = manager.getCreaturesInCurrentRoom(floor, location);

            if (creatures.isEmpty()) {
                // Check if in elemental floor and in center
                if (!Objects.equals(floor, "StartFloor") && Arrays.equals(location, new int[]{1, 1})) {
                    manager.updateRoomAndFloorLists(this,floor,location,"StartFloor",new int[]{0,0});
                    this.floor = "StartFloor";
                    this.location = new int[]{0,0};
                    ApplyDiscordOrResonance();
                }
                /* Check if we're on the starting floor. If so, check which elemental tile we're on
                 * and move to that floor at position {1,1} which is the center. */
                else if (Objects.equals(floor, "StartFloor")) {
                    String newFloor = "";
                    if (Arrays.equals(location, new int[]{0, 1}))
                        newFloor = "AirFloor";
                    else if (Arrays.equals(location, new int[]{1, 0}))
                        newFloor = "EarthFloor";
                    else if (Arrays.equals(location, new int[]{0, -1}))
                        newFloor = "FireFloor";
                    else if (Arrays.equals(location, new int[]{-1, 0}))
                        newFloor = "WaterFloor";

                    manager.updateRoomAndFloorLists(this,floor,location,newFloor,new int[]{1,1});
                    this.floor = newFloor;
                    this.location = new int[]{1, 1};

                    notifyObservers(getType(this) + " moved to " + floor + ": "
                            + Arrays.toString(location) + ".");

                    ApplyDiscordOrResonance();
                    FindTreasure();
                }
                else {
                    FindTreasure();
                }
            }
            else {
                Fight();
            }
        }
        else {
            Fight();
        }
    }

    private void Move(ArrayList<Room> adjacentRooms) {
        /* Attempt to teleport if able to. If the roll fails, will move normally. */
        if (canTeleport) {
            if (Teleport()) return;
        }

        int nextRoom = Dice.rollCustom(adjacentRooms.size());

        int[] newLocation = adjacentRooms.get(nextRoom).getCoordinates();

        // Update the adventurer list for the current room
        manager.updateRoomAndFloorLists(this, floor, location, floor, newLocation);

        this.location = newLocation;
    }

    // Returns true if teleport was successful, false otherwise.
    private boolean Teleport() {
        int shouldTeleport = Dice.rollD10s();

        if (shouldTeleport < 15)
            return false;

        int floor_num = Dice.rollD4();
        int x = Dice.rollD2_Include0();
        int y = Dice.rollD2_Include0();

        int[] newLocation = new int[]{x,y};

        String newFloor = switch (floor_num) {
            case 1 -> "FireFloor";
            case 2 -> "WaterFloor";
            case 3 -> "AirFloor";
            case 4 -> "EarthFloor";
            default -> floor;
        };

        manager.updateRoomAndFloorLists(this, floor, location, newFloor, newLocation);

        this.floor = newFloor;
        this.location = newLocation;

        return true;
    }

    private void FindTreasure() {
        int roll = Dice.rollD10s();

        // Skip if roll fails
        if (searchBehavior.searchRoll(roll + diceBonusCombat + treasureBuff))
            return;

        ArrayList<Treasure> treasures = new ArrayList<>(manager.getTreasuresInCurrentRoom(floor, location));

        // This is an example of using decorator pattern
        for (Treasure treasure : treasures) {
            boolean collected = false;

            String treasureType = "";

            inventory = switch (treasure) {
                case Armor ignored -> {
                    if (Collections.frequency(inventory.getTreasures(), "Armor") >= 1)
                        yield inventory;
                    else {
                        collected = true;
                        treasureType = "Armor";
                        yield new Armor(inventory);
                    }
                }
                case Elixir ignored -> {
                    if (Collections.frequency(inventory.getTreasures(), "Elixir") >= 1)
                        yield inventory;
                    else {
                        collected = true;
                        treasureType = "Elixir";
                        yield new Elixir(inventory);
                    }
                }
                case Ether ignored -> {
                    if (Collections.frequency(inventory.getTreasures(), "Ether") >= 1)
                        yield inventory;
                    else {
                        collected = true;
                        treasureType = "Ether";
                        yield new Ether(inventory);
                    }
                }
                case Portal ignored -> {
                    if (Collections.frequency(inventory.getTreasures(), "Portal") >= 1)
                        yield inventory;
                    else {
                        collected = true;
                        treasureType = "Portal";
                        yield new Portal(inventory);
                    }
                }
                case Potion ignored -> {
                    if (Collections.frequency(inventory.getTreasures(), "Potion") >= 1)
                        yield inventory;
                    else {
                        collected = true;
                        treasureType = "Potion";
                        yield new Potion(inventory);
                    }
                }
                case Sword ignored -> {
                    if (Collections.frequency(inventory.getTreasures(), "Sword") >= 1)
                        yield inventory;
                    else {
                        collected = true;
                        treasureType = "Sword";
                        yield new Sword(inventory);
                    }
                }
                case Gem ignored -> {
                    collected = true;
                    treasureType = "Gem";
                    yield new Gem(inventory);
                }
                default -> inventory;
            };

            if (collected) {
                manager.removeTreasureFromRoom(treasure, floor, location);
                notifyObservers(getType(this) + " has found a(n) "
                        + treasureType + ".");
                UpgradeSearch();
                break;
            }
        }

        applyItemBuffs();
    }

    private void UpgradeSearch() {
        this.searchLevel += 1;

        searchBehavior = switch(searchLevel) {
            case 1 -> new SeasonedSearch();
            case 2 -> new VeteranSearch();
            case 3 -> new MasterSearch();
            default -> searchBehavior;
        };

        notifyObservers(getType(this) + " has upgraded search.");
    }

    private void applyItemBuffs() {
        List<String> itemList = inventory.getTreasures();

        // Reset buff values before recalculating
        healthBuff = 0;
        combatBuff = 0;
        treasureBuff = 0;
        creatureBuff = 0;
        dodgeBuff = 0;

        for (String item : itemList) {
            switch (item) {
                case "Sword":
                    combatBuff += 1;
                    break;
                case "Armor":
                    creatureBuff -= 1;
                    break;
                case "Portal":
                    canTeleport = true;
                    break;
                case "Potion":
                    healthBuff += 1;
                    break;
                case "Ether":
                    treasureBuff += 1;
                    break;
                case "Elixir":
                    dodgeBuff += 0.1f;
                    break;
                case "Gem":
                    creatureBuff += 1;
                    break;
            }
        }
    }

    protected void ApplyDiscordOrResonance() {
        // Each adventurer should implement their own version of this.
    }

    private void Fight() {
        ArrayList<Creature> creatures = manager.getCreaturesInCurrentRoom(floor, location);

        for (Creature enemy : creatures) {
            FightCreature(enemy);

            // Stop fighting if dead
            if (health + healthBuff <= 0) {
                notifyObservers(getType(this) + " has died.");
                break;
            }
        }

        manager.signalReap();
    }

    public void FightCreature(Creature enemy) {
        notifyObservers(getType(this) + " is now fighting " + getType(enemy) + ".");

        int attack = Dice.rollD6s() + diceBonusCombat + combatBuff;
        float dodge = this.dodge + dodgeBuff;

        enemy.setAttackBonus(creatureBuff);

        int damage = combatBehavior.Fight(this, enemy, attack, dodge, damageTaken);
        health -= damage;

        int tempHealth = health + healthBuff;

        notifyObservers(getType(this) + " has lost " + damage
                + " health; it now has " + tempHealth + " left.");

        // Reset enemy's attack bonus because it should only apply to this adventurer
        enemy.setAttackBonus(0);
    }

    public void UpgradeCombat() {
        this.combatLevel += 1;

        combatBehavior = switch(combatLevel) {
            case 1 -> new SeasonedCombat();
            case 2 -> new VeteranCombat();
            case 3 -> new MasterCombat();
            default -> combatBehavior;
        };

        notifyObservers(getType(this) + " has upgraded combat.");
    }

    public int getHealth() {
        return health + healthBuff;
    }

    public String getFloor() {
        return floor;
    }

    public int[] getLocation() {
        return location;
    }

    public int getNumTreasures() {
        return inventory.getNumTreasures();
    }

    public int getTreasureValue() {
        return inventory.getValue();
    }

    public String getTreasures() {
        int size = inventory.getNumTreasures();
        List<String> treasures = inventory.getTreasures();

        StringBuilder temp = new StringBuilder();

        /* Loop through the list of all treasures and add their type to string.
        * Do not add the comma when at the end of the list. */
        for (int i = 0; i < size; i++) {
            temp.append(treasures.get(i));
            if (i != size - 1)
                temp.append(", ");
        }

        return temp.toString();
    }

    public boolean getHasResonance() {
        return hasResonance;
    }

    public boolean getHasDiscord() {
        return hasDiscord;
    }
}
