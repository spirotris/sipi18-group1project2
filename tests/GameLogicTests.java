import game.*;

import static game.Direction.*;
import static org.junit.Assert.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class GameLogicTests {
	GameEngine ge;

	@Before
	public void setup() {
		 ge = new GameEngine();
	}

	@Test
	public void testCreateGameBoard_LevelOneRequired_ClassReturned() {
		// Arrange

		// Act
		Point actual = ge.getPoint(1, 1);

		// Assert
		assertEquals(Floor.class, actual.getClass());
	}

	@Test
	public void SetTheBoardsOuterEdgesToWalls_LevelOneRequired_ClassReturned() {
		// Arrange

		// Act
		Point wallPoint = ge.getPoint(0, 0);
		Point floorPoint = ge.getPoint(5, 14);

		// Assert
		assertEquals(Wall.class, wallPoint.getClass());
		assertEquals(Floor.class, floorPoint.getClass());
	}

	@Test
	public void addDoorToGameBoard_PositionedRight_GettingBooleanTrueFromhasDoorOnTile() {
		// Arrange

		// Act
		Point doorPoint = ge.getPoint(9, 18);

		// Assert
		assertTrue(((Floor)doorPoint).isDoorOnTile());
	}

	@Test
	@Parameters({ "9,2,RIGHT", "8,1,UP", "9,0,LEFT", "10,1,DOWN" })
	public void moveCharacterOnePositionAccordingToDirection_checksWhatsOnTheActualPoint(int y, int x, Direction direction) {
		// Arrange
		Point[][] board = GameBoard.getBoard();
		Mover.move(direction);

		// Act
		Point actual = board[y][x];

		// Assert
		if(actual.getClass() == Floor.class) {
			assertTrue(((Floor)actual).isPlayerOnTile());
		} else {
			assertSame(actual.getClass(), Wall.class);
		}
	}

	@Parameters({
			"0, 1, true", // Wall
			"9, 1, false", // Character
			"5, 5, false" // Floor
	})
	@Test
	public void gettingAPointToCheckWhatsThere_GetBooleanTrueIfThereIsAWall(int y, int x, boolean expected) {
		// Arrange
		Point[][] board = GameBoard.getBoard();

		// Act
		boolean actual = board[y][x].getClass() == Wall.class;

		// Assert
		assertEquals(expected, actual);
	}

	@Parameters({ "UP, false", "RIGHT, false", "DOWN, false", "LEFT, true" })
	@Test
	public void moveCharacterIntoWall_CheckingIfCharacterIsOnSamePoint_GettingTrueIfThereWasACollideAndMovementWasNotPossible(Direction direction,
			boolean expected) {
		// Arrange
		Point character = ge.getPoint(9,1);

		// Act
		Mover.move(direction);

		// Assert
		assertEquals(expected,((Floor)character).isPlayerOnTile());
	}

	@Test
	public void moveCharacterTwoSteps_ShouldReturnTrueSinceTheresIsntAWall() {
		// Arrange

		// Act
		Mover.move(RIGHT);
		Mover.move(RIGHT);
		Point character = ge.getPoint(9,3);

		// Assert
		assertTrue(((Floor) character).isPlayerOnTile());
	}

	@Test
	public void moveCharacterMultipleSteps_levelOneRequired_thePointReturnedShouldReturnTrueFromisPlayerOnTileSinceLastMoveStopsMovementOnWall() {
		// Arrange
		int yPos = 0;
		int xPos = 0;

		// Act
		Mover.move(RIGHT);
		xPos++;
		Mover.move(RIGHT);
		xPos++;
		Mover.move(RIGHT);
		xPos++;
		Mover.move(RIGHT);
		xPos++;
		Mover.move(RIGHT);
		xPos++;
		Mover.move(UP);
		yPos++;
		Mover.move(UP);
		yPos++;
		Mover.move(UP);
		yPos++;
		Mover.move(UP);
		yPos++;
		Point character = ge.getPoint(yPos,xPos);

		// Assert
		assertFalse(((Floor)character).isPlayerOnTile());
	}

	@Test
	public void addingAMonsterToBoard_booleanisMonsterOnTileShoulReturnTrue() {
		// Arrange
		Point monster = ge.getPoint(10, 10);

		// Act
		((Floor) monster).setMonsterOnTile(true);


		// Assert
		assertTrue(((Floor) monster).isMonsterOnTile());
	}

	@Test
	public void testPlayerWalkOntoTreasureAndCollectIt() {
		// Arrange
		Point treasure = ge.getPoint(9, 2);
		int expected = 1;

		// Create a treasure at 9,2
		((Floor) treasure).setTreasureOnTile(true);

		// Act
		Mover.move(RIGHT);

		// Assert
		assertEquals(expected, Player.getTreasure());
	}

	@Test
	public void testPlayerWalkingOntoDoorWithTreasures_LevelOneRequired_PlayerisFinishedShouldReturnTrue() {
		// Arrange
		Point treasure = ge.getPoint(9,15);

		// Act
		((Floor) treasure).setTreasureOnTile(true);
		for (int i = 1; i < 18; i++) {
			Mover.move(Direction.RIGHT);
		}

		// Assert
		assertTrue(Player.isFinished);
	}

	@Test
	@Parameters(method = "parametersFortestPersistanceOfDoorWhenPlayerMovesOverIT")
	public void testPersistanceOfDoorWhenPlayerMovesOverIT(Direction[] direction) {
		// Arrange
		Point door = ge.getPoint(9, 2);
		((Floor) door).setDoorOnTile(true);

		// Act
		for (int i = 0; i < direction.length; i++) {
			Mover.move(direction[i]);
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
		Point monster = ge.getPoint(10,3);
		((Floor) monster).setMonsterOnTile(true);

		// Act
		Mover.move(Direction.RIGHT);
		Mover.move(Direction.RIGHT);
		Mover.move(Direction.DOWN);

		// Assert
		assertFalse(Player.isAlive);
	}

	@Test
	public void tryingToResetTheGame_ShouldGetBooleanFalseFromisFinished() {
		// Arrange
		GameEngine ge = new GameEngine();
		Player.isAlive = false;

		// Act
		ge.resetGame();

		// Assert
		assertTrue(Player.isAlive);
	}

	@Test
	public void tryingToSetTheGameToFinished_ShouldReturnTrueFromisFinished() {
		// Arrange
		GameEngine ge = new GameEngine();
		Point[][] board = GameBoard.getBoard();
		Player.setY(9);
		Player.setX(18);
		((Floor)board[9][18]).setPlayerOnTile(true);
		Player.addTreasure();
		Player.addTreasure();

		// Act
		ge.isFinished();

		// Assert
		assertTrue(Player.isFinished);

	}
}
