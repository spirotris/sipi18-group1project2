import game.Levels;
import game.Point;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class LevelsTests {
    @Parameters ({
            "1, 3, 6",
            "2, 17, 5"
    })
    @Test
    public void setLevelOne_Get2dArrayInReturn(int levelParam, int pointY, int pointX) {
        // Arrange
        Levels level = new Levels(levelParam);

        // Act
        Point[][] board = level.getBoard();

        int actual = board[pointY][pointX].getStatus();

        // Assert
        assertEquals(1, actual);
    }
}
