import game.*;
import static game.TileType.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class MonsterTest {
    @Test
    public void getTheTileTypeFromMonster_ReturnsMONSTER() {
        // Arrange
        Point monster = new Monster(2,10);

        // Act
        TileType actual = monster.getTileType();

        // Assert
        assertEquals(MONSTER, actual);
    }
}
