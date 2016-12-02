package model.battleship.ai;

import java.util.Random;

import model.battleship.BattleshipBoard;
import model.battleship.BattleshipGame;
import model.battleship.Boat;

public class RandomStrategy implements Strategy {

	public static final StrategyFactory	factory	= new StrategyFactory("Random Strategy") {

													@Override
													public Strategy create() {
														return new RandomStrategy();
													}
												};

	private final static Random			random	= new Random();

	@Override
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

	@Override
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

	@Override
	public String toString() {
		return "Random Strategy";
	}

}
