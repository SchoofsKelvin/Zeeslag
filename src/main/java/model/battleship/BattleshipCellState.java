package model.battleship;

public enum BattleshipCellState {

	EMPTY(false, false), SHOT(true, false), BOAT(true, true), HIT(false, true);

	public final boolean	canShoot;
	public final boolean	hasBoat;

	BattleshipCellState(boolean canShoot, boolean hasBoat) {
		this.canShoot = canShoot;
		this.hasBoat = hasBoat;
	}
}
