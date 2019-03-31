package game;

import java.util.*;

import static game.GameBoard.boardGrid;

public class GameEngine {

	private int level = 1;
	GameBoard gb;

	public GameEngine() {
		startNewGame();
	}

	public void resetGame() {
		gb.setNrOfTreasures(0);
		Player.resetTreasures();
		Player.isFinished = false;
		Player.isAlive = true;
		startNewGame();
	}

	public boolean isFinished() {
		if(isPlayerOnDoorWithTreasures()) {
			Player.isFinished = true;
			level++;
			return true;
		}
		return false;
	}

	private boolean isPlayerOnDoorWithTreasures() {
		if(((Floor) boardGrid[Player.getY()][Player.getX()]).isPlayerOnTile() &&
				((Floor) boardGrid[Player.getY()][Player.getX()]).isDoorOnTile() &&
				Player.getTreasure() == gb.getNrOfTreasures()) {
			return true;
		}
		return false;
	}

	private void startNewGame() {
		gb = new GameBoard(level);
		Player.y = 9;
		Player.x = 1;
		((Floor) boardGrid[Player.getY()][Player.getX()]).setPlayerOnTile(true);
		
		monsterTimer();
	}
	
	public GameBoard getGameBoard() {
		return this.gb;
	}

	// Returning the Point of requested position
	public Point getPoint(int y, int x) {
		return boardGrid[y][x];
	}

	// Timer that moves the monsters every one and a half second
	private void monsterTimer() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Mover.move();
			}
		};

		Timer timer = new Timer("MonsterTimer");

		timer.scheduleAtFixedRate(task, 100, 1500);
	}
}
