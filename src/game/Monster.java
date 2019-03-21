package game;

public class Monster extends Point {
    private boolean isActive = false;

    public Monster(int y, int x, TileType tileType) {
        super(y, x, tileType);
    }

    // Getter
    public boolean isActive() {
        return isActive;
    }

    // Setter

    public void setActive(boolean active) {
        isActive = active;
    }
}
