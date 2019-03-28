package game;

public class Point {
    protected final TileType ORIGINAL_TYPE;
    protected TileType tileType; // What kind of point it is

    public Point(TileType tileType) {
        ORIGINAL_TYPE = tileType;
        this.tileType = ORIGINAL_TYPE;
    }

    public TileType getTileType() {
        return tileType;
    }
}
