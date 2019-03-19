package game;

public class Point {

    private int y; // On what y-axis the point is on
    private int x; // On what x-axis the point is on
    private int status; // What kind of point it is

    public Point(int x, int y, int status) {
        this.y = y;
        this.x = x;
        this.status = status;
    }
    
    protected void setPoint(Point p) {
    	this.x = p.getX();
    	this.y = p.getY();
    	this.status = p.getStatus();
    }
    
    public boolean compare(Point p) {
    	if(x == p.getX() && y == p.getY() && status == p.getStatus())
    		return true;
    	return false;
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
    public int getStatus() {
        return status;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    // Setting the status
    public void setStatus(int status) {
        this.status = status;
    }
}
