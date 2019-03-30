
/**
 * @author Marcus Laitala
 * @Date 2019-03-17 
 * @version 0.8
 */

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import game.Player;
import game.Point;
import game.TileType;

import static game.TileType.*;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class PlayerTest {
	Player player;

	@Before
	public void setup() {
		player = new Player(9, 1, CHARACTER);
	}

	@Test
	public void testPlayerPickupTreasureIncreasesTreasure() {
		// Arrange
		player.addTreasure();

		// Act
		int actual = player.getTreasure();

		// Assert
		assertEquals(actual, 1);
	}

	@Test
	public void testSetPlayerPosition() {
		// Arrange
		player = new Player(1, 1, TileType.CHARACTER);
		
		//Act
		Point actual = (Point)player;
		
		//Assert
		assertTrue(actual.equals(player));

	}
	
	@Test
	public void testSetPlayerAlive() {
		//Arrange
		Boolean alive = false;
		
		//Act
		player.setAlive(alive);
		
		//Assert
		assertFalse(player.isAlive());
	}
}
