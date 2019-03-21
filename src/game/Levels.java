package game;

import java.util.*;

public class Levels {
    private Point[][] boardGrid = new Point[20][20];
    private List<Monster> monsters = new ArrayList<>();

    public Levels(int level) {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                addPointToBoard(y, x);
            }
        }
        levelDesigner(level);
        monsterDelegator();
    }

    private void monsterDelegator() {
        for (Monster m :
                monsters) {
            boardGrid[m.getY()][m.getX()] = m;
            m.setActive(true);
        }
    }

    private void levelDesigner(int level) {
        switch (level){
            case 1:
                monsters.add(new Monster(3,10, TileType.MONSTER));
                setWalls(true, 0, 6, 6);
                break;
            case 2:
                monsters.add(new Monster(3, 10, TileType.MONSTER));
                monsters.add(new Monster(9, 15, TileType.MONSTER));
                monsters.add(new Monster(9, 4, TileType.MONSTER));
                setWalls(true, 0, 6, 6);
                setWalls(false, 17, 0, 7);
                break;
            case 3:
                monsters.add(new Monster(3, 10, TileType.MONSTER));
                monsters.add(new Monster(9, 15, TileType.MONSTER));
                monsters.add(new Monster(9, 4, TileType.MONSTER));
                monsters.add(new Monster(15,3, TileType.MONSTER));
                monsters.add(new Monster(10, 18, TileType.MONSTER));
                monsters.add(new Monster(12, 17, TileType.MONSTER));
                setWalls(true, 4,10, 10);
                setWalls(false, 4,5, 15);
                setWalls(true,2,5,10);
                setWalls(false, 10, 1, 5);
                setWalls(true, 0, 16, 17);
                setWalls(false, 6, 12, 7);
                break;
        }
    }

    private void setWalls(boolean isYAxis, int startPositionY, int startPositionX, int wallLength) {
        if(isYAxis) {
            for (int y = startPositionY; y < wallLength; y++) {
                boardGrid[y][startPositionX].setTileType(TileType.WALL);
            }
        } else {
            for (int x = startPositionX; x < wallLength; x++) {
                boardGrid[startPositionY][x].setTileType(TileType.WALL);
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
