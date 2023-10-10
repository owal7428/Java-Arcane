package ooad.arcane;

import java.util.HashMap;
import java.util.Map;

public class Tracker implements Observer {
    private Map<String, String> gameData = new HashMap<>();

    @Override
    public void update(String eventMessage) {
        String[] parts = eventMessage.split("=");
        if (parts.length == 2) {
            gameData.put(parts[0], parts[1]);
        }
    }


public void printSummary() {
    System.out.println("Tracker: Turn " + (gameData.containsKey("Turn") ? gameData.get("Turn") : "N/A"));
    System.out.println("Total Treasure Value: " + (gameData.containsKey("TotalTreasureValue") ? gameData.get("TotalTreasureValue") : "N/A"));
    System.out.println("Total Active Adventurers: " + (gameData.containsKey("TotalActiveAdventurers") ? gameData.get("TotalActiveAdventurers") : "N/A"));
    System.out.println("Adventurers        Room            Health      Treasure                         Treasure Value");
    
    printAdventurerData("EmberKnight");
    printAdventurerData("MistWalker");
    printAdventurerData("TerraVoyager");
    printAdventurerData("ZephyrRogue");

    System.out.println("Elemental Resonance:");
    if (gameData.containsKey("ElementalResonance")) {
        System.out.println(gameData.get("ElementalResonance"));
    } else {
        System.out.println("None");
    }

    System.out.println("Elemental Discord:");
    if (gameData.containsKey("ElementalDiscord")) {
        System.out.println(gameData.get("ElementalDiscord"));
    } else {
        System.out.println("None");
    }

    System.out.println("Total Active Creatures: " + (gameData.containsKey("TotalActiveCreatures") ? gameData.get("TotalActiveCreatures") : "N/A"));

}


private void printAdventurerData(String adventurerName) {
    System.out.print(adventurerName + "      ");
    System.out.print((gameData.containsKey(adventurerName + "Room") ? gameData.get(adventurerName + "Room") : "N/A") + "      ");
    System.out.print((gameData.containsKey(adventurerName + "Health") ? gameData.get(adventurerName + "Health") : "N/A") + "          ");
    System.out.print((gameData.containsKey(adventurerName + "Treasure") ? gameData.get(adventurerName + "Treasure") : "N/A") + "      ");
    System.out.println((gameData.containsKey(adventurerName + "TreasureValue") ? gameData.get(adventurerName + "TreasureValue") : "N/A"));
}


}
