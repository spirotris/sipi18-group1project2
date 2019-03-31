package game;

import java.util.Random;

import static game.GameBoard.boardGrid;
import static game.Direction.*;

public class Mover {
    private static int newY = 0, oldY = 0;
    private static int newX = 0, oldX = 0;
    private static Random rnd = new Random();
    private static Point[][]board = GameBoard.getBoard();
    private static boolean isPlayer;

    public static void move() {
    Direction direction = DOWN;
    isPlayer = false;
        for (int y = 1; y < board.length - 1; y++) {
            for (int x = 1; x < board.length - 1; x++) {
                if (board[y][x].getClass() == Floor.class) {
                    if(((Floor)board[y][x]).isMonsterOnTile()) {
                        oldY = y;
                        oldX = x;

                        int dirY = rnd.nextInt(3);
                        int dirX = rnd.nextInt(3);
                        if(dirY == 2) {
                            direction = DOWN;
                        } else if(dirY == 1) {
                            direction = UP;
                        }
                        if(dirX == 2) {
                            direction = RIGHT;
                        } else if (dirX == 1) {
                            direction = LEFT;
                        }
                        performMove(oldY,oldX,direction);
                    }
                }
            }
        }
        newY = 0;
        oldY = 0;
        newX = 0;
        oldX = 0;
    }
    public static void move(Direction direction) {
        isPlayer = true;

        Floor character = (Floor)board[Player.y][Player.x];

        for (int y = 1; y < board.length - 1; y++) {
            for (int x = 1; x < board.length - 1; x++) {
                if (board[y][x].getClass() == Floor.class) {
                    if(character.isPlayerOnTile() && ((Floor)board[y][x]).isPlayerOnTile()) {
                        oldY = y;
                        oldX = x;
                        break;
                    }
                }
            }
        }
        performMove(Player.y, Player.x, direction);
        newY = 0;
        oldY = 0;
        newX = 0;
        oldX = 0;
    }

    private static void performMove(int y, int x, Direction direction) {
        int move = direction.getValue();

        if (direction == RIGHT || direction == LEFT) {
            newX = oldX + move;
            newY = y;
        } else if (direction == UP || direction == DOWN) {
            newY = oldY + move;
            newX = x;
        }
        if (!onCollision(boardGrid[newY][newX])) {
            if(((Floor)board[y][x]).isPlayerOnTile()) {
                ((Floor) GameBoard.boardGrid[oldY][oldX]).setPlayerOnTile(false);
                ((Floor) GameBoard.boardGrid[newY][newX]).setPlayerOnTile(true);
                Player.y = newY;
                Player.x = newX;
            } else if(((Floor)board[y][x]).isMonsterOnTile()) {
                ((Floor) GameBoard.boardGrid[oldY][oldX]).setMonsterOnTile(false);
                ((Floor) GameBoard.boardGrid[newY][newX]).setMonsterOnTile(true);
            }
        }
    }

    private static boolean onCollision(Point p) {
        if (p.getClass() == Wall.class) {
            return true;
        } else if (((Floor) p).isMonsterOnTile()) {
            if(isPlayer)
                Player.isAlive = false;
            return true;
        } else if (((Floor) p).isTreasureOnTile()) {
            if(isPlayer) {
                Player.addTreasure();
                ((Floor) p).setTreasureOnTile(false);
                ((Floor) p).setPlayerOnTile(true);
                return false;
            } else
                return true;
        } else if (((Floor) p).isDoorOnTile()) {
            if (Player.getTreasure() > 0) {
                Player.isFinished = true;
            }
            return false;
        } else if(!isPlayer && ((Floor)p).isPlayerOnTile()) {
            Player.isAlive = false;
            return true;
        } else {
            return false;
        }
    }
}
