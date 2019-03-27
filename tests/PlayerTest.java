
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

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class PlayerTest {
	Player player;

	@Before
	public void setup() {
		player = new Player();
	}

	@Test
	@Parameters({ "10", "0", "-1" })
	public void testSetAndGetTreasureCount(int treasureCount) {
		// Arrange
		player.addTreasure(treasureCount);

		// Act
		int actual = player.getTreasure();

		// Assert
		assertEquals(actual, treasureCount);
	}

	@Test
	public void testSetPlayerPosition() {
		// Arrange
		player = new Player();
		
		//Act
		//Point actual = player;
		
		//Assert
		assertTrue(false);

	}
	
	@Test
	public void testSetPlayerAlive() {
		//Arrange
		Boolean alive = false;
		
		//Act
		player.setAlive(alive);
		
		//Assert
		assertFalse(player.getAlive());
	}
}
