package game;

public enum Direction {
    RIGHT(1),
    UP(-1),
    LEFT(-1),
    DOWN(1);

    private int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
