package view.battleship;

import java.util.ArrayList;
import java.util.List;

import model.listener.GameResettedListener;
import model.listener.GameStartedListener;
import view.BoardCellFactory;
import view.BoardFrame;
/**
 * @author Thomas Goris, Kelvin Schoofs
 *
 */
public final class BattleshipBoardFrame extends BoardFrame {

	private static final long			serialVersionUID	= -2458982237956517265L;

	private ShipPickerPanel				picker;

	private List<GameStartedListener>	startListeners		= new ArrayList<>();
	private List<GameResettedListener>	resetListeners		= new ArrayList<>();

	public BattleshipBoardFrame(int gridSize, BoardCellFactory factory1,
		BoardCellFactory factory2) {
		super(gridSize, factory1, factory2);
	}

	@Override
	protected void addExtraFirst() {
		picker = new ShipPickerPanel(this);
		add(picker);
	}

	public ShipPickerPanel getShipPicker() {
		return picker;
	}

	public void addGameStartedListener(GameStartedListener gamelistener) {
		startListeners.add(gamelistener);
	}

	public void addGameResettedListener(GameResettedListener gamelistener) {
		resetListeners.add(gamelistener);
	}

	public void fireGameStarted() {
		for (GameStartedListener listener : startListeners) {
			listener.gameStarted();
		}
	}

	public void fireGameResetted() {
		for (GameResettedListener listener : resetListeners) {
			listener.gameResetted();
		}
	}

	public void resetBoards() {
		left.resetBoard();
		right.resetBoard();
	}

}
