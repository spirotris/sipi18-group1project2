
/**
 * @author Marcus Laitala
 * @Date 2019-03-17
 * @version 0.8
 */

import junitparams.JUnitParamsRunner;

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
	public void testPlayerPickupTreasureIncreasesTreasure() {
		// Arrange
		Player.addTreasure();

		// Act
		int actual = Player.getTreasure();

		// Assert
		assertEquals(actual, 1);
	}

	@Test
	public void testSetPlayerPosition() {
		// Arrange
		Player.y = 9;
		Player.x = 2;

		//Act
		boolean actual = Player.getY() == 9 && Player.getX() == 2;

		//Assert
		assertTrue(actual);

	}	
}
