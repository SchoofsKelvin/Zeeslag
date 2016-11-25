package model.battleship;

import exception.DomainException;
import model.Board;
import model.Cell;
import model.Player;
import model.battleship.BattleshipGame.Turn;

public class BattleshipBoard extends Board {

	public final BattleshipGame	game;
	public final Player			player;

	public BattleshipBoard(BattleshipGame game, Player player) {
		this.game = game;
		this.player = player;
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
		Turn turn = game.getTurn();
		if (turn == Turn.Starting && other) return;
		if (turn != Turn.Starting && game.getActivePlayer() != player) return;
		BattleshipCell cell = getCell(x, y);
		if (other) {
			game.shoot(x, y);
		} else if (turn == Turn.Starting) {
			game.placeBoat(player, cell);
		}
	}

	public boolean canPlaceBoat(Boat b, boolean horizontal, Cell clicked)
		throws DomainException {
		int start_x = clicked.x - 1;
		int start_y = clicked.y - 1;
		int end_x = clicked.x + 1;
		int end_y = clicked.y + 1;
		if (horizontal) {
			end_x += b.length;
		} else {
			end_y += b.length;
		}
		for (int x = start_x; x < end_x; x++) {
			for (int y = start_y; y < end_y; y++) {
				if (getCell(x, y).getBoat() != null) return false;
			}
		}
		return true;
	}

}
