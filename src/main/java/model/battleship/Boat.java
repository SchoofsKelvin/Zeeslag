package model.battleship;

public enum Boat {

	AircraftCarrier(5), Battleship(4), Submarine(3), Destroyer(3), PatrolShip(2);

	public final int length;

	Boat(int length) {
		this.length = length;
	}

}
