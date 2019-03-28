import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import game.*;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class FloorTests {

	Point[][] board;

	Point floor;

	@Before
	public void setup() {
		board = new Point[20][20];
		board[10][10] = new Floor();
		floor = board[10][10];
	}

	@Test
	public void testSetFloorOnPointAndCheckIfItIsThere() {
		//Arrange - The arrange part is being done in @Before

		// Act
				
		// Assert
		assertSame(floor.getClass(), Floor.class);
	}	

	@Test
	@Parameters({"Player", "Monster", "Treasure", "Door"})
	public void testSetPlayerOnTileToTrue(String arg) {
		// Arrange
		switch (arg) {
		case "Player":
			((Floor)floor).setPlayerOnTile(true);
			break;
		case "Monster":
			((Floor)floor).setMonsterOnTile(true);
			break;
		case "Treasure":
			((Floor)floor).setTreasureOnTile(true);
			break;
		case "Door":
			((Floor)floor).setDoorOnTile(true);
			break;
		}
		// Act
		boolean actual = false;
		
		switch (arg) {
		case "Player":
			actual = ((Floor)floor).isPlayerOnTile();
			break;
		case "Monster":
			actual = ((Floor)floor).isMonsterOnTile();
			break;
		case "Treasure":
			actual = ((Floor)floor).isTreasureOnTile();
			break;
		case "Door":
			actual = ((Floor)floor).isDoorOnTile();
			break;
		}
		// Assert
		assertTrue(actual);

	}

}
