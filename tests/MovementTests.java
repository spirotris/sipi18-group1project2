import game.*;
import org.junit.Test;

import static game.Direction.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MovementTests {
    @Test
    public void moveCharacterToTheRight_checksIfCharacterIsOnPoint() {
        // Arrange
        GameEngine ge = new GameEngine();
        Point[][] board = GameBoard.getBoard();

        int positionY = 0;
        int positionX = 0;
        for (int y = 1; y < board.length -1; y++) {
            for (int x = 1; x < board.length -1; x++) {
                if(board[y][x].getClass() == Floor.class) {
                    if(((Floor)board[y][x]).isPlayerOnTile()){
                        positionY = y;
                        positionX = x;
                    }
                }
            }
        }

        //
        Mover.move(RIGHT);

        // Assert
        Point[][] newBoard = GameBoard.getBoard();
        assertTrue(((Floor)newBoard[positionY][positionX + 1]).isPlayerOnTile());
    }

    @Test
    public void moveCharacterTwoStepsToTheRight_checksIfCharacterIsOnPoint() {
        // Arrange
        GameEngine ge = new GameEngine();
        Point[][] board = GameBoard.getBoard();

        int positionY = 0;
        int positionX = 0;
        for (int y = 1; y < board.length -1; y++) {
            for (int x = 1; x < board.length -1; x++) {
                if(board[y][x].getClass() == Floor.class) {
                    if(((Floor)board[y][x]).isPlayerOnTile()){
                        positionY = y;
                        positionX = x;
                    }
                }
            }
        }

        // Act
        Mover.move(RIGHT);
        Mover.move(RIGHT);

        // Assert
        Point[][] newBoard = GameBoard.getBoard();
        assertTrue(((Floor)newBoard[positionY][positionX + 2]).isPlayerOnTile());
    }

    @Test
    public void moveCharacterOneStepUp_checksIfCharacterIsOnPoint() {
        // Arrange
        GameEngine ge = new GameEngine();
        Point[][] board = GameBoard.getBoard();

        int positionY = 0;
        int positionX = 0;
        for (int y = 1; y < board.length -1; y++) {
            for (int x = 1; x < board.length -1; x++) {
                if(board[y][x].getClass() == Floor.class) {
                    if(((Floor)board[y][x]).isPlayerOnTile()){
                        positionY = y;
                        positionX = x;
                    }
                }
            }
        }

        // Act
        Mover.move(UP);

        // Assert
        Point[][] newBoard = GameBoard.getBoard();
        assertTrue(((Floor)newBoard[positionY - 1][positionX]).isPlayerOnTile());
    }

    @Test
    public void moveCharacterTwoStepsUp_checksIfCharacterIsOnPoint() {
        // Arrange
        GameEngine ge = new GameEngine();
        Point[][] board = GameBoard.getBoard();

        int positionY = 0;
        int positionX = 0;
        for (int y = 1; y < board.length -1; y++) {
            for (int x = 1; x < board.length -1; x++) {
                if(board[y][x].getClass() == Floor.class) {
                    if(((Floor)board[y][x]).isPlayerOnTile()){
                        positionY = y;
                        positionX = x;
                    }
                }
            }
        }

        // Act
        Mover.move(UP);
        Mover.move(UP);

        // Assert
        Point[][] newBoard = GameBoard.getBoard();
        assertTrue(((Floor)newBoard[positionY - 2][positionX]).isPlayerOnTile());
    }


    @Test
    public void moveCharacterOneStepLeft_shouldntBePossible_checksIfCharacterIsntOnPoint() {
        // Arrange
        GameEngine ge = new GameEngine();
        Point[][] board = GameBoard.getBoard();

        int positionY = 0;
        int positionX = 0;
        for (int y = 1; y < board.length -1; y++) {
            for (int x = 1; x < board.length -1; x++) {
                if(board[y][x].getClass() == Floor.class) {
                    if(((Floor)board[y][x]).isPlayerOnTile()){
                        positionY = y;
                        positionX = x;
                    }
                }
            }
        }

        // Act
        Mover.move(LEFT);

        // Assert
        Point[][] newBoard = GameBoard.getBoard();
        assertFalse(((Floor)newBoard[positionY-1][positionX]).isPlayerOnTile());
    }
}
