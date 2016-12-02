package model;

import exception.DomainException;

public class Cell {

	public int x, y;

	public Cell(int x, int y) {
		setX(x);
		setY(y);
	}
	
	@Override
	public boolean equals(Object cell){
		if(!(cell instanceof Cell)){
			return false;
		}
		Cell c = (Cell) cell;
		return this.x == c.x && this.y == c.y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private void setX(int x) {
		
		this.x = x;
	}

	private void setY(int y) {
		
		this.y=y;
	}
	

}
