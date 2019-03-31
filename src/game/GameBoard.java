package game;

public class GameBoard {
	static Point[][] boardGrid = new Point[20][20];
	private int nrOfTreasures = 0;

	public GameBoard(int level) {
		for (int y = 0; y < 20; y++) {
			for (int x = 0; x < 20; x++) {
				createTheBasicBoard(y, x); // Generates the basic board
			}
		}
		levelDesigner(level); // Create level
		((Floor) boardGrid[9][18]).setDoorOnTile(true);
	}

	private void levelDesigner(int level) {
		switch (level) {
		case 1:
			setWalls(true, 0, 6, 6);
			((Floor) boardGrid[3][10]).setMonsterOnTile(true);
			((Floor) boardGrid[10][3]).setMonsterOnTile(true);
			setTreasureOnTile(9, 14);
			break;
		case 2:
			((Floor) boardGrid[3][10]).setMonsterOnTile(true);
			((Floor) boardGrid[9][15]).setMonsterOnTile(true);
			((Floor) boardGrid[9][4]).setMonsterOnTile(true);
			setWalls(true, 0, 6, 6);
			setWalls(false, 17, 0, 7);
			break;
		case 3:
			((Floor) boardGrid[3][10]).setMonsterOnTile(true);
			((Floor) boardGrid[9][15]).setMonsterOnTile(true);
			((Floor) boardGrid[9][4]).setMonsterOnTile(true);
			((Floor) boardGrid[15][3]).setMonsterOnTile(true);
			((Floor) boardGrid[10][18]).setMonsterOnTile(true);
			((Floor) boardGrid[12][17]).setMonsterOnTile(true);
			setWalls(true, 4, 10, 10);
			setWalls(false, 4, 5, 15);
			setWalls(true, 2, 5, 10);
			setWalls(false, 10, 1, 5);
			setWalls(true, 0, 16, 17);
			setWalls(false, 6, 12, 7);
			break;
		}
	}

	// Add the walls to the board
	private void setWalls(boolean isYAxis, int startPositionY, int startPositionX, int wallLength) {
		if (isYAxis) {
			for (int y = startPositionY; y < wallLength; y++) {
				boardGrid[y][startPositionX] = new Wall();
			}
		} else {
			for (int x = startPositionX; x < wallLength; x++) {
				boardGrid[startPositionY][x] = new Wall();
			}
		}
	}

	// Generating the basic board
	private void createTheBasicBoard(int y, int x) {
		if (y == 0 || x == 0 || y == 19 || x == 19)
			boardGrid[y][x] = new Wall();
		else
			boardGrid[y][x] = new Floor();
	}

	public static Point[][] getBoard() {
		return boardGrid;
	}

	public boolean setTreasureOnTile(int y, int x) {
		if (isThereAnObstacle(y, x))
			return false;
		((Floor)boardGrid[y][x]).setTreasureOnTile(true);
		nrOfTreasures++;
		return true;
		
	}

	private boolean isThereAnObstacle(int y, int x) {
		if (boardGrid[y][x].tileType == TileType.WALL)
			return true;
		return false;
	}

	/**
	 * @return the nrOfTreasures
	 */
	public int getNrOfTreasures() {
		return nrOfTreasures;
	}

	/**
	 * @param nrOfTreasures the nrOfTreasures to set
	 */
	public void setNrOfTreasures(final int nrOfTreasures) {
		this.nrOfTreasures = nrOfTreasures;
	}
}
