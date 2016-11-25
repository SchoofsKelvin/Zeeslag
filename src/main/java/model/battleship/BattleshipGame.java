package model.battleship;

import exception.DomainException;
import model.Player;
import view.BoardCell;
import view.BoardCellFactory;
import view.BoardFrame;
import view.battleship.BattleshipBoardCell;

public class BattleshipGame {

	private Player player1, player2;

	private BoardFrame frame;

	public BattleshipGame(Player player) throws DomainException {
		this.player1 = player;
		this.player2 = new AI();
		this.BattleshipGames();
	}

	public void BattleshipGames() {
		frame = new BoardFrame(10, buttonsize -> new BattleshipBoardCell(this, buttonsize));
		frame = new BoardFrame(10, new BoardCellFactory() {

			@Override
			public BoardCell createCell(int buttonsize) {
				return new BattleshipBoardCell(BattleshipGame.this, buttonsize);
			}
		});
		frame.setLeftName(player1.getName());
		frame.setRightName(player2.getName());
	}

}
