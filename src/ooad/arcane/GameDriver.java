package ooad.arcane;

public class GameDriver {
    /* This whole main function is an example of abstraction because all of the complexity behind the internals
    * of the game have been abstracted away so only a few function calls are necessary to run the game. */

    public static void main(String[] args) {
        int numAdventurerWins = 0;
        int numCreatureWins = 0;

        boolean shouldRender = false;

        for (int i = 0; i < 30; i++) {
            GameEngine engine = new GameEngine(shouldRender);
            int winner = engine.Simulate();

            if (winner == 1)
                numAdventurerWins++;
            else
                numCreatureWins++;
        }

        if (!shouldRender) {
            System.out.println("The adventurers won " + numAdventurerWins + " times");
            System.out.println("The creatures won " + numCreatureWins + " times");
        }

        System.out.println();
        System.out.println("...");
        System.out.println("Game Finished.");
    }

}
