package game;

public class Point {
    private int y;
    private int x;
    private int status;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
