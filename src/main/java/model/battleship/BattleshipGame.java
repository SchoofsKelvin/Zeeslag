package model.battleship;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import exception.DomainException;
import model.Player;
import model.battleship.ai.AI;
import model.listener.GameReadyChangedListener;
/**
 * @author Daan Adams, Thomas Goris, Kelvin Schoofs, Surendra Sapkota
 *
 */
public class BattleshipGame {

	public final static int					gridSize	= 10;

	public final Player						player1, player2;
	public final BattleshipBoard			board1, board2;
	private BattleshipInput					input1, input2;
	private boolean							ready1, ready2;

	private TurnState						turn		= TurnState.Starting;

	private List<GameReadyChangedListener>	listeners	= new ArrayList<>();

	public BattleshipGame(Player player) throws DomainException {
		this(player, new AI());
	}

	public BattleshipGame(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		board1 = new BattleshipBoard(this, player1);
		board2 = new BattleshipBoard(this, player2);
	}

	public void setInput(BattleshipInput input) {
		this.input1 = input;
		this.input2 = BattleshipInput.empty;
	}

	public void setInput(BattleshipInput input1, BattleshipInput input2) {
		this.input1 = input1;
		this.input2 = input2;
	}

	private BattleshipInput getInput(Player player) {
		return player == player1 ? input1 : input2;
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
			input1.updateScore();
			input2.updateScore();
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
		JOptionPane.showMessageDialog(null, "Game over!\n" + player.getName() + " won with "
			+ player.getScore() + " points...");
	}

	public TurnState getTurn() {
		return turn;
	}

	public enum TurnState {
		Starting, Player1, Player2, Finished;
	}

	public void placeBoat(Player player, BattleshipCell cell) {
		BattleshipInput input = getInput(player);
		if (input.isFinishedPickingBoats()) return;
		Boat boat = input.getPickedBoat();
		boolean horizontal = input.isRotationHorizontal();
		BattleshipBoard board = player.equals(player1) ? board1 : board2;
		if ( !board.canPlaceBoat(boat, horizontal, cell)) return;
		board.placeBoat(boat, horizontal, cell);
		input.removePickableBoat(boat);
		if (input.isFinishedPickingBoats()) {
			setReady(player, true);
		}
	}

	private void setReady(Player player, boolean ready) {
		if (player == player1) {
			ready1 = ready;
		} else {
			ready2 = ready;
		}
		fireGameReadyChanged();
	}

	private void fireGameReadyChanged() {
		for (GameReadyChangedListener listener : listeners) {
			listener.gameReadyChanged(ready1, ready2);
		}
	}

	public void addGameReadyChangedListener(GameReadyChangedListener listener) {
		listeners.add(listener);
	}

	public void startGame() {
		turn = TurnState.Player1;
		ready1 = false;
		ready2 = false;
		fireGameReadyChanged();
	}

	public void resetGame() {
		turn = TurnState.Starting;
		board1.resetBoard(gridSize);
		board2.resetBoard(gridSize);
		player1.setDestroyedCells(0);
		player2.setDestroyedCells(0);
		ready1 = false;
		ready2 = false;
		if (player2 instanceof AI) {
			((AI) player2).placeBoats(board2);
			((AI) player2).setStrategy(input1.createStrategy());
			setReady(player2, true);
		}
		input1.reset();
		input2.reset();
		fireGameReadyChanged();
	}

}
