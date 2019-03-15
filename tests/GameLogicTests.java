import game.Direction;
import game.Gameboard;

import static org.junit.Assert.*;

import game.Point;
import org.junit.Test;

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
    public void addCharacterToGameBoard_GetInt2ReturnedFromPoint() {
        // Arrange
        Gameboard board = new Gameboard(20,20);

        // Act
        Point characterPoint = board.getPoint(1,10);

        // Assert
        assertEquals(2, characterPoint.getStatus());
    }

    @Test
    public void moveCharacterOnePositionToTheRight_GetInt2ReturnedFromNewPoint() {
        // Arrange
        Gameboard board = new Gameboard(20,20);
        board.moveCharacter(Direction.RIGHT);

        // Act
        Point characterPoint = board.getPoint(2,10);

        // Assert
        assertEquals(2, characterPoint.getStatus());
    }

    @Test
    public void moveCharacterOnePositionDown_GetInt2ReturnedFromNewPoint() {
        // Arrange
        Gameboard board = new Gameboard(20,20);
        board.moveCharacter(Direction.UP);

        // Act
        Point characterPoint = board.getPoint(1,11);

        // Assert
        assertEquals(2, characterPoint.getStatus());
    }
}