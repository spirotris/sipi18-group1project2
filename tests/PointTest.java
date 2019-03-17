import game.Point;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class PointTest {

    @Test
    @Parameters({
            "1,1,0", // Floor
            "0,0,1", // Wall
            "2,2,2", // Character
            "5,5,3", // Treasure
            "10,20,4", // Door
            "5,10,5" // Laser
    })
    public void createPointGetTypeByInt(int x, int y, int status) {
        // Assert
        Point p = new Point(x, y, status);

        // Act
        int actual = p.getStatus();

        // Assert
        assertEquals(status,actual);
    }
}

