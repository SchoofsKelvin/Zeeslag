package model.battleship;

import javax.swing.JOptionPane;

import exception.DomainException;
import model.Player;
import model.battleship.ai.AI;
import model.battleship.ai.Strategy;
import view.battleship.UserInterfaceService;

public class BattleshipGame {

	public final static int			gridSize	= 10;

	public final Player				player1, player2;
	public final BattleshipBoard	board1, board2;

	private TurnState				turn		= TurnState.Starting;
	private UserInterfaceService	uis;

	public BattleshipGame(Player player, Strategy strategy) throws DomainException {
		AI AI = new AI(strategy);
		this.player1 = player;
		this.player2 = AI;
		board1 = new BattleshipBoard(this, player1);
		board2 = new BattleshipBoard(this, player2);
		uis = new UserInterfaceService(this);
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

	public Player getInActivePlayer() {
		switch (turn) {
			case Player1:
				return player2;
			case Player2:
				return player1;
			default:
				return null;
		}
	}

	public boolean shoot(int x, int y) throws DomainException {
		if (x < 0 || x >= gridSize) return false;
		if (y < 0 || y >= gridSize) return false;
		if (turn == TurnState.Starting) return false;
		boolean leTurn = turn == TurnState.Player1;
		BattleshipBoard board = leTurn ? board2 : board1;
		BattleshipCell cell = board.getCell(x, y);
		if (cell.isShot()) return false;
		cell.setShot(true);
		if (cell.hasBoat()) {
			getInActivePlayer().addDestroyedCell();
			uis.updateScore();
		}
		board.fireCellUpdated(x, y);
		turn = turn == TurnState.Player1 ? TurnState.Player2 : TurnState.Player1;
		checkForGameOver();
		checkForAI();
		return true;
	}

	private void checkForAI() {
		Player player = getActivePlayer();
		if (player instanceof AI) {
			((AI) player).doTurn(this);
		}
	}

	private void checkForGameOver() {
		if (board1.noShipsLeft()) {
			playerWon(player2);
		} else if (board2.noShipsLeft()) {
			playerWon(player1);
		}
	}

	private void playerWon(Player player) {
		turn = TurnState.Finished;
		JOptionPane.showMessageDialog(null, "Game over!\n" + player.getName() + " won met "
			+ player.getScore() + " punten...");
	}

	public TurnState getTurn() {
		return turn;
	}

	public enum TurnState {
		Starting, Player1, Player2, Finished;
	}

	public void placeBoat(Player player, BattleshipCell cell) {
		if (uis.isFinishedPickingBoats()) return;
		Boat boat = uis.getPickedBoat();
		boolean horizontal = uis.isRotationHorizontal();
		BattleshipBoard board = player.equals(player1) ? board1 : board2;
		if ( !board.canPlaceBoat(boat, horizontal, cell)) return;
		board.placeBoat(boat, horizontal, cell);
		uis.removePickableBoat(boat);
	}

	public void startGame() {
		turn = TurnState.Player1;
	}

	public void resetGame() {
		turn = TurnState.Starting;
		board1.resetBoard(gridSize);
		board2.resetBoard(gridSize);
		player1.setDestroyedCells(0);
		player2.setDestroyedCells(0);
		uis.reset();
	}

}
