package game;

public class Gameboard {

    private final Point[][] boardGrid;

    private final int FLOOR = 0;
    private final int WALL = 1;
    private final int CHARACTER = 2;
    private final int DOOR = 4;

    private Point characterPosition = new Point(9,1,CHARACTER);
    private Point doorPosition = new Point(9,18,DOOR);

    Levels levels = new Levels();

    public Gameboard(int level) {
        boardGrid = levels.getLevel(level);
        boardGrid[characterPosition.getY()][characterPosition.getX()].setStatus(CHARACTER);
        boardGrid[doorPosition.getY()][doorPosition.getX()].setStatus(DOOR);
        boardGrid[18][9].setStatus(5);
    }

    // Returning the Point of requested position
    public Point getPoint(int y, int x) {
        return boardGrid[y][x];
    }

    // Moving character in desired direction
    public boolean moveCharacter(Direction direction) {
        if(direction.equals(Direction.RIGHT ) || direction.equals(Direction.LEFT)) { //Moving right or left
            if(!onCollision(boardGrid[characterPosition.getY()][characterPosition.getX() + direction.getValue()])) {  // If there isn't a wall movement is possible
                boardGrid[characterPosition.getY()][characterPosition.getX() + direction.getValue()] = characterPosition; // Sets the status of new position to CHARACTER
                characterPosition = new Point(characterPosition.getY(), characterPosition.getX() + direction.getValue(), CHARACTER);
                boardGrid[characterPosition.getY()][characterPosition.getX() - direction.getValue()].setStatus(FLOOR); // Sets the status of old position to FLOOR
                return true;
            }
        } else if(direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) { // Moving up or down
            if(!onCollision(boardGrid[characterPosition.getY() + direction.getValue()][characterPosition.getX()])) { // If there isn't a wall movement is possible
                boardGrid[characterPosition.getY() + direction.getValue()][characterPosition.getX()].setStatus(CHARACTER); // Sets the status of new position to CHARACTER
                characterPosition = new Point(characterPosition.getY() + direction.getValue(), characterPosition.getX(), CHARACTER);
                boardGrid[characterPosition.getY() - direction.getValue()][characterPosition.getX()].setStatus(FLOOR); // Sets the status of old position to FLOOR
                return true;
            }
        }
        // There was a wall in the way
        return false;
    }

    // Checks if the movement results in collision with a wall
    public boolean onCollision(Point p) {
        return p.getStatus() == 1; // Comparing with 1 since it is the value of walls
    }
}
