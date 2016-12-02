package view.battleship;

import model.battleship.BattleshipGame;
import view.BoardCellFactory;
import view.BoardFrame;

public final class BattleshipBoardFrame extends BoardFrame {

	private static final long	serialVersionUID	= -2458982237956517265L;

	private ShipPickerPanel		picker;

	private BattleshipGame		game;

	public BattleshipBoardFrame(BattleshipGame game, int gridSize, BoardCellFactory factory1,
		BoardCellFactory factory2) {
		super(gridSize, factory1, factory2);
		this.game = game;
	}

	@Override
	protected void addExtraFirst() {
		picker = new ShipPickerPanel(game);
		add(picker);
	}

	public ShipPickerPanel getShipPicker() {
		return picker;
	}

}
