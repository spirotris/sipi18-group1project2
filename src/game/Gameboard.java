package game;

import java.security.SecureRandom;
import java.util.*;

import static game.TileType.*;

public class Gameboard {

	private Point[][] boardGrid;
	private int level = 1;

	private Levels levels;
	private Player player = new Player();

	private boolean isFinished = false;
	private SecureRandom rnd = new SecureRandom();

	public Gameboard() {
		levels = new Levels(level);
		boardGrid = levels.getBoard();
		((Floor) boardGrid[9][1]).setPlayerOnTile(true);
		((Floor) boardGrid[9][14]).setTreasureOnTile(true);
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
				if(boardGrid[px.getY()][px.getX()].getClass() == Floor.class && ((Floor) boardGrid[px.getY()][px.getX()]).isPlayerOnTile()) {
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
				((Floor) boardGrid[newY][newX]).setPlayerOnTile(true);
				((Floor) prevPoint).setPlayerOnTile(false);
			}
		}
	}

	// Checks if the movement results in a collision
	public boolean onCollision(Point p) {
		if (p.getClass() == Wall.class) {
			return true; // Can't move, wall in the way
		} else if (((Floor) p).isMonsterOnTile()) {
			player.setAlive(false);	
			return true;
		} else if (((Floor) p).isTreasureOnTile()) {
			player.addTreasure();
			((Floor) p).setTreasureOnTile(false);
			((Floor) p).setPlayerOnTile(true);
			return false;
		} else if (((Floor) p).isDoorOnTile()) {
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
					if(boardGrid[y][x].getClass() == Floor.class && ((Floor) boardGrid[y][x]).isMonsterOnTile()) {
						newY = y + rnd.nextInt(2) - 1;
						newX = x + rnd.nextInt(2) - 1;
						// TODO: 2019-03-26 Something is wrong with the randomizer, for now it is only moving left and upwards
						if (boardGrid[newY][newX].getTileType() == FLOOR) {
							((Floor) boardGrid[newY][newX]).setMonsterOnTile(true);
							((Floor) boardGrid[y][x]).setMonsterOnTile(false);
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
