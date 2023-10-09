package ooad.test;

import ooad.arcane.Adventurer.EmberKnight;
import ooad.arcane.Floor.ElementalFloor;
import ooad.arcane.Floor.Floor;
import ooad.arcane.Floor.Room;
import ooad.arcane.Manager.AdventurerManager;
import ooad.arcane.Manager.CreatureManager;
import ooad.arcane.Manager.FloorManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class FloorTest {

    @Test
    public void testRoomConstruct() {
        Room room = new Room(1,2);
        assert (Arrays.equals(room.getCoordinates(), new int[]{1, 2}));
    }

    @Test
    public void testFloorConstruct() {
        Room room = new Room(1,2);

        ArrayList<Room> floorMap = new ArrayList<>();
        floorMap.add(room);

        ElementalFloor floor = new ElementalFloor("WaterFloor", floorMap);

        assert (floor.getFloorMap() == floorMap);
        assert (Objects.equals(floor.getName(), "WaterFloor"));
    }

    /* Check that the FloorManager is correctly initializing the
    /* adjacent rooms for starting floor. */
    @Test
    public void testFloorManagerInitStarterAdjacent() {
        FloorManager floorManager = new FloorManager();

        ArrayList<Room> testAdjacent = new ArrayList<>();
        testAdjacent.add(new Room(0,1));
        testAdjacent.add(new Room(1,0));
        testAdjacent.add(new Room(0, -1));
        testAdjacent.add(new Room(-1, 0));

        ArrayList<Room> testedAdjacent = floorManager.getFloor("StartFloor").getAdjacentRooms(new int[] {0,0});

        for (int i = 0; i < 4; i++) {
            assert (testAdjacent.get(i).getCoordinates()[0] == testedAdjacent.get(i).getCoordinates()[0]);
            assert (testAdjacent.get(i).getCoordinates()[1] == testedAdjacent.get(i).getCoordinates()[1]);
        }
    }

    // Check move function works for adventurer
    @Test
    public void testAdventurerMove() {
        FloorManager floorManager = new FloorManager();

        CreatureManager creatureManager = new CreatureManager(floorManager);

        AdventurerManager adventurerManager = new AdventurerManager(creatureManager, floorManager);

        creatureManager.setAdventurerManager(adventurerManager);

        EmberKnight knight = new EmberKnight(adventurerManager);

        int[] oldLocation = knight.getLocation();
        String oldFloor = knight.getFloor();

        knight.Turn();

        System.out.println(knight.getFloor());
        System.out.println(knight.getLocation()[0] + "," + knight.getLocation()[1]);

        assert (!Objects.equals(knight.getFloor(), oldFloor) && knight.getLocation() != oldLocation);
    }
    @Test
    public void testAdventurerListUpdateWhenMove() {
        FloorManager floorManager = new FloorManager();

        CreatureManager creatureManager = new CreatureManager(floorManager);

        AdventurerManager adventurerManager = new AdventurerManager(creatureManager, floorManager);

        creatureManager.setAdventurerManager(adventurerManager);

        EmberKnight knight = new EmberKnight(adventurerManager);

        for (int i = 1; i <= 5; i++)
        {
            int[] oldLocation = knight.getLocation();
            Floor oldFloor = floorManager.getFloor(knight.getFloor());

            knight.Turn();

            int[] newLocation = knight.getLocation();
            Floor newFloor = floorManager.getFloor(knight.getFloor());


            System.out.println("Turn: " + i);

            System.out.println(oldFloor.getName());
            System.out.println(oldLocation[0] + "," + oldLocation[1]);

            System.out.println(newFloor.getName());
            System.out.println(newLocation[0] + "," + newLocation[1]);

            System.out.println("------------");

            assert(oldFloor.getAdventurersInRoom(oldLocation).isEmpty()
                    && newFloor.getAdventurersInRoom(newLocation).size() == 1 );
        }
    }
}
