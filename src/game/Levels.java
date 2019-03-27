package game;

import java.util.*;

public class Levels {
    private Point[][] boardGrid = new Point[20][20];
    private List<Point> levelPoints = new ArrayList<>();

    public Levels(int level) {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                createTheBasicBoard(y, x); // Generates the basic board
            }
        }
        levelDesigner(level); // Create level
        setLevelPoints();
        ((Floor) boardGrid[9][18]).setDoorOnTile(true);
    }

    // Sets the level
    // Number of monsters for the actual level is set
    // And the layout
    private void levelDesigner(int level) {
        switch (level){
            case 1:
                setWalls(true, 0, 6, 6);
                setMonsters(3,10);
                setMonsters(10,3);
                break;
            case 2:
                setMonsters(3,10);
                setMonsters(9,15);
                setMonsters(9,4);
                setWalls(true, 0, 6, 6);
                setWalls(false, 17, 0, 7);
                break;
            case 3:
                setMonsters(3,10);
                setMonsters(9,15);
                setMonsters(9,4);
                setMonsters(15,3);
                setMonsters(10,18);
                setMonsters(12,17);
                setWalls(true, 4,10, 10);
                setWalls(false, 4,5, 15);
                setWalls(true,2,5,10);
                setWalls(false, 10, 1, 5);
                setWalls(true, 0, 16, 17);
                setWalls(false, 6, 12, 7);
                break;
        }
    }

    // Add the walls to the board
    private void setWalls(boolean isYAxis, int startPositionY, int startPositionX, int wallLength) {
        if(isYAxis) {
            for (int y = startPositionY; y < wallLength; y++) {
                levelPoints.add(new Wall(y,startPositionX));
            }
        } else {
            for (int x = startPositionX; x < wallLength; x++) {
                levelPoints.add(new Wall(startPositionY,x));
            }
        }
    }

    private void setMonsters(int y, int x) {
        if(boardGrid[y][x].getClass() == Floor.class){
            ((Floor) boardGrid[y][x]).setMonsterOnTile(true);
        }

    }

    // Generating the basic board
    private void createTheBasicBoard(int y, int x) {
        if (y == 0 || x == 0 || y == 19 || x == 19)
            boardGrid[y][x] = new Wall(y, x);
        else
            boardGrid[y][x] = new Floor(y, x);
    }

    private void setLevelPoints() {
        for (Point p :
                levelPoints) {
            boardGrid[p.getY()][p.getX()] = p;
        }
    }

    public Point[][] getBoard() {
        return boardGrid;
    }
}
