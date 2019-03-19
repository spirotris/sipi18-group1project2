package game;

import static game.TileType.*;

public class Gameboard {

    private final Point[][] boardGrid;
    private int level = 1;

    private Point characterPosition = new Point(9,1, CHARACTER);
    private Point doorPosition = new Point(9,18, DOOR);

    private Levels levels;

    public Gameboard() {
        levels = new Levels(level);
        boardGrid = levels.getBoard();
        boardGrid[characterPosition.getY()][characterPosition.getX()].setTileType(CHARACTER);
        boardGrid[doorPosition.getY()][doorPosition.getX()].setTileType(DOOR);
        boardGrid[18][9].setTileType(TREASURE);
    }

    // Returning the Point of requested position
    public Point getPoint(int y, int x) {
        return boardGrid[y][x];
    }

    // Moving character in desired direction
    public boolean moveCharacter(Direction direction) {
        int y = characterPosition.getY();
        int x = characterPosition.getX();
        int move = direction.getValue();
        if (direction.equals(Direction.RIGHT) || direction.equals(Direction.LEFT)) {
            if (!onCollision(boardGrid[y][x + move])) {
                boardGrid[y][x + move].setTileType(CHARACTER);
                boardGrid[y][x].setTileType(FLOOR);

                characterPosition = boardGrid[y][x + move];
                return true;
            }
        } else if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) {
            if (!onCollision(boardGrid[y + move][x])) {
                boardGrid[y + move][x].setTileType(CHARACTER);
                boardGrid[y][x].setTileType(FLOOR);

                characterPosition = boardGrid[y + move][x];
                return true;
            }
        }

        // There was a wall in the way
        return false;
    }

    // Checks if the movement results in collision with a wall
    public boolean onCollision(Point p) {
        return p.getTileType() == WALL; // Comparing with 1 since it is the value of walls
    }
}
