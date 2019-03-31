package game;

import static game.TileType.*;

public class Floor extends Point {
    private boolean playerOnTile = false;
    private boolean monsterOnTile = false;
    private boolean treasureOnTile = false;
    private boolean doorOnTile = false;

    public Floor() {
        super(TileType.FLOOR);
    }


    public boolean isPlayerOnTile() {
        return playerOnTile;
    }

    public boolean isMonsterOnTile() {
        return monsterOnTile;
    }

    public boolean isTreasureOnTile() {
        return treasureOnTile;
    }

    public boolean isDoorOnTile() {
        return doorOnTile;
    }

    public void setPlayerOnTile(boolean playerOnTile) {
        if(playerOnTile) {
            this.tileType = CHARACTER;
        } else {
            if(doorOnTile) {
                tileType = DOOR;
            } else {
                tileType = ORIGINAL_TYPE;
            }
        }
        this.playerOnTile = playerOnTile;
    }

    public void setMonsterOnTile(boolean monsterOnTile) {
        if(monsterOnTile) {
            tileType = MONSTER;
        } else {
            if(treasureOnTile) {
                tileType = TREASURE;
            } else if (doorOnTile) {
                tileType = DOOR;
            } else {
                tileType = ORIGINAL_TYPE;
            }
        }
        this.monsterOnTile = monsterOnTile;
    }

    public void setTreasureOnTile(boolean treasureOnTile) {
        if(treasureOnTile) {
            this.tileType = TREASURE;
        }
        this.treasureOnTile = treasureOnTile;
    }

    public void setDoorOnTile(boolean doorOnTile) {
        if(doorOnTile) {
            this.tileType = DOOR;
        }
        this.doorOnTile = doorOnTile;
    }
}
