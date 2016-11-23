package model.battleship;

import model.Player;
import view.BoardCell;
import view.BoardCellFactory;
import view.BoardFrame;
import view.battleship.BattleshipBoardCell;

public class BattleshipGame {

	public final Player	player1, player2;

	private BoardFrame	frame;

	public BattleshipGame(Player playerA, Player playerB) {
		player1 = playerA;
		player2 = playerB;
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

	public BattleshipGame(Player player) {
		this(player, new AI());
	}

}
