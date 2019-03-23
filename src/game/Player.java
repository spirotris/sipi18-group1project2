package game;

/**
 * @author Marcus Laitala
 * @Date 2019-03-17
 * @version 0.3
 */
public class Player extends MoveableCharacter {

	public Player(int y, int x, TileType tileType) {
		super(y, x, tileType);		
	}
	
	private int treasures;
    private boolean alive = true;
	
	public int getTreasure() {
		return treasures;
	}
	
	public void setTreasure(int treasures) {
		this.treasures += treasures;
	}

	public boolean getAlive() {
        return alive;
    }
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
