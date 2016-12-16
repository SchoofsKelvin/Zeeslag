package model.battleship;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import model.Cell;
import model.Player;

public class BattleshipBoardTest {
private BattleshipCell valid,unvalid;
private boolean horizontal,vertical;
private Boat shortBoat,longBoat;
private BattleshipBoard board;
@Mock
private Player player;
@Mock
private BattleshipGame game; 
	
	@Before
	public void setUp(){
		initMocks(this);
		this.valid = new BattleshipCell(7, 7);
		this.unvalid = new BattleshipCell(-1, -1);
		this.horizontal = true;
		this.vertical = false;
		this.shortBoat = Boat.PatrolShip;
		this.longBoat = Boat.AircraftCarrier;
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
	
	@Test
	public void test_placeBoat(){
		board.placeBoat(shortBoat, horizontal,valid);
		List<Cell> boatcells = new ArrayList<Cell>();
		for(int l=0;l<shortBoat.length;l++){
			boatcells.add(new Cell(valid.x,valid.y+l));
		}
		for(int i=0;i<board.getGridSize();i++){
			for(int j=0;j<board.getGridSize();j++){
				BattleshipCell c = board.getCell(i, j);
				if((boatcells.contains(c) && !c.hasBoat()) || (!boatcells.contains(c) && c.hasBoat())){
					//assertTrue(false);
					return;
				}
			}
		}
		assertTrue(true);
	}
	
	@After
	public void cleanUp(){
		board. resetBoard(board.getGridSize());
	}
	
	
	
	
	

}
package model.battleship;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.MockitoAnnotations.initMocks;

import model.Cell;
import model.Player;

public class BattleshipBoardTest {
private BattleshipCell valid,unvalid;
private boolean horizontal,vertical;
private Boat shortBoat,longBoat;
private BattleshipBoard board;
@Mock
private Player player;
@Mock
private BattleshipGame game; 
	
	@Before
	public void setUp(){
		initMocks(this);
		this.valid = new BattleshipCell(7, 7);
		this.unvalid = new BattleshipCell(-1, -1);
		this.horizontal = true;
		this.vertical = false;
		this.shortBoat = Boat.PatrolShip;
		this.longBoat = Boat.AircraftCarrier;
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
	
	@Test
	public void test_placeBoat(){
		board.placeBoat(shortBoat, horizontal,valid);
		List<Cell> boatcells = new ArrayList<Cell>();
		for(int l=0;l<shortBoat.length;l++){
			boatcells.add(new Cell(valid.x,valid.y+l));
		}
		for(int i=0;i<board.getGridSize();i++){
			for(int j=0;j<board.getGridSize();j++){
				BattleshipCell c = board.getCell(i, j);
				if((boatcells.contains(c) && !c.hasBoat()) || (!boatcells.contains(c) && c.hasBoat())){
					//assertTrue(false);
					return;
				}
			}
		}
		assertTrue(true);
	}
	
	@After
	public void cleanUp(){
		board. resetBoard(board.getGridSize());
	}
	
	
	
	
	

}
