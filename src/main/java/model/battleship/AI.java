package model.battleship;

import java.util.Random;

import exception.DomainException;
import model.Player;

public class AI extends Player {

	private final static Random random = new Random();

	public AI() throws DomainException {
		super("Computer");
	}

	public void placeBoats(BattleshipBoard board) {
		int size = board.getGridSize();
		for (Boat boat : Boat.values()) {
			while (true) {
				int x = random.nextInt(size);
				int y = random.nextInt(size);
				boolean h = random.nextBoolean();
				if (board.canPlaceBoat(boat, h, board.getCell(x, y))) {
					board.placeBoat(boat, h, board.getCell(x, y));
					break;
				}
			}
		}
	}

	public void doTurn(BattleshipGame game) {
		int size = BattleshipGame.gridSize;
		while (true) {
			int x = random.nextInt(size);
			int y = random.nextInt(size);
			if (game.shoot(x, y)) {
				break;
			}
		}
	}

}
