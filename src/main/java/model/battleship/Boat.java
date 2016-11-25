package model.battleship;

public enum Boat {

	AircraftCarrier(5, "Aircraft Carrier"),
	Battleship(4, "Battleship"),
	Submarine(3, "Submarine"),
	Destroyer(3, "Destroyer"),
	PatrolShip(2, "Patrol Ship");

	public final int	length;
	public final String	fullname;

	Boat(int length, String fullname) {
		this.length = length;
		this.fullname = fullname;
	}

	@Override
	public String toString() {
		return fullname + " (" + length + ")";
	}

}
