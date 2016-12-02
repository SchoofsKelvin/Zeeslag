package model;

import exception.DomainException;

public class Cell {

	public int x, y;

	public Cell(int x, int y) {
		setX(x);
		setY(y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private void setX(int x) {
		if(x<0||x>10){
			throw new DomainException("should be between 0 and 10");
		}
		this.x = x;
	}
	
	@Override
	public boolean equals(Object cell){
		if(!(cell instanceof Cell)){
			return false;
		}
		Cell c = (Cell) cell;
		return this.x == c.x && this.y == c.y;
	}

	private void setY(int y) {
		if(y<0||y>10){
			throw new DomainException("should be between 0 and 10");
		}
		this.y=y;
	}
	

}
