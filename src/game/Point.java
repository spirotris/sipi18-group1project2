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
    
    protected void setPoint(Point p) {
    	this.x = p.getX();
    	this.y = p.getY();
    	this.tiletype = p.getTileType();
    }
    
    public Point getPoint() {
    	return this;
    }
    
    protected void setX(int x) {
    	this.x = x;
    }
    
    protected void setY(int y) {
    	this.y = y;
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

    // Setting the tileType
    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
}
