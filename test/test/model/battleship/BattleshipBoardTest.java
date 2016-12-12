package test.model.battleship;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.battleship.BattleshipBoard;
import model.battleship.BattleshipCell;
import model.battleship.Boat;

public class BattleshipBoardTest{
	
	public BattleshipBoard board;
	
	@Before
	public void setUp(){
		board = new BattleshipBoard(null, null);
	}
	
	@After
	public void clean(){
		
	}
	
	@Test
	public void canPlaceBoatTest(){
		BattleshipCell c = board.getCell(7, 7);
		Boat shortBoat = Boat.PatrolShip;
		Boat longBoat = Boat.AircraftCarrier;
		Assert.assertTrue(board.canPlaceBoat(shortBoat, true, c));
		Assert.assertTrue(board.canPlaceBoat(shortBoat, false, c));
		Assert.assertFalse(board.canPlaceBoat(longBoat, true, c));
		Assert.assertFalse(board.canPlaceBoat(longBoat, false, c));
	}

}
