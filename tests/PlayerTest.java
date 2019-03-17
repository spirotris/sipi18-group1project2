/**
 * @author Marcus Laitala
 * @Date 2019-03-17 * 
 * @version 0.1
 */

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import game.Point;
import game.Player;

@RunWith(JUnitParamsRunner.class)
public class PlayerTest {
	Player p;

	@Before
	public void setup() {		
		p = new Player(new Point(1,1,0));
	}
	
	@Test
	@Parameters({ "Tommy", "Emil", "Bob", "Marcus", "\n\t", "" })
	public void testSettingPlayerName(String name) {
		// Arrange
		p.setName(name);
		// Act
		String actual = p.getName();
		// Assert
		assertEquals(actual, name);
	}

	@Test
	@Parameters({ "" })
	public void testGetPosition(Point po) {
		// Arrange
		p.setPosition(po);

		// Act
		Point pNew = p.getPosition();
		// Assert
		assertEquals(pNew, po);		
	}
}
