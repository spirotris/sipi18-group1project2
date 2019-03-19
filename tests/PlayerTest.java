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
	@Parameters({"10", "0"})
	public void testGetTreasureCount(int treasureCount) {
		//Arrange
		p.setTreasure(treasureCount);
		
		//Act
		int actual = p.getTreasureCount();
				
		//Assert
		assertEquals(actual, treasureCount);		
	}
	
	@Test
	public void testPlayerWalkIntoWallWithZeroTreasuresGameExit() {
		//Arrange
		Gameboard g = new Gameboard();
		Point po, wall;
		
		//Act
		//Sätt spelaren på plats 0,0
		po = g.getPoint(0,0);
		po.setStatus(3); //3 = Player
		
		//Skapa en vägg på plats 0,1
		wall = g.getPoint(0,1);
		wall.setStatus(1); //1 = Wall
		
		//Flytta spelaren in i väggen
		boolean w = false;
		if(g.moveCharacter(Direction.DOWN) == TileType.WALL)
			w = true;	//Om spelaren rörade sig in i en vägg		
		
		//Assert
		assertTrue(w);
		}
}
