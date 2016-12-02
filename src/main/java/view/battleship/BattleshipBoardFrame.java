package view.battleship;

import java.util.ArrayList;
import java.util.List;

import model.battleship.BattleshipGame;
import view.BoardCellFactory;
import view.BoardFrame;

public final class BattleshipBoardFrame extends BoardFrame {

	private static final long	serialVersionUID	= -2458982237956517265L;

	private ShipPickerPanel		picker;

	private List<GameStartedListener> listeners = new ArrayList<>();

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
	
	public void addGameStartedListener(GameStartedListener gamelistener){
		listeners.add(gamelistener);
	}
	
	public void fireGameStarted(){
		for(GameStartedListener listener: listeners){
			listener.gameStarted();
		}
	}

}
