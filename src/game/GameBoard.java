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
				setWalls(true, 1, 6, 12);
				setWalls(false, 7, 2, 6);
				setWalls(true, 11, 7, 7);
				setWalls(false, 6, 10, 2);
				setWalls(false, 12, 14, 3);
				setWalls(false, 12, 2, 6);
				setWalls(true, 10, 15, 3);
				((Floor) boardGrid[5][10]).setMonsterOnTile(true);
				((Floor) boardGrid[10][3]).setMonsterOnTile(true);
				setTreasureOnTile(13, 14);
				setTreasureOnTile(1, 1);
				break;
			case 2:
				setWalls(true, 0, 6, 6);
				setWalls(false, 15, 0, 7);
				setWalls(true, 8, 8,9);
				((Floor) boardGrid[3][10]).setMonsterOnTile(true);
				((Floor) boardGrid[9][15]).setMonsterOnTile(true);
				((Floor) boardGrid[9][4]).setMonsterOnTile(true);
				setTreasureOnTile(13, 14);
				setTreasureOnTile(1, 1);
				setTreasureOnTile(5, 3);
				setTreasureOnTile(18, 14);
				break;
			case 3:
				setTreasureOnTile(3, 1);
				setTreasureOnTile(18, 18);
				setTreasureOnTile(10, 12);
				setTreasureOnTile(10, 5);
				setTreasureOnTile(2, 14);
				((Floor) boardGrid[3][10]).setMonsterOnTile(true);
				((Floor) boardGrid[9][15]).setMonsterOnTile(true);
				((Floor) boardGrid[2][3]).setMonsterOnTile(true);
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
			case 4:
				setWalls(true, 2, 2, 4);
				setWalls(false, 5, 2, 6);
				setWalls(false, 7, 1, 2);
				setWalls(true, 7, 2, 11);
				setWalls(true, 5, 4, 14);
				setWalls(true, 1, 4, 3);
				setWalls(false, 12, 5, 5);
				setWalls(true, 2, 6, 4);
				setWalls(true, 3, 6, 6);
				setWalls(false, 10, 6, 2);
				setWalls(false, 14, 6, 8);
				setWalls(true, 14, 6, 4);
				setWalls(true, 8, 7, 3);
				setWalls(false, 8, 7, 3);
				setWalls(false, 1, 8, 4);
				setWalls(true, 16, 8, 4);
				setWalls(false, 5, 9, 5);
				setWalls(true, 5, 9, 4);
				setWalls(false, 10, 9, 3);
				setWalls(true, 10, 9, 3);
				setWalls(true, 14, 10, 4);
				setWalls(false, 17, 10, 8);
				setWalls(false, 7, 11, 5);
				setWalls(true, 7, 11, 4);
				setWalls(true, 1, 13, 5);
				setWalls(false, 9, 13, 5);
				setWalls(true, 9, 13, 6);
				setWalls(false, 2, 15, 3);
				setWalls(true, 2, 15, 6);
				setWalls(false, 6, 15, 3);
				setWalls(true, 11, 15, 5);
				setWalls(false, 15, 15, 4);
				setWalls(false, 4, 17, 2);
				setWalls(false, 8, 17, 2);
				setWalls(true, 8, 17, 6);
				setTreasureOnTile(1, 1);
				((Floor) boardGrid[2][10]).setMonsterOnTile(true);
				((Floor) boardGrid[10][15]).setMonsterOnTile(true);
				((Floor) boardGrid[9][5]).setMonsterOnTile(true);
				((Floor) boardGrid[15][3]).setMonsterOnTile(true);
				((Floor) boardGrid[10][18]).setMonsterOnTile(true);
				((Floor) boardGrid[12][15]).setMonsterOnTile(true);
				((Floor) boardGrid[18][1]).setMonsterOnTile(true);
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
		((Floor) boardGrid[y][x]).setTreasureOnTile(true);
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
