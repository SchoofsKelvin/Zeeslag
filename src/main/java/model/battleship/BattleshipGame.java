package model.battleship;

import exception.DomainException;
import model.Player;
import view.BoardFrame;
import view.battleship.BattleshipBoardCell;

public class BattleshipGame {

	private final Player player1, player2;

	private BoardFrame frame;

	public BattleshipGame(Player playerA, Player playerB) {
		player1 = playerA;
		player2 = playerB;
		frame = new BoardFrame(10, buttonsize -> new BattleshipBoardCell(this, buttonsize));
		frame.setLeftName(player1.getName());
		frame.setRightName(player2.getName());
	}

	public BattleshipGame(Player player) throws DomainException {
		this(player, new AI());
	}

}
