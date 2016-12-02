package model.battleship.ai;

import model.battleship.BattleshipBoard;
import model.battleship.BattleshipGame;

public interface Strategy {

	public void placeBoats(BattleshipBoard board);

	public void doTurn(BattleshipGame game);

	public static abstract class StrategyFactory {

		private final String name;

		public abstract Strategy create();

		public StrategyFactory(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}

	}
}
