package game;

public abstract class Point {
    private final TileType ORIGINAL_TYPE;
    private int y; // On what y-axis the point is on
    private int x; // On what x-axis the point is on
    private TileType tileType; // What kind of point it is
    private boolean hasPlayerOnTile = false;
    private boolean hasMonsterOnTile = false;

    public Point(int y, int x, TileType tileType) {
        this.y = y;
        this.x = x;
        ORIGINAL_TYPE = tileType;
        this.tileType = ORIGINAL_TYPE;
    }

    // Getters
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public TileType getTileType() {
        return tileType;
    }

    public boolean isHasPlayerOnTile() {
        return hasPlayerOnTile;
    }

    public boolean isHasMonsterOnTile() {
        return hasMonsterOnTile;
    }

    // Setters
    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public void setHasPlayerOnTile(boolean hasPlayerOnTile) {
        if(hasPlayerOnTile)
            tileType = TileType.CHARACTER;
        else
            tileType = ORIGINAL_TYPE;
        this.hasPlayerOnTile = hasPlayerOnTile;
    }

    public void setHasMonsterOnTile(boolean hasMonsterOnTile) {
        if(hasMonsterOnTile)
            tileType = TileType.MONSTER;
        else
            tileType = ORIGINAL_TYPE;
        this.hasMonsterOnTile = hasMonsterOnTile;
    }

    @Override
    public String toString() {
        return tileType.toString();
    }
}
