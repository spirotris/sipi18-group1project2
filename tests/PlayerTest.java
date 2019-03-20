/**
 * @author Marcus Laitala
 * @Date 2019-03-17 
 * @version 0.4
 */

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import game.Point;
import game.Player;
import game.Gameboard;
import game.Direction;
import game.TileType;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class PlayerTest {
	Player player;

	@Before
	public void setup() {		
		player = new Player();
	}
	
	@Test
	@Parameters({ "Tommy", "Emil", "Bob", "Marcus", "\n\t", "" })
	public void testSettingPlayerName(String name) {
		// Arrange
		player.setName(name);
		// Act
		String actual = player.getName();
		// Assert
		assertEquals(actual, name);
	}

	@Test
	@Parameters({"10", "0", "-1"})
	public void testSetAndGetTreasureCount(int treasureCount) {
		//Arrange
		player.setTreasure(treasureCount);
		
		//Act
		int actual = player.getTreasure();
				
		//Assert
		assertEquals(actual, treasureCount);		
	}
	
	@Test
	@Parameters({"0, 1", "0, -1"})	
	public void testPlayerWalkOntoTreasureAndCollectIt(int startTreasure, int nrOfFoundTreasures) {
		//Arrange
		Gameboard g = new Gameboard();
		player.setTreasure(startTreasure);
		Point po, treasure;
		
		//Act
		//Move the player onto point 0,0
		po = g.getPoint(0,0);
		po.setTileType(TileType.CHARACTER);
		
		//Create a treasure at 0,1
		treasure = g.getPoint(0,1);
		treasure.setTileType(TileType.TREASURE);
		int actual = 0;
		//Move player onto treasure
		if(g.moveCharacter(Direction.DOWN) && g.getPoint(po.getX(), po.getY() + Direction.DOWN.getValue()).getTileType() == TileType.TREASURE) {
			player.setTreasure(nrOfFoundTreasures);
			actual = player.getTreasure();
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
		po.setTileType(TileType.CHARACTER);
		
		//Create a treasure at 0,1
		door = g.getPoint(0,1);
		door.setTileType(TileType.DOOR);
			
		//Act
		boolean actual = false;
		//Move player onto door
		if(g.moveCharacter(Direction.DOWN) && g.getPoint(po.getX(), po.getY() + Direction.DOWN.getValue()).getTileType() == TileType.DOOR) {
			actual = true;
		}
		
		//Assert
		if(hasTreasure)
			assertTrue(actual);
		else
			assertFalse(actual);
	}

	/*
	@Test
	public void testPlayerWalkingOntoLaseResultsInGameOver() {
		//Arrange		
		Gameboard g = new Gameboard();
		Point po, laser;
		
		g.setPlayerAlive(true);
		
		//Move the player onto point 0,0
		po = g.getPoint(0,0);
		po.setTileType(TileType.CHARACTER);
		
		//Create a laser at 0,1
		door = g.getPoint(0,1);
		door.setTileType(TileType.LASER);
		
		//Act
		if(g.moveCharacter(Direction.DOWN) && g.getPoint(po.getX(), po.getY() + Direction.DOWN).TileType == TileType.LASER)
			g.setPlayerAlive(false);
		
		//Assert
		assertFalse(g.getPlayerAlive());
	}*/
}
