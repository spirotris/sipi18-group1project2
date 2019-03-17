/**
 * @author Marcus Laitala
 * @Date 2019-03-17 * 
 * @version 0.1
 */

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import game.Point;
import game.Player;

@RunWith(JUnitParamsRunner.class)
public class PlayerTest {
	Player p;

	@Before
	public void setup() {		
		p = new Player(new Point(1,1,0));
	}
	
	@Test
	@Parameters({ "Tommy", "Emil", "Bob", "Marcus", "\n\t", "" })
	public void testSettingPlayerName(String name) {
		// Arrange
		p.setName(name);
		// Act
		String actual = p.getName();
		// Assert
		assertEquals(actual, name);
	}

	@Test
	@Parameters({ 
	    	"1,1,0", // Floor
	        "0,0,1", // Wall
	        "2,2,2", // Character
	        "5,5,3", // Treasure
	        "20,10,4", // Door
	        "10,5,5" // Laser
	        })
	public void testGetPositionThroughPlayerClass(int x, int y, int status) {	
		// Act
		//Set the player position
		p.setPosition(new Point(x, y, status));
		
		// Assert
		//Check that the players postion equals what we set it to
		assertTrue(p.getPosition().compare(new Point(x, y, status)));
	}
}
