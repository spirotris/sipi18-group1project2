/**
 * @author Marcus Laitala
 * @Date 2019-03-17 * 
 * @version 0.1
 */

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import game.Point;

@RunWith(JUnitParamsRunner.class)
public class PlayerTest {

	@Before
	public void setup() {
		Player p = new Player();
	}

	@After
	public void reset() {
		p.reset();
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
