/**
 * @author Marcus Laitala
 * @Date 2019-03-17 
 * @version 0.3
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
import game.Gameboard;
import game.Direction;

@RunWith(JUnitParamsRunner.class)
public class PlayerTest {
	Player p;

	@Before
	public void setup() {		
		p = new Player();
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
	@Parameters({"10", "0", "-1"})
	public void testSetAndGetTreasureCount(int treasureCount) {
		//Arrange
		p.setTreasure(treasureCount);
		
		//Act
		int actual = p.getTreasureCount();
				
		//Assert
		assertEquals(actual, treasureCount);		
	}
	
	@Test
	@Parameters({"0, 1", "0, -1"})	
	public void testPlayerWalkOntoTreasureAndCollectIt(int startTreasure, int nrOfFoundTreasures) {
		//Arrange
		Gameboard g = new Gameboard();
		p.setTreasure(startTreasure);
		Point po, treasure;
		
		//Act
		//Move the player onto point 0,0
		po = g.getPoint(0,0);
		po.setStatus(TileType.CHARACTER);
		
		//Create a treasure at 0,1
		treasure = g.getPoint(0,1);
		treasure.setStatus(TileType.TREASURE);
		int actual = 0;
		//Move player onto treasure
		if(g.moveCharacter(Direction.DOWN) == TileType.TREASURE) {
			p.setTreasure(nrOfFoundTreasures);
			actual = p.getTreasure();
		}
			
		//Assert
		assertEquals(actual, nrOfFoundTreasures);
	}
		
	@Test
	@Parameters({"true", "false"})	
	public void testPlayerWalkingOntoDoorWithTreasures(boolean hasTreasure ) {
		//Arrange		
		Gameboard g = new Gameboard();
		Point po, door;
			
		//Move the player onto point 0,0
		po = g.getPoint(0,0);
		po.setStatus(TileType.CHARACTER);
		
		//Create a treasure at 0,1
		door = g.getPoint(0,1);
		door.setStatus(TileType.DOOR);
			
		//Act
		boolean actual = false;
		//Move player onto door
		if(g.moveCharacter(Direction.DOWN) == TileType.DOOR) {
			actual = true;
		}
		
		//Assert
		if(hasTreasure)
			assertTrue(actual);
		else
			assertFalse(actual);
	}
	
	@Test
	public void testPlayerWalkingOntoLaseResultsInGameOver() {
		//Arrange		
		Gameboard g = new Gameboard();
		Point po, laser;
		
		g.setPlayerAlive(True);
		
		//Move the player onto point 0,0
		po = g.getPoint(0,0);
		po.setStatus(TileType.CHARACTER);
		
		//Create a laser at 0,1
		door = g.getPoint(0,1);
		door.setStatus(TileType.LASER);
		
		//Act
		if(g.moveCharacter(Direction.Down) == TileType.LASER)
				g.setPlayerAlive(false);
		
		//Assert
		assertFalse(g.getPlayerAlive());
	}
}
