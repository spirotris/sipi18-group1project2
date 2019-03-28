package game;

/**
 * @author Marcus Laitala
 * @Date 2019-03-17
 * @version 0.3
 */
public class Player{

    private static int treasures;
    public static boolean isAlive = true;
    public static boolean isFinished;
    public static int y;
    public static int x;

    public Player() {
        treasures = 0;
    }

    public static int getTreasure() {
        return treasures;
    }

    public static void addTreasure() {
        treasures++;
    }

    public static int getY() {
        return y;
    }

    public static int getX() {
        return x;
    }
}
