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
            "20,10,4", // Door
            "10,5,5" // Laser
    })
    public void createPointGetTypeByInt(int y, int x, int status) {
        // Assert
        Point p = new Point(y, x, status);

        // Act
        int actual = p.getStatus();

        // Assert
        assertEquals(status,actual);
    }
}

