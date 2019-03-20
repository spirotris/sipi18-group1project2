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
            "1, 3, 6, WALL",
            "2, 17, 5, WALL"
    })
    @Test
    public void setLevelOne_Get2dArrayInReturn(int levelParam, int pointY, int pointX, TileType tileType) {
        // Arrange
        Levels level = new Levels(levelParam);

        // Act
        Point[][] board = level.getBoard();

        TileType actual = board[pointY][pointX].getTileType();

        // Assert
        assertEquals(tileType, actual);
    }
}
