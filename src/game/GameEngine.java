package game;

import java.util.*;

import static game.GameBoard.boardGrid;

public class GameEngine {

	private int level = 1;
	private boolean isFinished = false;
	private Player player = new Player();
	GameBoard gb;

	public GameEngine() {
		startNewGame();
	}

	public void resetGame() {
		gb.setNrOfTreasures(0);
		Player.resetTreasures();
		isFinished = false;
		Player.isAlive = true;
		startNewGame();
	}
	
	public boolean isFinished() {
		if(isPlayerOnDoorWithTreasures()) {
			isFinished = true;
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
		//((Floor) boardGrid[9][14]).setTreasureOnTile(true);
		
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

	public Point getCharacterPosition() {
		Point character = null;
		for (int y = 1; y < boardGrid.length - 1; y++) {
			for (int x = 1; x < boardGrid.length - 1; x++) {
				if (boardGrid[y][x].getClass() == Floor.class && ((Floor) boardGrid[y][x]).isPlayerOnTile()) {
					character = boardGrid[y][x];
				}
			}
		}
		return character;
	}
}
