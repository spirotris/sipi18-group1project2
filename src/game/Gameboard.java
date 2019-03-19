package game;

public class Gameboard {

    private final Point[][] boardGrid;
    private int level = 1;

    private Point characterPosition = new Point(9,1, TileType.CHARACTER);
    private Point doorPosition = new Point(9,18, TileType.DOOR);

    private Levels levels;

    public Gameboard() {
        levels = new Levels(level);
        boardGrid = levels.getBoard();
        boardGrid[characterPosition.getY()][characterPosition.getX()].setTileType(TileType.CHARACTER);
        boardGrid[doorPosition.getY()][doorPosition.getX()].setTileType(TileType.DOOR);
        boardGrid[18][9].setTileType(TileType.TREASURE);
    }

    // Returning the Point of requested position
    public Point getPoint(int y, int x) {
        return boardGrid[y][x];
    }
    
    public Point getCharacterPosition() {
        return characterPosition;
    }

    // Moving character in desired direction
    public boolean moveCharacter(Direction direction) {
        int y = characterPosition.getY();
        int x = characterPosition.getX();
        int move = direction.getValue();
        if (direction.equals(Direction.RIGHT) || direction.equals(Direction.LEFT)) {
            if (!onCollision(boardGrid[y][x + move])) {
                boardGrid[y][x + move].setTileType(TileType.CHARACTER);
                boardGrid[y][x].setTileType(TileType.FLOOR);

                characterPosition = boardGrid[y][x + move];
                return true;
            }
        } else if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) {
            if (!onCollision(boardGrid[y + move][x])) {
                boardGrid[y + move][x].setTileType(TileType.CHARACTER);
                boardGrid[y][x].setTileType(TileType.FLOOR);

                characterPosition = boardGrid[y + move][x];
                return true;
            }
        }

        // There was a wall in the way
        return false;
    }

    // Checks if the movement results in collision with a wall
    public boolean onCollision(Point p) {
        return p.getTileType() == TileType.WALL; // Comparing with 1 since it is the value of walls
    }
}