package model.battleship;

import java.util.ArrayList;

import exception.DomainException;
import model.Player;
import view.battleship.BattleshipBoardCell;
import view.battleship.BattleshipBoardFrame;
import view.battleship.ShipPickerPanel;

public class BattleshipGame {

	public final static int			gridSize	= 10;

	public final Player				player1, player2;
	public final BattleshipBoard	board1, board2;

	private BattleshipBoardFrame	frame;
	private Turn					turn		= Turn.Starting;

	public BattleshipGame(Player player) throws DomainException {
		AI AI = new AI();
		this.player1 = player;
		this.player2 = AI;
		board1 = new BattleshipBoard(this, player1);
		board2 = new BattleshipBoard(this, player2);
		frame = new BattleshipBoardFrame(gridSize,
			(x, y, buttonsize) -> new BattleshipBoardCell(x, y, buttonsize, board1, false),
			(x, y, buttonsize) -> new BattleshipBoardCell(x, y, buttonsize, board2, true));
		frame.setLeftName(player1.getName());
		frame.setRightName(player2.getName());
		board1.addObserver(frame.left);
		board2.addObserver(frame.right);
		AI.placeBoats(board2);
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
		System.out.println("Shoot (" + x + ", " + y + ")");
		if (turn == Turn.Starting) return false;
		boolean leTurn = turn == Turn.Player1;
		BattleshipBoard board = leTurn ? board2 : board1;
		BattleshipCell cell = board.getCell(x, y);
		if (cell.isShot()) return false;
		cell.setShot(true);
		// BoardPanel sender = leTurn ? frame.left : frame.right;
		// BoardPanel victim = leTurn ? frame.right : frame.left;
		// sender.updateCell(x, y);
		// victim.updateCell(x, y);
		board.fireCellUpdated(x, y);
		turn = turn == Turn.Player1 ? Turn.Player2 : Turn.Player1;
		checkForAI();
		return true;
	}

	private void checkForAI() {
		Player player = getActivePlayer();
		if (player instanceof AI) {
			((AI) player).doTurn(this);
		}
	}

	public Turn getTurn() {
		return turn;
	}

	public enum Turn {
		Starting, Player1, Player2, Finished;
	}

	public void placeBoat(Player player, BattleshipCell cell) {
		System.out.println(player + " places a boat at " + cell);
		ShipPickerPanel picker = frame.getShipPicker();
		if (picker.isFinished()) return;
		Boat boat = picker.getBoat();
		boolean horizontal = picker.rotationIsHorizontal();
		BattleshipBoard board = player.equals(player1) ? board1 : board2;
		if ( !board.canPlaceBoat(boat, horizontal, cell)) return;
		board.placeBoat(boat, horizontal, cell);
		picker.removeBoat(boat);
		if ( !picker.isFinished()) return;
		turn = Turn.Player1;
	}

	public void placeAllBoatsAI(ArrayList<Boat> boats) {
		for (int i = 0; i < 5; i++) {
			Boat boat = boats.get((int) (boats.size() * Math.random()));
			boats.remove(boat);

			BattleshipBoard board = this.board2;

			int size = board.getGridSize();

			double x = size * Math.random();
			double y = size * Math.random();
			double direction = Math.random();
			boolean horizontal = (direction < 0.5);
			BattleshipCell cell = board.getCell((int) x, (int) y);
			if ( !board.canPlaceBoat(boat, horizontal, cell)) return;
			board.placeBoat(boat, horizontal, cell);
		}
	}

	/*
	 * frame = new BoardFrame(10, new BoardCellFactory() {
	 * @Override public BoardCell createCell(int buttonsize) { return new
	 * BattleshipBoardCell(BattleshipGame.this, buttonsize); } });
	 */

}
