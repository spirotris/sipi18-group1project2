package game;

import java.util.*;

import static game.TileType.*;

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

    private void levelDesigner(int level) {
        switch (level){
            case 1:
                monsters.add(new Monster(3,10, MONSTER));
                monsters.add(new Monster(10,3,MONSTER));
                setWalls(true, 0, 6, 6);
                break;
            case 2:
                monsters.add(new Monster(3, 10, MONSTER));
                monsters.add(new Monster(9, 15, MONSTER));
                monsters.add(new Monster(9, 4, MONSTER));
                setWalls(true, 0, 6, 6);
                setWalls(false, 17, 0, 7);
                break;
            case 3:
                monsters.add(new Monster(3, 10, MONSTER));
                monsters.add(new Monster(9, 15, MONSTER));
                monsters.add(new Monster(9, 4, MONSTER));
                monsters.add(new Monster(15,3, MONSTER));
                monsters.add(new Monster(10, 18, MONSTER));
                monsters.add(new Monster(12, 17, MONSTER));
                setWalls(true, 4,10, 10);
                setWalls(false, 4,5, 15);
                setWalls(true,2,5,10);
                setWalls(false, 10, 1, 5);
                setWalls(true, 0, 16, 17);
                setWalls(false, 6, 12, 7);
                break;
        }
    }

    private void monsterDelegator() {
        for (Monster m :
                monsters) {
            boardGrid[m.getY()][m.getX()] = m;
        }
    }

    private void setWalls(boolean isYAxis, int startPositionY, int startPositionX, int wallLength) {
        if(isYAxis) {
            for (int y = startPositionY; y < wallLength; y++) {
                boardGrid[y][startPositionX].setTileType(WALL);
            }
        } else {
            for (int x = startPositionX; x < wallLength; x++) {
                boardGrid[startPositionY][x].setTileType(WALL);
            }
        }
    }

    private void addPointToBoard(int y, int x) {
        if (y == 0 || x == 0 || y == 19 || x == 19)
            boardGrid[y][x] = new Point(y, x, WALL);
        else
            boardGrid[y][x] = new Point(y, x, FLOOR);
    }

    public Point[][] getBoard() {
        return boardGrid;
    }
}
