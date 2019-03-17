package game;

public class Point {

    private int y; // On what y-axis the point is on
    private int x; // On what x-axis the point is on
    private int status; // What kind of point it is

    public Point(int y, int x, int status) {
        this.y = y;
        this.x = x;
        this.status = status;
    }
    
    protected void setPoint(Point p) {
    	this.x = p.getX();
    	this.y = p.getY();
    	this.status = p.getStatus();
    }
    
    protected Point getPoint() {
    	return new Point(x,y,status);
    }
    
    protected int setX(int x) {
    	this.x = x;
    }
    
    protected int setY(int y) {
    	this.y = y;
    }
    
    protected int setStatus(int status) {
    	this.status = status;
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
