package game;

public class Levels {
    private Point[][] boardGrid = new Point[20][20];

    public Levels(int level) {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                addPointToBoard(y, x);
            }
        }
        levelDesigner(level);
    }

    private void levelDesigner(int level) {
        switch (level){
            case 1:
                int wallLength = 6;
                for (int y = 0; y < wallLength; y++){
                    boardGrid[y][6].setTileType(TileType.WALL);
                }
            case 2:
                int wallLengthOne = 6;
                int wallLengthTwo = 7;
                for (int y = 0; y < wallLengthOne;y++) {
                    boardGrid[y][6].setTileType(TileType.WALL);
                }
                for (int x = 0; x < wallLengthTwo; x++) {
                    boardGrid[17][x].setTileType(TileType.WALL);
                }
        }
    }

    private void addPointToBoard(int y, int x) {
        if (y == 0 || x == 0 || y == 19 || x == 19)
            boardGrid[y][x] = new Point(y, x, TileType.WALL);
        else
            boardGrid[y][x] = new Point(y, x, TileType.FLOOR);
    }

    public Point[][] getBoard() {
        return boardGrid;
    }
}
