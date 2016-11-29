package model;

import exception.DomainException;

public abstract class Board {

	private Cell[][] cells;

	public void resetBoard(int gridSize) {
		cells = new Cell[gridSize][gridSize];
		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				cells[x][y] = createCell(x, y);
			}
		}
	}
	public Cell getCell(int x, int y) throws DomainException {
		if (x < 0 || x >= cells.length) throw new DomainException("Invalid X");
		if (y < 0 || y >= cells.length) throw new DomainException("Invalid Y");
		return cells[x][y];
	}

	protected abstract Cell createCell(int x, int y);

	
}
