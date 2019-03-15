import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PointTest {
    @Test
    public void createPointGetTypeByInt() {
        // Assert
        Point p = new Point(0,0);

        // Act
        int actual = p.getStatus();

        // Assert
        assertEquals(0,actual);
    }
}

