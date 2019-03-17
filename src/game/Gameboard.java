package game;

public class Gameboard {
    
    private final int HEIGHT;
    private final int WIDTH;
    private final Point[][] boardGrid;

    private final int FLOOR = 0;
    private final int WALL = 1;
    private final int CHARACTER = 2;
    private final int DOOR = 4;

    private Point characterPosition;

    public Gameboard(int width, int height) {
        this.HEIGHT = height;
        this.WIDTH = width;
        boardGrid = new Point[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                addPointToBoard(i, j);
            }
        }
    }

    // Adding the Points to the gameboard
    private void addPointToBoard(int i, int j) {
        if(i == 0 || j == 0 || i == WIDTH -1 || j == HEIGHT -1) {
            // Adding Outer Walls
            boardGrid[i][j] = new Point(i, j, WALL);
        } else if(i == (int)Math.ceil((double)WIDTH / (double)2) && j == 1) {
            // Adding the character to it's starting point, first row to the left in the middle of the height
            characterPosition = new Point(i, j, CHARACTER);
            boardGrid[i][j] = characterPosition;
        } else if(i == 10 && j == HEIGHT - 2 ) {
            boardGrid[i][j] = new Point(i, j, DOOR);
        } else{
            // Everything else is considered floor
            boardGrid[i][j] = new Point(i, j, FLOOR);
        }
    }

    // Returning the Point of requested position
    public Point getPoint(int x, int y) {
        return boardGrid[x][y];
    }

    // Moving character in desired direction
    public boolean moveCharacter(Direction direction) {
        if(direction.equals(Direction.RIGHT ) || direction.equals(Direction.LEFT)) { //Moving right or left
            if(!onCollision(boardGrid[characterPosition.getX()][characterPosition.getY() + direction.getValue()])) {  // If there isn't a wall movement is possible
                boardGrid[characterPosition.getX()][characterPosition.getY() + direction.getValue()].setStatus(CHARACTER); // Sets the status of new position to CHARACTER
                boardGrid[characterPosition.getX()][characterPosition.getY()].setStatus(FLOOR); // Sets the status of old position to FLOOR
                return true;
            }
        } else if(direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) { // Moving up or down
            if(!onCollision(boardGrid[characterPosition.getX() + direction.getValue()][characterPosition.getY()])) { // If there isn't a wall movement is possible
                boardGrid[characterPosition.getX() + direction.getValue()][characterPosition.getY()].setStatus(CHARACTER); // Sets the status of new position to CHARACTER
                boardGrid[characterPosition.getX()][characterPosition.getY()].setStatus(FLOOR); // Sets the status of old position to FLOOR
                return true;
            }
        }
        // There was a wall in the way
        return false;
    }

    // Checks if the movement results in collision with a wall
    public boolean onCollision(Point p) {
        return p.getStatus() == 1; // Comparing with 1 since it is the value of walls
    }
}
