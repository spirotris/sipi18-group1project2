package game;

public class Point {

    private int y;
    private int x;
    private TileType tileType;

    public Point(int y, int x, TileType tileType) {
        this.y = y;
        this.x = x;
        this.tileType = tileType;
    }

    public TileType getTileType() {
        return tileType;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean equals(Point p) {
        return (this.x == p.getX() && this.y == p.getY() && p.getTileType() == p.getTileType());
    }

    public void move(Point p) {
        y = p.getY();
        x = p.getX();
        tileType = p.getTileType();
    }
}
