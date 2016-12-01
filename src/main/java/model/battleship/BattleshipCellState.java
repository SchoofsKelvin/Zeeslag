package model.battleship;

public enum BattleshipCellState {

	EMPTY(true, false), SHOT(false, false), BOAT(true, true), HIT(false, true);

	public final boolean	canShoot;
	public final boolean	hasBoat;

	BattleshipCellState(boolean canShoot, boolean hasBoat) {
		this.canShoot = canShoot;
		this.hasBoat = hasBoat;
	}
}
