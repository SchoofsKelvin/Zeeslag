package model.battleship;

import exception.DomainException;
import model.Player;
import view.BoardPanel;
import view.battleship.BattleshipBoardCell;
import view.battleship.BattleshipBoardFrame;
import view.battleship.ShipPickerPanel;

public class BattleshipGame {

	public final Player				player1, player2;
	public final BattleshipBoard	board1, board2;

	private BattleshipBoardFrame	frame;
	private Turn					turn	= Turn.Starting;

	public BattleshipGame(Player player) throws DomainException {
		this.player1 = player;
		this.player2 = new AI();
		board1 = new BattleshipBoard(this, player1);
		board2 = new BattleshipBoard(this, player2);
		frame = new BattleshipBoardFrame(10,
			(x, y, buttonsize) -> new BattleshipBoardCell(x, y, buttonsize, board1, false),
			(x, y, buttonsize) -> new BattleshipBoardCell(x, y, buttonsize, board2, true));
		frame.setLeftName(player1.getName());
		frame.setRightName(player2.getName());
	}

	public Player getActivePlayer() {
		switch (turn) {
			case Player1:
				return player1;
			case Player2:
				return player2;
			default:
				return null;
		}
	}

	public boolean shoot(int x, int y) throws DomainException {
		if (turn == Turn.Starting) return false;
		boolean leTurn = turn == Turn.Player1;
		BattleshipBoard board = leTurn ? board2 : board1;
		BattleshipCell cell = board.getCell(x, y);
		if (cell.isShot()) return false;
		cell.setShot(true);
		BoardPanel sender = leTurn ? frame.left : frame.right;
		BoardPanel victim = leTurn ? frame.right : frame.left;
		sender.updateCell(x, y);
		victim.updateCell(x, y);
		return true;
	}

	public Turn getTurn() {
		return turn;
	}

	public enum Turn {
		Starting, Player1, Player2;
	}

	public void placeBoat(Player player, BattleshipCell cell) {
		ShipPickerPanel picker = frame.getShipPicker();
		Boat boat = picker.getBoat();

	}

	/*
	 * frame = new BoardFrame(10, new BoardCellFactory() {
	 * @Override public BoardCell createCell(int buttonsize) { return new
	 * BattleshipBoardCell(BattleshipGame.this, buttonsize); } });
	 */

}
