package ooad.arcane.Utility;

import java.util.Random;

public class Dice {
    private static final Random roll = new Random();

    public static int rollCustom(int size) {
        return roll.nextInt(0, size);
    }

    public static int rollD10s() {
        int r1 = roll.nextInt(0,10) + 1;
        int r2 = roll.nextInt(0,10) + 1;
        return r1 + r2;
    }

    public static int rollD6s() {
        int r1 = roll.nextInt(0,6) + 1;
        int r2 = roll.nextInt(0,6) + 1;
        return r1 + r2;
    }

    public static int rollD4s() {
        int r1 = roll.nextInt(0,4) + 1;
        int r2 = roll.nextInt(0,4) + 1;
        return r1 + r2;
    }

    public static int rollD4() {
        return roll.nextInt(0,4) + 1;
    }

    public static int rollD2_Include0() {
        return roll.nextInt(-1,2) + 1;
    }
}
