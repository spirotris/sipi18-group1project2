package game;

public class Gameboard {
    
    private final int HEIGHT;
    private final int WIDTH;
    private final Point[][] boardGrid;

    private final int FLOOR = 0;
    private final int WALL = 1;
    private final int CHARACTER = 2;

    private Point characterPosition;
    
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

    // Adding the Points to the gameboard
    private void addPointToBoard(int i, int j) {
        if(i == 0 || j == 0 || i == HEIGHT -1 || j == WIDTH -1) {
            // Adding Outer Walls
            boardGrid[i][j] = new Point(i, j, WALL);
        } else if(i == (HEIGHT / 2) && j == 1) {
            // Adding the character to it's starting point, first row to the left in the middle of the height
            characterPosition = new Point(i, j, CHARACTER);
            boardGrid[i][j] = characterPosition;
        } else {
            // Everything else is considered floor
            boardGrid[i][j] = new Point(i, j, FLOOR);
        }
    }

    // Returning the Point of requested position
    public Point getPoint(int x, int y) {
        return boardGrid[y][x];
    }

    // Moving character in desired direction
    public void moveCharacter(Direction direction) {
        if(direction.equals(Direction.RIGHT ) || direction.equals(Direction.LEFT)) { //Moving right or left
            boardGrid[characterPosition.getY()][characterPosition.getX() + direction.getValue()].setStatus(CHARACTER); // Sets the status of new position to CHARACTER
            boardGrid[characterPosition.getY()][characterPosition.getX()].setStatus(FLOOR); // Sets the status of old position to FLOOR
        } else if(direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) { // Moving up or down
            boardGrid[characterPosition.getY() + direction.getValue()][characterPosition.getX()].setStatus(CHARACTER); // Sets the status of new position to CHARACTER
            boardGrid[characterPosition.getY()][characterPosition.getX()].setStatus(FLOOR); // Sets the status of old position to FLOOR
        }
    }
}
