package game;

public class Point {
    protected final TileType ORIGINAL_TYPE;
    private int y; // On what y-axis the point is on
    private int x; // On what x-axis the point is on
    protected TileType tileType; // What kind of point it is

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

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    // TODO: 2019-03-27 Ska bort
    @Override
    public String toString() {
        return tileType.toString();
    }
}
