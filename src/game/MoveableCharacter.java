/**
 * 
 */
package game;

/**
 * @author Marcus Laitala
 * @Date 2019-03-23
 */
public abstract class MoveableCharacter extends Point {

	/**
	 * @param y
	 * @param x
	 * @param tileType
	 */
	public MoveableCharacter(int y, int x, TileType tileType) {
		super(y, x, tileType);
	}
	
	public void move(Point p) {
		this.setY(p.getY());
		this.setX(p.getX());
		this.setTileType(p.getTileType());
	}
}
