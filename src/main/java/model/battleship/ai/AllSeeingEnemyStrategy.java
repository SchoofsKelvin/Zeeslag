package model.battleship.ai;

import model.battleship.BattleshipBoard;
import model.battleship.BattleshipCell;
import model.battleship.BattleshipGame;

public class AllSeeingEnemyStrategy extends RandomStrategy {

	public static final AllSeeingEnemyStrategy singleton = new AllSeeingEnemyStrategy();

	@Override
	public void doTurn(BattleshipGame game) {
		BattleshipBoard board = game.board1;
		int size = BattleshipGame.gridSize;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				BattleshipCell cell = board.getCell(x, y);
				if (cell.hasBoat() && !cell.isShot()) {
					if (game.shoot(x, y)) return;
				}
			}
		}
	}

	@Override
	public String toString() {
		return "All Seeing Enemy Strategy";
	}

}
