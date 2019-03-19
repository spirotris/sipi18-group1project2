import game.Levels;
import game.Point;
import game.TileType;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class LevelsTests {
    @Parameters ({
            "1, 3, 6",
            "2, 17, 5",
            "3, 10, 4"
    })
    @Test
    public void setLevelOne_Get2dArrayInReturn(int levelParam, int pointY, int pointX) {
        // Arrange
        Levels level = new Levels(levelParam);

        // Act
        Point[][] board = level.getBoard();

        TileType actual = board[pointY][pointX].getTileType();

        // Assert
        assertEquals(TileType.WALL, actual);
    }
}
