import game.GameBoard;
import game.Point;
import game.Wall;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertSame;

@RunWith(JUnitParamsRunner.class)
public class GameBoardTests {
    @Parameters({
            "1,2,6",
            "2,17,6",
            "3,14,16"
    })
    @Test
    public void setWallLevelOne(int levelParam, int y, int x) {
        // Arrange
        GameBoard gb = new GameBoard(levelParam);
        Point[][] board = GameBoard.getBoard();

        // Act
        Point wall = board[y][x];

        // Assert
        assertSame(wall.getClass(), Wall.class);
    }
}
