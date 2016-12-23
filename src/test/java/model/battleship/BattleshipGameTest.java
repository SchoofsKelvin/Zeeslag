package model.battleship;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import model.Cell;
import model.Player;
import model.battleship.BattleshipGame.TurnState;
import model.battleship.ai.RandomStrategy;

/**
 * @author Daan Adams, Thomas Goris
 *
 */
public class BattleshipGameTest {

	private int				cell;
	private BattleshipGame	game;
	@Mock
	private BattleshipBoard	bord;

	@Before
	public void setUp() {
		initMocks(this);
		this.game = new BattleshipGame(new Player("player"));
	}

	@Test
	public void shoot_on_cell_with_boat_returns_true() {
		bord.placeBoat(Boat.PatrolShip, true, new BattleshipCell(3, 3));
		game.startGame();
		assertTrue(game.shoot(3, 3));
	}
	
	@Test
	public void getScore_returns_correct_score(){
		game.startGame();
		assertEquals(game.player1.getScore(), 19);game.player1.getScore();
		game.player2.setDestroyedCells(5);
		assertEquals(game.player2.getScore(), 14);
	}
}
