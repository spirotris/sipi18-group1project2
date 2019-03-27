import game.*;

import static game.Direction.*;
import static org.junit.Assert.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class GameLogicTests {

	@Test
	public void testCreateGameBoard_LevelOneRequired_ClassReturned() {
		// Arrange
		Gameboard board = new Gameboard();

		// Act
		Point actual = board.getPoint(1, 1);

		// Assert
		assertEquals(Floor.class, actual.getClass());
	}

	@Test
	public void SetTheBoardsOuterEdgesToWalls_LevelOneRequired_ClassReturned() {
		// Arrange
		Gameboard board = new Gameboard();

		// Act
		Point wallPoint = board.getPoint(0, 0);
		Point floorPoint = board.getPoint(5, 14);

		// Assert
		assertEquals(Wall.class, wallPoint.getClass());
		assertEquals(Floor.class, floorPoint.getClass());
	}

	@Test
	public void addDoorToGameBoard_PositionedRight_GettingBooleanTrueFromhasDoorOnTile() {
		// Arrange
		Gameboard board = new Gameboard();

		// Act
		Point doorPoint = board.getPoint(9, 18);

		// Assert
		assertTrue(((Floor)doorPoint).isDoorOnTile());
	}

	@Test
	@Parameters({ "9,2,RIGHT,CHARACTER", "8,1,UP,CHARACTER", "9,0,LEFT,WALL", "10,1,DOWN,CHARACTER" })
	public void moveCharacterOnePositionAccordingToDirection_GetIntWithResultMovement(int y, int x, Direction direction,
			TileType expected) {
		// Arrange
		Gameboard board = new Gameboard();
		board.moveCharacter(direction);

		// Act
		Point actual = board.getPoint(y, x);

		// Assert
		assertEquals(expected, actual.getTileType());
	}

	@Parameters({ "0, 1, true", // Wall
			"1, 10, false", // Character
			"5, 5, false" // Floor
	})
	@Test
	public void gettingAPointToCheckWhatsThere_GetBooleanFalseIfThereIsAWall(int y, int x, boolean expected) {
		// Arrange
		Gameboard board = new Gameboard();

		// Act
		boolean actual = board.onCollision(board.getPoint(x, y));

		// Assert
		assertEquals(expected, actual);
	}

	@Parameters({ "UP, false", "RIGHT, false", "DOWN, false", "LEFT, true" })
	@Test
	public void moveCharacterIntoWall_CheckingIfCharacterIsOnSamePoint_GettingTrueIfThereWasACollideAndMovementWasNotPossible(Direction direction,
			boolean expected) {
		// Arrange
		Gameboard board = new Gameboard();
		Point character = board.getPoint(9,1);

		// Act
		board.moveCharacter(direction);

		// Assert
		assertEquals(expected,((Floor)character).isPlayerOnTile());
	}

	@Test
	public void moveCharacterTwoSteps_ShouldReturnTrueSinceTheresIsntAWall() {
		// Arrange
		Gameboard board = new Gameboard();

		// Act
		board.moveCharacter(RIGHT);
		board.moveCharacter(RIGHT);
		Point character = board.getPoint(9,3);

		// Assert
		assertTrue(((Floor) character).isPlayerOnTile());
	}

	@Test
	public void moveCharacterMultipleSteps_levelOneRequired_thePointReturnedShouldReturnTrueFromisPlayerOnTileSinceLastMoveStopsMovementOnWall() {
		// Arrange
		Gameboard board = new Gameboard();
		int yPos = 0;
		int xPos = 0;

		// Act
		board.moveCharacter(RIGHT);
		xPos++;
		board.moveCharacter(RIGHT);
		xPos++;
		board.moveCharacter(RIGHT);
		xPos++;
		board.moveCharacter(RIGHT);
		xPos++;
		board.moveCharacter(RIGHT);
		xPos++;
		board.moveCharacter(UP);
		yPos++;
		board.moveCharacter(UP);
		yPos++;
		board.moveCharacter(UP);
		yPos++;
		board.moveCharacter(UP);
		yPos++;
		Point character = board.getPoint(yPos,xPos);

		// Assert
		assertFalse(((Floor)character).isPlayerOnTile());
	}

	@Test
	public void addingAMonsterToBoard_booleanisMonsterOnTileShoulReturnTrue() {
		// Arrange
		Gameboard board = new Gameboard();
		Point monster = board.getPoint(10, 10);

		// Act
		((Floor) monster).setMonsterOnTile(true);


		// Assert
		assertTrue(((Floor) monster).isMonsterOnTile());
	}	

	@Test
	public void testPlayerWalkOntoTreasureAndCollectIt() {
		// Arrange
		Gameboard board = new Gameboard();
		Point treasure = board.getPoint(9, 2);

		// Create a treasure at 9,2
		((Floor) treasure).setTreasureOnTile(true);

		// Act
		// Move onto the treasure which should automatically collect it
		board.moveCharacter(RIGHT);

		// How many treasures do we expect the player to collect
		int actual = 1;

		// Assert
		assertEquals(actual, board.getPlayer().getTreasure());
	}

	@Test
	public void testPlayerWalkingOntoDoorWithTreasures_LevelOneRequired_PlayerisFinishedShouldReturnTrue() {
		// Arrange
		Gameboard board = new Gameboard();
		Point treasure = board.getPoint(9,15);

		// Act
		((Floor) treasure).setTreasureOnTile(true);
		for (int i = 1; i < 18; i++) {
			board.moveCharacter(Direction.RIGHT);
		}

		// Assert
		assertTrue(board.isFinished());
	}

	@Test
	@Parameters(method = "parametersFortestPersistanceOfDoorWhenPlayerMovesOverIT")
	public void testPersistanceOfDoorWhenPlayerMovesOverIT(Direction[] direction) {
		// Arrange
		Gameboard board = new Gameboard();
		Point door = board.getPoint(9, 2);
		((Floor) door).setDoorOnTile(true);

		// Act
		for (int i = 0; i < direction.length; i++) {
			board.moveCharacter(direction[i]);
		}

		boolean isPointStillDoor = ((Floor) door).isDoorOnTile();
		// Assert
		assertTrue(isPointStillDoor);
	}
	
	@SuppressWarnings({"unused"})
	private Object[] parametersFortestPersistanceOfDoorWhenPlayerMovesOverIT() {
		return new Object[] {
		new Direction[] {RIGHT, RIGHT},
		new Direction[] {UP,RIGHT,DOWN,DOWN},
		new Direction[] {DOWN, RIGHT, UP, UP},
		new Direction[] {DOWN, RIGHT, RIGHT, UP, LEFT, LEFT}
		};
	}

	@Test
	public void testPlayerWalkingOntoMonsterResultsInGameOver() {
		// Arrange
		Gameboard g = new Gameboard();
		Point monster = g.getPoint(10,3);
		((Floor) monster).setMonsterOnTile(true);

		// Act
		g.moveCharacter(Direction.RIGHT);
		g.moveCharacter(Direction.RIGHT);
		g.moveCharacter(Direction.DOWN);

		// Assert
		assertFalse(g.getPlayer().getAlive());
	}
	
	
}
