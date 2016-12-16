package model.battleship;

import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import model.Player;
import model.battleship.BattleshipBoard;
import model.battleship.BattleshipCell;
import model.battleship.BattleshipGame;
import model.battleship.Boat;
import model.battleship.ai.RandomStrategy;

public class BattleshipGameTest {

	private int				cell;
	private BattleshipGame	game;
	@Mock
	private BattleshipBoard	bord;

	@Before
	public void setUp() {
		initMocks(this);
		this.game = new BattleshipGame(new Player("player"), new RandomStrategy());
	}

	@Test
	public void shoot_on_cell_with_boat_returns_true() {
		bord.placeBoat(Boat.PatrolShip, true, new BattleshipCell(3, 3));
		game.startGame();
		assertTrue(game.shoot(3, 3));
	}
}
