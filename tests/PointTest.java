import game.Point;
import game.TileType;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class PointTest {

    @Test
    @Parameters({
            "1,1,FLOOR", // Floor
            "0,0,WALL", // Wall
            "2,2,CHARACTER", // Character
            "5,5,TREASURE", // Treasure
            "10,20,DOOR", // Door
            "5,10,MONSTER" // Laser
    })
    public void createPointGetTypeByInt(int x, int y, TileType tileType) {
        // Assert
        Point p = new Point(x, y, tileType);

        // Act
        TileType actual = p.getTileType();

        // Assert
        assertEquals(tileType,actual);
    }    
}

