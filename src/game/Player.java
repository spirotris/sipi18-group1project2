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
    private static int y;
    private static int x;

	public Player() {
        treasures = 0;
    }

    public static int getTreasure() {
        return treasures;
    }
   
    public static void addTreasure() {
        treasures++;
    }
    
    public static void resetTreasures() {
    	treasures = 0;
    }

    public static int getY() {
        return y;
    }

    public static int getX() {
        return x;
    }
    
    public static void setY(int y) {
		Player.y = y;
	}

	public static void setX(int x) {
		Player.x = x;
	}
}
