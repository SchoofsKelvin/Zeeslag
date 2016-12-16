package model.battleship;

import model.Cell;

public class BattleshipCell extends Cell {

	private BattleshipCellState state = BattleshipCellState.EMPTY;

	public BattleshipCell(int x, int y) {
		super(x, y);
	}

	public boolean hasBoat() {
		return state.hasBoat;
	}

	public boolean isShot() {
		return !state.canShoot;
	}

	public void setBoat(boolean boat) {
		if (isShot()) {
			state = boat ? BattleshipCellState.HIT : BattleshipCellState.SHOT;
		} else {
			state = boat ? BattleshipCellState.BOAT : BattleshipCellState.EMPTY;
		}
		
	}

	public void setShot(boolean shot) {
		if (shot) {
			state = hasBoat() ? BattleshipCellState.HIT : BattleshipCellState.SHOT;
		} else {
			state = hasBoat() ? BattleshipCellState.BOAT : BattleshipCellState.EMPTY;
		}
	}

	@Override
	public String toString() {
		return "BattleshipCell(" + x + ", " + y + ", " + state + ")";
	}

}
