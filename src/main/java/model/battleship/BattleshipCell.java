package model.battleship;

import model.Cell;

public class BattleshipCell extends Cell {

	private boolean	shot;
	private Boat	boat;

	public BattleshipCell(int x, int y) {
		super(x, y);
	}

	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	public boolean isShot() {
		return shot;
	}

	public void setShot(boolean shot) {
		this.shot = shot;
	}

}
