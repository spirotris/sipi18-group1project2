package game;

import static game.TileType.*;

public class Gameboard {

	private final Point[][] boardGrid;
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
		boardGrid[9][14].setTileType(TREASURE);
	}

	// Returning the Point of requested position
	public Point getPoint(int y, int x) {
		return boardGrid[y][x];
	}

	public Player getPlayer() {
		return player;
	}

	// TODO This needs a better name
	// No special handling for any other usecase other than door, so far. If they
	// arise, put them here
	private void fixPreviousTile(int y, int x, TileType tiletype) {
		switch (tiletype) {
		case DOOR:
			boardGrid[y][x].setTileType(tiletype);
			isPlayerOnDoor = false;
			break;
		default:
			boardGrid[y][x].setTileType(tiletype);
			break;
		}
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
					if (boardGrid[y][x + move].getTileType() == DOOR)
						fixPreviousTile(y, x, FLOOR);
					else
						fixPreviousTile(y, x, DOOR);
					// Move the character on the board
					fixPreviousTile(y, x + move, CHARACTER);
				}
				// Update the players coordinates
				player.movePlayer(boardGrid[y][x + move]);
				return true;
			}
		} else if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) {
			if (!onCollision(boardGrid[y + move][x])) {
				// Mostly, the player will be walking on floor, makes sense to have this first
				if (!isPlayerOnDoor) {
					// Move the character on the board
					fixPreviousTile(y + move, x, CHARACTER);
					fixPreviousTile(y, x, FLOOR);
				} else {
					// Move the character on the board
					fixPreviousTile(y + move, x, CHARACTER);
					fixPreviousTile(y, x, DOOR);
				}
				// Update the players coordinates
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
			player.setAlive(false);			
			// Game is over
			return true;
		} else if (p.getTileType() == TREASURE) {
			player.setTreasure(1);
			return false;
		} else if (p.getTileType() == DOOR) {
			if (player.getTreasure() > 0) {
				isFinished = true;
			}
			isPlayerOnDoor = true;
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
