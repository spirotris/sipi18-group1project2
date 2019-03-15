package game;

public class Gameboard {
    
    private final int HEIGHT;
    private final int WIDTH;
    private final Point[][] boardGrid;

    private final int FLOOR = 0;
    private final int WALL = 1;
    private final int CHARACTER = 2;
    
    public Gameboard(int width, int height) {
        this.HEIGHT = height;
        this.WIDTH = width;
        boardGrid = new Point[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                addPointToBoard(i, j);
            }
        }
    }

    private void addPointToBoard(int i, int j) {
        if(i == 0 || j == 0 || i == HEIGHT -1 || j == WIDTH -1)
            boardGrid[i][j] = new Point(i,j,WALL);
        else if(i == (HEIGHT / 2) && j == 1) {
            boardGrid[i][j] = new Point(i,j,CHARACTER);
        }
        else
            boardGrid[i][j] = new Point(i,j,FLOOR);
    }

    public Point getPoint(int x, int y) {
        return boardGrid[y][x];
    }
}
