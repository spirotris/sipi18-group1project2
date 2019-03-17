/**
 * 
 */
package game;

/**
 * @author Marcus
 * @Date 2019-03-17
 * @version 0.1
 */
public class Player extends Point{
	public Player(int y, int x, int status) {
		super(y, x, status);
	}
	
	public Player(Point p) {
		super(p.getY(), p.getX(), p.getStatus());
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getPosition() {
		return super.getPoint();
	}

	public void setPosition(Point position) {
		setPoint(position);
	}

}
