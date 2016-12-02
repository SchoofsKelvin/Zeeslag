package model.battleship;

public enum Boat {

	AircraftCarrier(5, "Aircraft Carrier", 1),
	Battleship(4, "Battleship", 2),
	Submarine(3, "Submarine", 3),
	Destroyer(3, "Destroyer", 3),
	PatrolShip(2, "Patrol Ship", 4);

	public final int	length;
	public final String	fullname;
	public final int	amount;

	Boat(int length, String fullname, int amount) {
		this.length = length;
		this.fullname = fullname;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return fullname + " (" + length + ")";
	}

}
