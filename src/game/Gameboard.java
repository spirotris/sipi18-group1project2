package game;

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
                if(i == 0 || j == 0 || i == HEIGHT -1 || j == WIDTH -1)
                    boardGrid[i][j] = 0;
                else
                    boardGrid[i][j] = 1;
            }
        }
    }
    
    public int getPoint(int x, int y) {
        return boardGrid[y][x];
    }
}
