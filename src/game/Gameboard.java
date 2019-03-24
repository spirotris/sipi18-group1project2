package game;

import static game.TileType.*;

public class Gameboard {

    private Point[][] boardGrid;
    private int level = 1;

    private Point doorPosition = new Point(9, 18, DOOR);

    private Levels levels;
    private Player player;

    private boolean isPlayerOnDoor = false;
    private boolean isFinished = false;

    public Gameboard() {
        levels = new Levels(level);
        player = new Player(9, 1, CHARACTER);
        boardGrid = levels.getBoard();
        boardGrid[player.getY()][player.getX()].setTileType(CHARACTER);
        boardGrid[doorPosition.getY()][doorPosition.getX()].setTileType(DOOR);
    }

    public Point getPoint(int y, int x) {
        return boardGrid[y][x];
    }

    public Player getPlayer() {
        return player;
    }

    private void fixPreviousTile(int y, int x, TileType tiletype) {
        if (tiletype == DOOR) {
            isPlayerOnDoor = false;
        }
        boardGrid[y][x].setTileType(tiletype);
    }

    // Moving character in desired direction
    public boolean moveCharacter(Direction direction) {
        int y = player.getY();
        int x = player.getX();
        int move = direction.getValue();

        if (direction.equals(Direction.RIGHT) || direction.equals(Direction.LEFT)) {
            if (!onCollision(boardGrid[y][x + move])) {
                // Mostly, the player will be walking on floor, makes sense to have this first
                if (!isPlayerOnDoor) {
                    // Move the character on the board
                    fixPreviousTile(y, x, FLOOR);
                    fixPreviousTile(y, x + move, CHARACTER);
                } else {
                    if (boardGrid[y][x + move].getTileType() == DOOR) {
                        fixPreviousTile(y, x, FLOOR);
                    } else {
                        fixPreviousTile(y, x, DOOR);
                    }
                    // Move the character on the board
                    fixPreviousTile(y, x + move, CHARACTER);
                }
                // Update the players coordinates
                player.move(boardGrid[y][x + move]);
                return true;
            }
        } else if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) {
            if (!onCollision(boardGrid[y + move][x])) {
                // Mostly, the player will be walking on floor, makes sense to have this first
                if (!isPlayerOnDoor) {
                    // Move the character on the board
                    fixPreviousTile(y, x, FLOOR);
                    fixPreviousTile(y + move, x, CHARACTER);
                } else {
                    if (boardGrid[y + move][x].getTileType() == DOOR) {
                        fixPreviousTile(y, x, FLOOR);
                    } else {
                        fixPreviousTile(y, x, DOOR);
                    }
                    // Move the character on the board
                    fixPreviousTile(y + move, x, CHARACTER);
                }
                // Update the players coordinates
                player.move(boardGrid[y + move][x]);

                return true;
            }
        }

        // There was a wall in the way
        return false;
    }

    // Checks if the movement results in a collision
    public boolean onCollision(Point p) {
        switch (p.getTileType()) {
            case WALL:
                return true;
            case MONSTER:
                player.setAlive(false);
                return true;
            case TREASURE:
                player.addTreasure();
                return false;
            case DOOR:
                if (player.getTreasure() >= levels.getTreasureCount(level)) {
                    if (level == 3) {
                        isFinished = true;
                    } else {
                        player.resetTreasure();
                        levels = new Levels(++level);
                        boardGrid = levels.getBoard();
                    }
                }
                isPlayerOnDoor = true;
                return false;
            default:
                // Otherwise movement is a okay
                return false;
        }
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Levels getLevels() {
        return levels;
    }

    public int getCurrentLevel() {
        return level;
    }
}
