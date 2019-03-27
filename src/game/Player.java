package game;

/**
 * @author Marcus Laitala
 * @Date 2019-03-17
 * @version 0.3
 */
public class Player{

	public Player() {
		treasures = 0;
	}
	
	private int treasures;
    private boolean alive = true;
	
	public int getTreasure() {
		return treasures;
	}
	
	public void addTreasure() {
		this.treasures++;
	}

	public boolean getAlive() {
        return alive;
    }
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
