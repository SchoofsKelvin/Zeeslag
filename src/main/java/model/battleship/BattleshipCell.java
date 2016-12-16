package model.battleship;

import model.Cell;

public class BattleshipCell extends Cell {

	private BattleshipCellState	state	= BattleshipCellState.EMPTY;
	private PlacedBoat			placedBoat;

	public BattleshipCell(int x, int y) {
		super(x, y);
	}

	public boolean hasBoat() {
		return state.hasBoat;
	}

	public boolean isShot() {
		return !state.canShoot;
	}

	public void setBoat(PlacedBoat boat) {
		placedBoat = boat;
		state = BattleshipCellState.BOAT;
	}

	public void setShot(boolean shot) {
		if (shot) {
			state = hasBoat() ? BattleshipCellState.HIT : BattleshipCellState.SHOT;
			if (hasDeadBoat()) {
				for (BattleshipCell c : placedBoat.getCells()) {
					placedBoat.board.fireCellUpdated(c.x, c.y);
				}
			}
		} else {
			state = hasBoat() ? BattleshipCellState.BOAT : BattleshipCellState.EMPTY;
		}
	}

	@Override
	public String toString() {
		return "BattleshipCell(" + x + ", " + y + ", " + state + ")";
	}

	public boolean hasDeadBoat() {
		return hasBoat() && placedBoat.isDead();
	}
	
}
