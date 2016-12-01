package model;

import java.util.ArrayList;

import exception.DomainException;

public abstract class Board {

	private Cell[][]						cells;
	private ArrayList<CellUpdatedObserver>	observers	= new ArrayList<>();

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

	public int getGridSize() {
		return cells.length;
	}

	protected abstract Cell createCell(int x, int y);

	public void addObserver(CellUpdatedObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(CellUpdatedObserver observer) {
		observers.remove(observer);
	}

	public void fireCellUpdated(int x, int y) {
		for (CellUpdatedObserver observer : observers) {
			observer.cellUpdated(x, y);
		}
	}

}
