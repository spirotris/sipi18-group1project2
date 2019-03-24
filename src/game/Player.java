package game;

public class Player extends Point {

    private int treasures;
    private boolean alive = true;
    
    public Player(int y, int x, TileType tileType) {
        super(y, x, tileType);
    }

    public int getTreasure() {
        return treasures;
    }

    public void addTreasure() {
        treasures++;
    }

    public boolean isAlive() {
        return alive;
    }
}
