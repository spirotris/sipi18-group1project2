package game;

public class Gameboard {

    private final int HEIGHT;
    private final int WIDTH;
    private final Point[][] boardGrid;

    public static final int FLOOR = 0;
    public static final int WALL = 1;
    public static final int CHARACTER = 2;
    public static final int DOOR = 4;
    public static final int TREASURE = 5;
    public static final int MONSTER = 6;

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
        if (i == 0 || j == 0 || i == WIDTH - 1 || j == HEIGHT - 1) {
            boardGrid[i][j] = new Point(i, j, WALL);
        } else if (i == (int) Math.ceil((double) WIDTH / (double) 2) && j == 1) {
            // Adding the character to it's starting point, first row to the left in the middle of the height
            characterPosition = new Point(i, j, CHARACTER);
            boardGrid[i][j] = characterPosition;
        } else if (i == 10 && j == HEIGHT - 2) {
            boardGrid[i][j] = new Point(i, j, DOOR);
        } else {
            boardGrid[i][j] = new Point(i, j, FLOOR);
        }
    }

    public Point getPoint(int x, int y) {
        return boardGrid[x][y];
    }

    // Moving character in desired direction
    public boolean moveCharacter(Direction direction) {
        int x = characterPosition.getX();
        int y = characterPosition.getY();
        int move = direction.getValue();
        if (direction.equals(Direction.RIGHT) || direction.equals(Direction.LEFT)) {
            if (!onCollision(boardGrid[x][y + move])) {
                boardGrid[x][y + move].setStatus(CHARACTER);
                boardGrid[x][y].setStatus(FLOOR);
                characterPosition.setPosition(x, y);
                return true;
            }
        } else if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) {
            if (!onCollision(boardGrid[x + move][y])) {
                boardGrid[x + move][y].setStatus(CHARACTER);
                boardGrid[x][y].setStatus(FLOOR);
                characterPosition.setPosition(x, y);
                return true;
            }
        }
        return false;
    }

    public boolean onCollision(Point p) {
        return p.getStatus() == 1; // Comparing with 1 since it is the value of walls
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }
}
