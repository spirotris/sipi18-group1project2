import game.Levels;
import game.Monster;
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
            "2, 17, 5, WALL",
            "3, 10, 4, WALL"
    })
    @Test
    public void setLevelOne_Get2dArrayInReturn(int levelParam, int pointY, int pointX, TileType tileType) {
        // Arrange
        Levels level = new Levels(levelParam);
        Point[][] board = level.getBoard();

        // Act
        TileType actual = board[pointY][pointX].getTileType();

        // Assert
        assertEquals(tileType, actual);
    }

    @Parameters ({
            "1, 3, 10, true",
            "1, 4, 10, false",
            "2, 9, 15, true",
            "2, 9, 1, false",
            "3, 15, 3, true",
            "3, 9, 4, true",
            "3, 3, 16, false"
    })
    @Test
    public void addMonstersToLevels_ChecksIfTheyAreActive_ExpectTrue(int levelParam, int y, int x, boolean expected) {
        // Arrange
        Levels level = new Levels(levelParam);
        Point[][] board = level.getBoard();
        boolean actual;

        // Act
        if(board[y][x].getClass() == Monster.class) {
            Monster m = (Monster)board[y][x];
            actual = m.isActive();
        } else {
            actual = false;
        }

        // Assert
        assertEquals(expected, actual);
    }
}
