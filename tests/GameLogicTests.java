import game.Direction;
import game.Gameboard;

import static org.junit.Assert.*;

import game.Point;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class GameLogicTests {

    @Test
    public void testCreateGameBoard() {
        // Arrange
        int width = 20;
        int height = 20;
        Gameboard board = new Gameboard(width, height);

        // Act
        Point actual = board.getPoint(15, 5);

        // Assert
        assertEquals(0, actual.getStatus());
    }

    @Test
    public void SetTheBoardsOuterEdgesToWalls() {
        // Arrange
        int width = 20;
        int height = 20;
        Gameboard board = new Gameboard(width, height);

        // Act
        Point wallPoint = board.getPoint(0, 0);
        Point floorPoint = board.getPoint(15, 5);

        // Assert
        assertEquals(1, wallPoint.getStatus());
        assertEquals(0, floorPoint.getStatus());
    }

    @Test
    public void addDoorToGameBoard_PositionedRight_GetInt4ReturnedFromPoint() {
        // Arrange
        Gameboard board = new Gameboard(20,20);

        // Act
        Point doorPoint = board.getPoint(18,10);

        // Assert
        assertEquals(4, doorPoint.getStatus());
    }

    @Parameters({
            "20,20,10",
            "25,25,13",
            "10,10,5"
    })
    @Test
    public void addCharacterToGameBoard_PositionedLeftAsCloseTooMiddleAsPossible_GetInt2ReturnedFromPoint(int width, int height, int yPosition) {
        // Arrange
        Gameboard board = new Gameboard(width,height);

        // Act
        Point characterPoint = board.getPoint(1,yPosition);

        // Assert
        assertEquals(2, characterPoint.getStatus());
    }

    @Test
    @Parameters({
            "2,10,RIGHT,2",
            "1,9,UP,2",
            "0,10,LEFT,1",
            "1,11,DOWN,2"
    })
    public void moveCharacterOnePositionAccordingToDirection_GetIntWithResultMovement(int x, int y, Direction direction, int expected) {
        // Arrange
        Gameboard board = new Gameboard(20,20);
        board.moveCharacter(direction);

        // Act
        Point actual = board.getPoint(x,y);

        // Assert
        assertEquals(expected, actual.getStatus());
    }

    @Parameters({
            "0, 1, true", // Wall
            "1, 10, false", // Character
            "5, 5, false" // Floor
    })
    @Test
    public void gettingAPointToCheckWhatsThere_GetBooleanFalseIfThereIsAWall(int x, int y, boolean expected) {
        // Arrange
        Gameboard board = new Gameboard(20,20);

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
        Gameboard board = new Gameboard(20,20);

        // Act
        boolean actual = board.moveCharacter(direction);

        // Assert
        assertEquals(actual, expected);
    }
}