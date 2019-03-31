import game.*;

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
    public void setLevelOne_getTheBoardInReturn_makeShureTheInnerWallsWereSet(int levelParam, int pointY, int pointX) {
        // Arrange
        GameBoard gb = new GameBoard(levelParam);
        Point[][] board = GameBoard.getBoard();

        // Act
        Point actual = board[pointY][pointX];

        // Assert
        assertEquals(Wall.class, actual.getClass());
    }

    @Parameters ({
            "1, 3, 10, true",
            "1, 4, 10, false",
            "2, 9, 15, true",
            "2, 9, 1, false",
            "3, 15, 3, true",
            "3, 9, 4, true"
    })
    @Test
    public void addMonstersToLevels_ChecksIfTheyArePlacedOnBoard_ExpectBoolean(int levelParam, int y, int x, boolean expected) {
        // Arrange
        GameBoard gb = new GameBoard(levelParam);
        Point[][] board = GameBoard.getBoard();

        // Act
        boolean actual = ((Floor) board[y][x]).isMonsterOnTile();

        // Assert
        assertEquals(expected, actual);
    }
    @Test
    public void updateMonstersPosition_GetNewBoard_ChecksPositionWithOldBoard_BooleanFalseExpected() {
        // Arrange
        GameBoard gb = new GameBoard(1);
        GameEngine ge = new GameEngine();
        Point[][] firstBoard = GameBoard.getBoard();
        Point[][] updatedBoard;
        int firstMonsterPosY = 0;
        int firstMonsterPosX = 0;

        // Act
        Mover.move();
        updatedBoard = GameBoard.getBoard();

        for (int y = 0; y < firstBoard.length; y++) {
            for (int x = 0; x < firstBoard.length; x++) {
                if (firstBoard[y][x].getClass() == Floor.class && ((Floor)firstBoard[y][x]).isMonsterOnTile()) {
                    firstMonsterPosY = y;
                    firstMonsterPosX = x;
                    ((Floor)updatedBoard[y][x]).setMonsterOnTile(false);
                    ((Floor)updatedBoard[y-1][x]).setMonsterOnTile(true);
                }
            }
        }

        // Assert
        assertFalse((updatedBoard[firstMonsterPosY][firstMonsterPosX].getClass() == Floor.class && ((Floor)updatedBoard[firstMonsterPosY][firstMonsterPosX]).isMonsterOnTile()));
    }

    @Test
    public void timerTest_checksWhetherAMonsterMovesAfterDesiredTime_BooleanFalseExpected() {
        // Arrange
        GameBoard gb = new GameBoard(1);
        Point[][] firstBoard = GameBoard.getBoard();
        Point[][] updatedBoard;

        int firstMonsterPosY = 0;
        int firstMonsterPosX = 0;

        // Act
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updatedBoard = GameBoard.getBoard();
        for (int y = 0; y < firstBoard.length; y++) {
            for (int x = 0; x < firstBoard.length; x++) {
                if (firstBoard[y][x].getClass() == Floor.class && ((Floor)firstBoard[y][x]).isMonsterOnTile()) {
                    firstMonsterPosY = y;
                    firstMonsterPosX = x;
                    ((Floor)updatedBoard[y][x]).setMonsterOnTile(false);
                    ((Floor)updatedBoard[y-1][x]).setMonsterOnTile(true);
                }
            }
        }

        // Assert
        assertFalse((updatedBoard[firstMonsterPosY][firstMonsterPosX].getClass() == Floor.class && ((Floor)updatedBoard[firstMonsterPosY][firstMonsterPosX]).isMonsterOnTile()));
    }
}
