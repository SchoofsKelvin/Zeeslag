package model.battleship;

import exception.DomainException;
import model.Board;
import model.Cell;

public class BattleshipBoard extends Board {

	@Override
	protected Cell createCell(int x, int y) {
		return new BattleshipCell(x, y);
	}

	@Override
	public BattleshipCell getCell(int x, int y) throws DomainException {
		return (BattleshipCell) super.getCell(x, y);
	}

}
