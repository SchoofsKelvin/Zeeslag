package view.battleship;

import view.BoardCellFactory;
import view.BoardFrame;

public final class BattleshipBoardFrame extends BoardFrame {

	private ShipPickerPanel picker;

	public BattleshipBoardFrame(int gridSize, BoardCellFactory factory) {
		super(gridSize, factory);
	}

	@Override
	protected void addExtraFirst() {
		picker = new ShipPickerPanel();
		add(picker);
	}

	private static final long serialVersionUID = -2458982237956517265L;

}