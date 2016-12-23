package model.battleship.ai;

import model.battleship.BattleshipGame;

public class DumbAIStrategy extends RandomStrategy {

	public static final StrategyBuilder	builder	= new StrategyBuilder("Dumb AI") {

													@Override
													public Strategy build() {
														return new DumbAIStrategy();
													}
												};

	private int							p		= 0;

	@Override
	public void doTurn(BattleshipGame game) {
		int size = BattleshipGame.gridSize;
		game.shoot(p % size, p++ / size);
	}

}
