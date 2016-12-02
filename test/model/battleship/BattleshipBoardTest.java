package model.battleship;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Board;
import model.Cell;
import model.Player;
import model.battleship.ai.AllSeeingEnemyStrategy;

public class BattleshipBoardTest {
private Cell valid,unvalid;
private boolean horizontal,vertical;
private Boat shortBoat,longBoat;
private BattleshipBoard board;
private Player player;
private BattleshipGame game; 
	
	@Before
	public void setUp(){
		this.valid = new Cell(7, 7);
		this.unvalid = new Cell(-1, -1);
		this.horizontal = true;
		this.vertical = false;
		this.shortBoat = Boat.PatrolShip;
		this.longBoat = Boat.AircraftCarrier;
		this.player = new Player("Surendra");
		this.game = new BattleshipGame(player, new AllSeeingEnemyStrategy());
		this.board = new BattleshipBoard(game, player);
	}
	
	/*U boat kan naar rechts of naar onderaan geplaats kunnen worden.*/
	
	@Test
	public void test_canPlaceBoat_for_fittingboat(){
		assertTrue(board.canPlaceBoat(shortBoat, horizontal, valid));
		assertTrue(board.canPlaceBoat(shortBoat, vertical, valid));

	}
	@Test
	public void test_canPlaceBoat_for_nonFittingBoat(){
		assertFalse(board.canPlaceBoat(longBoat, horizontal, valid));
		assertFalse(board.canPlaceBoat(longBoat, vertical, valid));

	}
	
	@Test
	public void test_cannotPlaceBoat_on_invalid_cell(){
		assertFalse(board.canPlaceBoat(shortBoat, horizontal, unvalid));
		assertFalse(board.canPlaceBoat(shortBoat, vertical, unvalid));	
	}
	
	
	
	
	

}
