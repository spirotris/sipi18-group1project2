package game;

public class Point {

    private int y; // On what y-axis the point is on
    private int x; // On what x-axis the point is on
    private TileType tileType; // What kind of point it is

    public Point(int y, int x, TileType tileType) {
        this.y = y;
        this.x = x;
        this.tileType = tileType;
    }

    // Getters
    public TileType getTileType() {
        return tileType;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    //Setters

    // Setters
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
    	if(this.x == p.getX() && this.y == p.getY() && p.getTileType() == p.getTileType())
    		return true;
    	return false;
    }
}
