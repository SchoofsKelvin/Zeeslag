package model.battleship;

import exception.DomainException;
import model.Player;
import view.BoardPanel;
import view.battleship.BattleshipBoardCell;
import view.battleship.BattleshipBoardFrame;

public class BattleshipGame {

	private Player					player1, player2;
	private BattleshipBoardFrame	frame;
	private BattleshipBoard			board1, board2;
	private boolean					turn	= false;

	public BattleshipGame(Player player) throws DomainException {
		this.player1 = player;
		this.player2 = new AI();
		board1 = new BattleshipBoard();
		board2 = new BattleshipBoard();
		frame = new BattleshipBoardFrame(10,
			(x, y, buttonsize) -> new BattleshipBoardCell(x, y, buttonsize, board1),
			(x, y, buttonsize) -> new BattleshipBoardCell(x, y, buttonsize, board2));
		frame.setLeftName(player1.getName());
		frame.setRightName(player2.getName());
	}

	public Player getActivePlayer() {
		return turn ? player1 : player2;
	}

	public boolean shoot(int x, int y) throws DomainException {
		BattleshipBoard board = turn ? board2 : board1;
		BattleshipCell cell = board.getCell(x, y);
		if (cell.isShot()) return false;
		cell.setShot(true);
		BoardPanel sender = turn ? frame.left : frame.right;
		BoardPanel victim = turn ? frame.right : frame.left;
		sender.updateCell(x, y);
		victim.updateCell(x, y);
		return true;
	}

	/*
	 * frame = new BoardFrame(10, new BoardCellFactory() {
	 * @Override public BoardCell createCell(int buttonsize) { return new
	 * BattleshipBoardCell(BattleshipGame.this, buttonsize); } });
	 */

}
