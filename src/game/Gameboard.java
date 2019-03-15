package game;

import java.util.Arrays;

public class Gameboard {
    
    private final int HEIGHT;
    private final int WIDTH;
    private final int[][] boardGrid;
    
    public Gameboard(int width, int height) {
        this.HEIGHT = height;
        this.WIDTH = width;
        boardGrid = new int[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                boardGrid[i][j] = 0;
            }
        }
    }
    
    public int getPoint(int x, int y) {
        return boardGrid[y][x];
    }

    public String getBoard() {
        return Arrays.deepToString(boardGrid).replace("], ", "]\n");
    }
}
