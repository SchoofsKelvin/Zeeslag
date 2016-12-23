package model;

import java.util.ArrayList;

import exception.DomainException;
import model.listener.CellUpdatedListener;
/**
 * @author Kelvin Schoofs, Surendra Sapkota
 *
 */
public abstract class Board {

	private Cell[][]						cells;
	private ArrayList<CellUpdatedListener>	listeners	= new ArrayList<>();

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

	public void addObserver(CellUpdatedListener listener) {
		listeners.add(listener);
	}

	public void removeObserver(CellUpdatedListener listener) {
		listeners.remove(listener);
	}

	public void fireCellUpdated(int x, int y) {
		for (CellUpdatedListener observer : listeners) {
			observer.cellUpdated(x, y);
		}
	}

}
