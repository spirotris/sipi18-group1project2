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

    // Returns the points kind
    public int getStatus() {
        return status;
    }


    // Setting the status
    public void setStatus(int status) {
        this.status = status;
    }
}
