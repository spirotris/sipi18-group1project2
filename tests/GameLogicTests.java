import game.Gameboard;

import static org.junit.Assert.*;
import org.junit.Test;

public class GameLogicTests {

    @Test
    public void testCreateGameBoard() {
        // Arrange
        int width = 20;
        int height = 20;
        Gameboard board = new Gameboard(width, height);

        // Act
        int point = board.getPoint(15, 5);

        // Assert
        assertEquals(0, point);
    }
}