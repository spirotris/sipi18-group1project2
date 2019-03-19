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
		//Sätt spelaren på plats 0,0
		po = g.getPoint(0,0);
		po.setStatus(2); //2 = Player
		
		//Skapa en skatt på plats 0,1
		treasure = g.getPoint(0,1);
		treasure.setStatus(TileType.TREASURE);
		int actual = 0;
		//Flytta spelaren till skatten
		if(g.moveCharacter(Direction.DOWN) == TileType.TREASURE) {
			p.setTreasure(nrOfFoundTreasures);
			actual = p.getTreasure();
		}
		
		//Assert
		assertEquals(actual, nrOfFoundTreasures);
		}
}
