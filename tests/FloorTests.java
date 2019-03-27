import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import game.*;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class FloorTests {

	Floor floor;

	@Before
	public void setup() {
		floor = new Floor(0, 0);
	}

	@Test
	public void testSetFloorOnPointAndCheckIfItIsThere() {
		//Arrange - The arrange part is being done in @Before
				
		// Act
		Point actual = (Point) floor;
				
		// Assert
		assertTrue(actual.equals(floor));
	}	

	@Test
	@Parameters({"Player", "Monster", "Treasure", "Door"})
	public void testSetPlayerOnTileToTrue(String arg) {
		// Arrange
		switch (arg) {
		case "Player":
			floor.setPlayerOnTile(true);
			break;
		case "Monster":
			floor.setMonsterOnTile(true);
			break;
		case "Treasure":
			floor.setTreasureOnTile(true);
			break;
		case "Door":
			floor.setDoorOnTile(true);
			break;
		}
		// Act
		boolean actual = false;
		
		switch (arg) {
		case "Player":
			actual = floor.isPlayerOnTile();
			break;
		case "Monster":
			actual = floor.isMonsterOnTile();
			break;
		case "Treasure":
			actual = floor.isTreasureOnTile();
			break;
		case "Door":
			actual = floor.isDoorOnTile();
			break;
		}
		// Assert
		assertTrue(actual);

	}

}
