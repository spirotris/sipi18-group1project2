package game;

public class Gameboard {
    
    private final int HEIGHT;
    private final int WIDTH;
    private final int[][] boardGrid;
    
    public Gameboard(int width, int height) {
        this.HEIGHT = height;
        this.WIDTH = width;
        boardGrid = new int[height][width];
         for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boardGrid[i][j] = 0;
            }
        }
    }
    
    public int getPoint(int x, int y) {
        return boardGrid[y][x];
    }
}
