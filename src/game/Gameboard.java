package game;

import java.security.SecureRandom;
import java.util.*;

import static game.TileType.*;

public class Gameboard {

	private Point[][] boardGrid;
	private List<Monster> monsters;
	private int level = 1;

	private Levels levels;
	private Player player = new Player(9, 1);

	private boolean isFinished = false;
	private SecureRandom rnd = new SecureRandom();

	public Gameboard() {
		levels = new Levels(level);
		monsters = levels.getMonsters();
		boardGrid = levels.getBoard();
		boardGrid[9][1].setHasPlayerOnTile(true);
		boardGrid[9][14].setTileType(TREASURE);
		monsterDelegator(false);
		monsterTimer();
	}

	// Returning the Point of requested position
	public Point getPoint(int y, int x) {
		return boardGrid[y][x];
	}

	public Player getPlayer() {
		return player;
	}

	// Moving character in desired direction
	public void moveCharacter(Direction direction) {
		Integer newY = null;
		Integer newX = null;
		int oldY = 0;
		int oldX = 0;
		for (Point[] py :
				boardGrid) {
			for (Point px :
					py) {
				if(boardGrid[px.getY()][px.getX()].isHasPlayerOnTile()) {
					newY = px.getY();
					oldY = newY;
					newX = px.getX();
					oldX = newX;
				}
			}
		}

		int move = direction.getValue();

		Point prevPoint;
		if(newY != null) {
			if (direction.equals(Direction.RIGHT) || direction.equals(Direction.LEFT)) {
				newX = oldX + move;
			}
			if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) {
				newY = oldY + move;
			}
			if(!onCollision(boardGrid[newY][newX])) {
				prevPoint = boardGrid[oldY][oldX];
				boardGrid[newY][newX].setHasPlayerOnTile(true);
				prevPoint.setHasPlayerOnTile(false);
			}
		}
	}

	// Checks if the movement results in a collision
	public boolean onCollision(Point p) {
		if (p.getTileType() == WALL) {
			return true; // Can't move, wall in the way
		} else if (p.getTileType() == MONSTER) {
			player.setAlive(false);	
			return true;
		} else if (p.getTileType() == TREASURE) {
			player.setTreasure(1);
			boardGrid[p.getY()][p.getX()] = new Floor(p.getY(),p.getX());
			p.setHasPlayerOnTile(true);
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

	// Loops through the List of monsters and puts the in the board
	public void monsterDelegator(boolean moveMonsters) {
		// If it isn't the first time the monsters are placed on the board
		// then the monsters are randomly placed
		if(moveMonsters) {
			for (int y = 1; y < boardGrid.length -1; y++) {
				for (int x = 1; x < boardGrid.length -1; x++) {
					int newX;
					int newY;
					if(boardGrid[y][x].isHasMonsterOnTile()) {
						newY = y + rnd.nextInt(2) - 1;
						newX = x + rnd.nextInt(2) - 1;
						// TODO: 2019-03-26 Something is wrong with the randomizer, for now it is only moving left and upwards
						if (boardGrid[newY][newX].getTileType() == FLOOR) {
							boardGrid[newY][newX].setHasMonsterOnTile(true);
							boardGrid[y][x].setHasMonsterOnTile(false);
						}
					}
				}
			}
		}
	}

	// Timer that moves the monsters every one and a half second
	private void monsterTimer() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				monsterDelegator(true);
			}
		};

		Timer timer = new Timer("MonsterTimer");

		timer.scheduleAtFixedRate(task, 100, 1500);
	}
}
