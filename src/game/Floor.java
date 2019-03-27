package game;

import static game.TileType.*;

public class Floor extends Point {
    private boolean hasPlayerOnTile = false;
    private boolean hasMonsterOnTile = false;
    private boolean hasTreasureOnTile = false;
    private boolean hasDoorOnTile = false;

    public Floor(int y, int x) {
        super(y, x, TileType.FLOOR);
    }


    public boolean isHasPlayerOnTile() {
        return hasPlayerOnTile;
    }

    public boolean isHasMonsterOnTile() {
        return hasMonsterOnTile;
    }

    public boolean isHasTreasureOnTile() {
        return hasTreasureOnTile;
    }

    public boolean isHasDoorOnTile() {
        return hasDoorOnTile;
    }

    public void setHasPlayerOnTile(boolean hasPlayerOnTile) {
        if(hasPlayerOnTile)
            this.tileType = CHARACTER;
        else
            tileType = ORIGINAL_TYPE;
        this.hasPlayerOnTile = hasPlayerOnTile;
    }

    public void setHasMonsterOnTile(boolean hasMonsterOnTile) {
        if(hasMonsterOnTile)
            tileType = MONSTER;
        else
            tileType = ORIGINAL_TYPE;
        this.hasMonsterOnTile = hasMonsterOnTile;
    }

    public void setHasTreasureOnTile(boolean hasTreasureOnTile) {
        if(hasTreasureOnTile) {
            this.tileType = TREASURE;
        }
        this.hasTreasureOnTile = hasTreasureOnTile;
    }

    public void setHasDoorOnTile(boolean hasDoorOnTile) {
        if(hasDoorOnTile) {
            this.tileType = DOOR;
        }
        this.hasDoorOnTile = hasDoorOnTile;
    }
}
