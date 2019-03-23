import game.Direction;
import game.Gameboard;
import game.Player;

import static game.TileType.*;
import static game.Direction.*;

import static org.junit.Assert.*;

import org.junit.Ignore;

import game.Point;
import game.TileType;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class GameLogicTests {

	@Test
	public void testCreateGameBoard() {
		// Arrange
		Gameboard board = new Gameboard();

		// Act
		Point actual = board.getPoint(5, 15);

		// Assert
		assertEquals(FLOOR, actual.getTileType());
	}

	@Test
	public void SetTheBoardsOuterEdgesToWalls() {
		// Arrange
		Gameboard board = new Gameboard();

		// Act
		Point wallPoint = board.getPoint(0, 0);
		Point floorPoint = board.getPoint(5, 15);

		// Assert
		assertEquals(WALL, wallPoint.getTileType());
		assertEquals(FLOOR, floorPoint.getTileType());
	}

	@Test
	public void addDoorToGameBoard_PositionedRight_GetInt4ReturnedFromPoint() {
		// Arrange
		Gameboard board = new Gameboard();

		// Act
		Point doorPoint = board.getPoint(9, 18);

		// Assert
		assertEquals(DOOR, doorPoint.getTileType());
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

	@Parameters({ "UP, true", "RIGHT, true", "DOWN, true", "LEFT, false" })
	@Test
	public void moveCharacterIntoWall_GettingFalseIfThereWasACollideAndMovementWasNotPossible(Direction direction,
			boolean expected) {
		// Arrange
		Gameboard board = new Gameboard();

		// Act
		boolean actual = board.moveCharacter(direction);

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void moveCharacterTwoSteps_ShouldReturnTrueSinceTheresIsntAWall() {
		// Arrange
		Gameboard board = new Gameboard();

		// Act
		board.moveCharacter(RIGHT);
		boolean actual = board.moveCharacter(RIGHT);

		// Assert
		assertTrue(actual);
	}

	@Test
	public void moveCharacterMultipleSteps_OnLevelONE_GettingFalseSinceItIsHittingAWall() {
		// Arrange
		Gameboard board = new Gameboard();

		// Act
		board.moveCharacter(RIGHT);
		board.moveCharacter(RIGHT);
		board.moveCharacter(RIGHT);
		board.moveCharacter(RIGHT);
		board.moveCharacter(RIGHT);
		board.moveCharacter(UP);
		board.moveCharacter(UP);
		board.moveCharacter(UP);
		boolean actual = board.moveCharacter(UP);

		// Assert
		assertFalse(actual);
	}

	@Test
	public void checkIfMonsterIsAddedToTheBoard_ReturnsThe2dArrayWhichIsLoopedThroughToFindMONSTER() {
		// Arrange
		Gameboard board = new Gameboard();
		board.getPoint(10, 10).setTileType(MONSTER);

		// Act
		TileType actual = board.getPoint(10, 10).getTileType();

		// Assert
		assertEquals(MONSTER, actual);
	}

	@Ignore
	@Test
	public void characterCollisionsWithMonster_BooleanIsAliveSetsToFalseAndPlayerDead() {
		// Arrange
		Gameboard board = new Gameboard();
		board.getPoint(9, 3).setTileType(MONSTER);

		// Act
		board.moveCharacter(RIGHT);
		board.moveCharacter(RIGHT);
		boolean actual = board.getPlayer().getAlive();

		// Assert
		assertFalse(actual);
	}

	@Test
	public void testPlayerWalkOntoTreasureAndCollectIt() {
		// Arrange
		Gameboard board = new Gameboard();
		Point treasure = board.getPoint(9, 2);

		// Create a treasure at 9,2
		treasure.setTileType(TREASURE);

		// Act
		// Move onto the treasure which should automatically collect it
		board.moveCharacter(RIGHT);

		// How many treasures do we expect the player to collect
		int actual = 1;

		// Assert
		assertEquals(actual, board.getPlayer().getTreasure());
	}

	@Test
	public void testPlayerWalkingOntoDoorWithTreasures() {
		// Arrange
		Gameboard board = new Gameboard();

		// Act
		// Move player onto door
		for (int i = 1; i < 18; i++) {
			board.moveCharacter(Direction.RIGHT);
		}

		// Assert
		assertTrue(board.isFinished());
	}

	@Test
	public void testPersistanceOfDoorWhenPlayerMovesOverIT() {
		// Arrange
		Gameboard board = new Gameboard();
		Point door = board.getPoint(9, 2);

		door.setTileType(DOOR);

		// Act
		// Move onto the Door
		board.moveCharacter(RIGHT);

		// Move away from the door
		board.moveCharacter(RIGHT);

		boolean isPointStillDoor = false;
		if (door.getTileType() == DOOR)
			isPointStillDoor = true;

		// Assert
		assertTrue(isPointStillDoor);

	}

	@Test
	public void testPlayerWalkingOntoMonsterResultsInGameOver() {
		// Arrange
		Gameboard g = new Gameboard();

		// Act
		g.moveCharacter(Direction.RIGHT);
		g.moveCharacter(Direction.RIGHT);
		g.moveCharacter(Direction.DOWN);

		// Assert
		assertFalse(g.getPlayer().getAlive()); // My first guess
	}
}
