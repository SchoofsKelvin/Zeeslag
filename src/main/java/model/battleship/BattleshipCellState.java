package model.battleship;

public enum BattleshipCellState {

	EMPTY(false, false), SHOT(true, false), BOAT(false, true), HIT(true, true);

	public final boolean	canShoot;
	public final boolean	hasBoat;

	BattleshipCellState(boolean canShoot, boolean hasBoat) {
		this.canShoot = canShoot;
		this.hasBoat = hasBoat;
	}
}
