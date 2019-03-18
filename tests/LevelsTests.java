import game.Levels;
import game.Point;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class LevelsTests {
    @Parameters ({
            "1, 3, 6",
            "2, 2, 6",
            "3, 2, 4",
            "4, 17, 19"
    })
    @Test
    public void setLevelOne_Get2dArrayInReturn(int levelParam, int pointX, int pointY) {
        // Arrange
        Levels level = new Levels();

        // Act
        Point[][] board = level.getLevel(levelParam);
        System.out.println(Arrays.deepToString(board).replace("[", "{").replace("], ", "}\n"));

        int actual = board[pointX][pointY].getStatus();

        // Assert
        assertEquals(1, actual);
    }
}
