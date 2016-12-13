package model;

public class Cell {

	public final int x, y;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object cell) {
		if ( !(cell instanceof Cell)) return false;
		Cell c = (Cell) cell;
		return x == c.x && y == c.y;
	}
	
	public int hashCode(){
		return x%13+y%13;
	}

}
