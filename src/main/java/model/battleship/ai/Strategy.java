package model.battleship.ai;
/**
 * @author Kelvin Schoofs, Surendra Sapkota
 *
 */
import model.battleship.BattleshipBoard;
import model.battleship.BattleshipGame;

public interface Strategy {

	public void placeBoats(BattleshipBoard board);

	public void doTurn(BattleshipGame game);

	public static abstract class StrategyBuilder {

		private final String name;

		public abstract Strategy build();

		public StrategyBuilder(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}

	}
}
