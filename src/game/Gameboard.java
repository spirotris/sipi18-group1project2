package game;

import static game.TileType.*;

public class Gameboard {

    private final Point[][] boardGrid;
    private int level = 1;
   
    private Point doorPosition = new Point(9, 18, DOOR);

    private Levels levels;
    private Player player;

    private boolean isFinished = false;

    public Gameboard() {
        levels = new Levels(level);
        player = new Player(9,1, CHARACTER);
        boardGrid = levels.getBoard();
        boardGrid[player.getY()][player.getX()].setTileType(CHARACTER);
        boardGrid[doorPosition.getY()][doorPosition.getX()].setTileType(DOOR);
        boardGrid[9][14].setTileType(TREASURE);
    }

    // Returning the Point of requested position
    public Point getPoint(int y, int x) {
        return boardGrid[y][x];
    }
    
    public Player getPlayer() {
    	return player;
    }

    // Moving character in desired direction
    public boolean moveCharacter(Direction direction) {
        int y = player.getY();
        int x = player.getX();
        int move = direction.getValue();
        if (direction.equals(Direction.RIGHT) || direction.equals(Direction.LEFT)) {
            if (!onCollision(boardGrid[y][x + move])) {
                boardGrid[y][x + move].setTileType(CHARACTER);
                boardGrid[y][x].setTileType(FLOOR);

                player.movePlayer(boardGrid[y][x + move]);
                return true;
            }
        } else if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) {
            if (!onCollision(boardGrid[y + move][x])) {
                boardGrid[y + move][x].setTileType(CHARACTER);
                boardGrid[y][x].setTileType(FLOOR);
                
                player.movePlayer(boardGrid[y + move][x]);
                return true;
            }
        }

        // There was a wall in the way
        return false;
    }

    // Checks if the movement results in a collision
    public boolean onCollision(Point p) {
        if (p.getTileType() == WALL) {
            return true; // Can't move, wall in the way
        } else if (p.getTileType() == MONSTER) {
            // Returns true to show that character really stepped onto monster
            // Game is although over
            return false;
        } else if (p.getTileType() == TREASURE) {
            player.setTreasure(1);
            return false;
        } else if (p.getTileType() == DOOR) {
            if (player.getTreasure() > 0) {
                isFinished = true;
            }
            return false;
        } else {
            // Otherwise movement is a okay
            return false;
        }
    }

    public boolean isFinished() {
        return isFinished;
    }    
}
