import game.*;

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

        // Act
        boolean actual = (board[y][x].isHasMonsterOnTile());

        // Assert
        assertEquals(expected, actual);
    }
    @Test
    public void updateMonstersPosition_GetNewBoard_ChecksPositionWithOldBoard_BooleanFalseExpected() {
        // Arrange
        Levels level = new Levels(1);
        Point[][] firstBoard = level.getBoard();
        Point[][] updatedBoard;
        Point monsterFirstBoard = null;

        // Act
        //level.monsterDelegator(true);
        updatedBoard = level.getBoard();
        boolean actual;

        for (int y = 0; y < firstBoard.length; y++) {
            for (int x = 0; x < firstBoard.length; x++) {
                if (firstBoard[y][x].getTileType() == TileType.MONSTER && monsterFirstBoard == null) {
                    monsterFirstBoard = firstBoard[y][x];
                }
            }
        }
        if(monsterFirstBoard == null) {
             actual = true;
        }  else if(updatedBoard[monsterFirstBoard.getY()][monsterFirstBoard.getX()].getTileType() == monsterFirstBoard.getTileType()) {
            actual = false;
        } else {
            actual = true;
        }

        // Assert
        assertFalse(actual);
    }

    @Test
    public void timerTest_checksWhetherAMonsterMovesAfterDesiredTime_BooleanFalseExpected() {
        // Arrange
        Levels level = new Levels(1);
        Point[][] firstBoard = level.getBoard();
        Point[][] updatedBoard;
        Point monsterFirstBoard = null;

        // Act
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updatedBoard = level.getBoard();
        boolean actual;

        for (int y = 0; y < firstBoard.length; y++) {
            for (int x = 0; x < firstBoard.length; x++) {
                if (firstBoard[y][x].getTileType() == TileType.MONSTER && monsterFirstBoard == null) {
                    monsterFirstBoard = firstBoard[y][x];
                }
            }
        }
        if(monsterFirstBoard == null) {
            actual = true;
        }  else if(updatedBoard[monsterFirstBoard.getY()][monsterFirstBoard.getX()].getTileType() == monsterFirstBoard.getTileType()) {
            actual = false;
        } else {
            actual = true;
        }

        // Assert
        assertFalse(actual);
    }
}
