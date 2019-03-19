import game.Direction;
import game.Gameboard;

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
        assertEquals(TileType.FLOOR, actual.getTileType());
    }

    @Test
    public void SetTheBoardsOuterEdgesToWalls() {
        // Arrange
        Gameboard board = new Gameboard();

        // Act
        Point wallPoint = board.getPoint(0, 0);
        Point floorPoint = board.getPoint(5, 15);

        // Assert
        assertEquals(TileType.WALL, wallPoint.getTileType());
        assertEquals(TileType.FLOOR, floorPoint.getTileType());
    }

    @Test
    public void addDoorToGameBoard_PositionedRight_GetInt4ReturnedFromPoint() {
        // Arrange
        Gameboard board = new Gameboard();

        // Act
        Point doorPoint = board.getPoint(9, 18);

        // Assert
        assertEquals(TileType.DOOR, doorPoint.getTileType());
    }

    @Test
    @Parameters({
            "9,2,RIGHT,CHARACTER",
            "8,1,UP,CHARACTER",
            "9,0,LEFT,WALL",
            "10,1,DOWN,CHARACTER"
    })
    public void moveCharacterOnePositionAccordingToDirection_GetIntWithResultMovement(int y, int x, Direction direction, TileType expected) {
        // Arrange
        Gameboard board = new Gameboard();
        board.moveCharacter(direction);

        // Act
        Point actual = board.getPoint(y, x);

        // Assert
        assertEquals(expected, actual.getTileType());
    }

    @Parameters({
            "0, 1, true", // Wall
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

    @Parameters({
            "UP, true",
            "RIGHT, true",
            "DOWN, true",
            "LEFT, false"
    })
    @Test
    public void moveCharacterIntoWall_GettingFalseIfThereWasACollideAndMovementWasNotPossible(Direction direction, boolean expected) {
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
        board.moveCharacter(Direction.RIGHT);
        boolean actual = board.moveCharacter(Direction.RIGHT);

        // Assert
        assertEquals(true, actual);
    }

    @Test
    public void moveCharacterMultipleSteps_GettingFalseSinceItIsHittingAWall() {
        // Arrange
        Gameboard board = new Gameboard();

        // Act
        board.moveCharacter(Direction.RIGHT);
        board.moveCharacter(Direction.DOWN);
        board.moveCharacter(Direction.DOWN);
        board.moveCharacter(Direction.DOWN);
        board.moveCharacter(Direction.DOWN);
        board.moveCharacter(Direction.DOWN);
        board.moveCharacter(Direction.DOWN);
        board.moveCharacter(Direction.DOWN);
        boolean actual = board.moveCharacter(Direction.DOWN);

        // Assert
        assertEquals(false, actual);
    }
}