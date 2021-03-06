package model.battleship;

import exception.DomainException;
import model.Board;
import model.Cell;
import model.Player;
import model.battleship.BattleshipGame.TurnState;
/**
 * @author Daan Adams, Thomas Goris, Kelvin Schoofs, Surendra Sapkota
 *
 */
public class BattleshipBoard extends Board {

	public final BattleshipGame	game;
	public final Player			player;
	private PlacedBoat[]	boats	= new PlacedBoat[5];

	public BattleshipBoard(BattleshipGame game, Player player) {
		this.game = game;
		this.player = player;
		resetBoard(10);
	}

	@Override
	protected Cell createCell(int x, int y) {
		return new BattleshipCell(x, y);
	}

	@Override
	public BattleshipCell getCell(int x, int y) throws DomainException {
		return (BattleshipCell) super.getCell(x, y);
	}

	public void clickedCell(int x, int y, boolean other) throws DomainException {
		TurnState turn = game.getTurn();
		if (turn == TurnState.Starting && other || turn == TurnState.Finished) return;
		if (turn != TurnState.Starting && game.getActivePlayer().equals(player) == other)
			return;
		BattleshipCell cell = getCell(x, y);
		if (other) {
			game.shoot(x, y);
		} else if (turn == TurnState.Starting) {
			game.placeBoat(player, cell);
		}
	}

	public boolean canPlaceBoat(Boat b, boolean horizontal, Cell clicked)
		throws DomainException {
		int start_x = clicked.x - 1, end_x = clicked.x + 1;
		int start_y = clicked.y - 1, end_y = clicked.y + 1;
		if (horizontal) {
			end_x += b.length - 1;
		} else {
			end_y += b.length - 1;
		}
		int size = getGridSize() - 1;
		if (start_x + 1 < 0 || end_x - 1 > size) return false;
		if (start_y + 1 < 0 || end_y - 1 > size) return false;
		start_x = start_x < 0 ? 0 : start_x;
		start_y = start_y < 0 ? 0 : start_y;
		end_x = end_x > size ? size : end_x;
		end_y = end_y > size ? size : end_y;
		for (int x = start_x; x <= end_x; x++) {
			for (int y = start_y; y <= end_y; y++) {
				if (getCell(x, y).hasBoat()) return false;
			}
		}
		return true;
	}

	public void placeBoat(Boat boat, boolean horizontal, BattleshipCell cell) {
		PlacedBoat placedBoat = new PlacedBoat(cell.x, cell.y, boat, horizontal, this);
		for (BattleshipCell c : placedBoat.getCells()) {
			c.setBoat(placedBoat);
			fireCellUpdated(c.x, c.y);
		}
		for (int i = 0; i < boats.length; i++) {
			if (boats[i] == null) {
				boats[i] = placedBoat;
				return;
			}
		}
	}

	public boolean noShipsLeft() {
		for (PlacedBoat b : boats) {
			if ( !b.isDead()) return false;
		}
		return true;
	}

	@Override
	public void resetBoard(int gridSize) {
		super.resetBoard(gridSize);
		boats = new PlacedBoat[5];
	}

}
