import game.Direction;
import game.Gameboard;
import static game.TileType.*;
import static game.Direction.*;

import static org.junit.Assert.*;

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
	@Parameters({ "1" })
	public void testPlayerWalkOntoTreasureAndCollectIt(int nrOfFoundTreasures) {
		// Arrange
		Gameboard g = new Gameboard();
		Point po, treasure;

		// Act
		// Move the player onto point 0,0
		po = g.getPoint(0, 0);
		po.setTileType(CHARACTER);
		g.getPlayer().movePlayer(po);

		// Create a treasure at 0,1
		treasure = g.getPoint(0, 1);
		treasure.setTileType(TREASURE);
		int actual = 0;
		// Move player onto treasure
		if (g.moveCharacter(Direction.DOWN)
				&& g.getPoint(po.getX(), po.getY() + Direction.DOWN.getValue()).getTileType() == TileType.TREASURE) {
			g.getPlayer().setTreasure(nrOfFoundTreasures);
			actual = g.getPlayer().getTreasure();
		}

		// Assert
		assertEquals(actual, nrOfFoundTreasures);
	}

	@Test
	public void testPlayerWalkingOntoDoorWithTreasures() {
		// Arrange
		Gameboard g = new Gameboard();

		// Act
		// Move player onto door
		for (int i = 1; i < 18; i++) {
			g.moveCharacter(Direction.RIGHT);
		}

		// Assert
		assertTrue(g.isFinished());
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
