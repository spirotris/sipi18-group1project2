import game.Monster;
import game.Point;
import game.TileType;

import org.junit.Test;
import static org.junit.Assert.*;

public class MonsterTest {
    @Test
    public void getTheTileTypeFromMonster_ReturnsMONSTER() {
        // Arrange
        Point monster = new Monster(2,10, TileType.MONSTER);

        // Act
        TileType actual = monster.getTileType();

        // Assert
        assertEquals(TileType.MONSTER, actual);
    }

    @Test
    public void checksIfMonsterIsActiveAndVisible_ReturnsTrue() {
        // Arrange
        Point monster = new Monster(12, 10, TileType.MONSTER);

        // Act
        ((Monster) monster).setActive(true);
        
        // Assert
        assertTrue(((Monster) monster).isActive());

    }
}
