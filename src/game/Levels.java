package game;

import java.util.*;

import static game.TileType.*;

public class Levels {

    private Point[][] boardGrid = new Point[20][20];
    private List<Monster> monsters = new ArrayList<>();

    private Random rnd = new Random();

    public Levels(int level) {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                createTheBasicBoard(y, x); // Generates the basic board
            }
        }
        levelDesigner(level); // Create level
        monsterDelegator(false); // Put monsters in startposition
        monsterTimer(); // Start the timer for moving the monsters
    }

    // Sets the level
    // Number of monsters for the actual level is set
    // And the layout
    private void levelDesigner(int level) {
        switch (level) {
            case 1:
                monsters.add(new Monster(3, 10));
                monsters.add(new Monster(10, 3));
                boardGrid[18][4].setTileType(TREASURE);
                boardGrid[4][17].setTileType(TREASURE);
                setWalls(true, 0, 6, 6);
                break;
            case 2:
                monsters.add(new Monster(3, 10));
                monsters.add(new Monster(9, 15));
                monsters.add(new Monster(9, 4));
                setWalls(true, 0, 6, 6);
                setWalls(false, 17, 0, 7);
                break;
            case 3:
                monsters.add(new Monster(3, 10));
                monsters.add(new Monster(9, 15));
                monsters.add(new Monster(9, 4));
                monsters.add(new Monster(15, 3));
                monsters.add(new Monster(10, 18));
                monsters.add(new Monster(12, 17));
                setWalls(true, 4, 10, 10);
                setWalls(false, 4, 5, 15);
                setWalls(true, 2, 5, 10);
                setWalls(false, 10, 1, 5);
                setWalls(true, 0, 16, 17);
                setWalls(false, 6, 12, 7);
                break;
        }
    }

    // Loops through the List of monsters and puts the in the board
    public void monsterDelegator(boolean moveMonsters) {
        // If it isn't the first time the monsters are placed on the board
        // then the monsters are randomly placed
        if (moveMonsters) {
            for (Monster m : monsters) {
                while (true) {
                    int x = m.getX(), oldX = m.getX();
                    int y = m.getY(), oldY = m.getY();

                    if ((rnd.nextInt(2)) > 0) {
                        y = m.getY() + (rnd.nextInt(3) - 1);
                    } else {
                        x = m.getX() + (rnd.nextInt(3) - 1);
                    }

                    if (boardGrid[y][x].getTileType() == FLOOR) {
                        m.move(boardGrid[y][x]);
                        boardGrid[y][x].setTileType(MONSTER);
                        boardGrid[oldY][oldX].setTileType(FLOOR);
                        break;
                    }
                }
            }
        } else {
            for (Monster m : monsters) {
                boardGrid[m.getY()][m.getX()] = m;
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

    // Add the walls to the board
    private void setWalls(boolean isYAxis, int startPositionY, int startPositionX, int wallLength) {
        if (isYAxis) {
            for (int y = startPositionY; y < wallLength; y++) {
                boardGrid[y][startPositionX].setTileType(WALL);
            }
        } else {
            for (int x = startPositionX; x < wallLength; x++) {
                boardGrid[startPositionY][x].setTileType(WALL);
            }
        }
    }

    // Generating the basic board
    private void createTheBasicBoard(int y, int x) {
        if (y == 0 || x == 0 || y == 19 || x == 19) {
            boardGrid[y][x] = new Point(y, x, WALL);
        } else {
            boardGrid[y][x] = new Point(y, x, FLOOR);
        }
    }

    public Point[][] getBoard() {
        return boardGrid;
    }

    /**
     * Check hows how many treasures are collectable on a certain level.
     *
     * @param level Specifies which level method returns data for.
     * @return Returns int with amount of treasures
     */
    public int getTreasureCount(int level) {
        switch (level) {
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 3;
            default:
                return 1;
        }
    }
}
