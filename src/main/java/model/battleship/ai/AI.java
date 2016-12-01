package model.battleship.ai;

import exception.DomainException;
import model.Player;
import model.battleship.BattleshipBoard;
import model.battleship.BattleshipGame;

public class AI extends Player {

	private Strategy strategy = new RandomStrategy();

	public AI() throws DomainException {
		super("Computer");
	}

	public AI(Strategy strategy) {
		this();
		setStrategy(strategy);
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public void placeBoats(BattleshipBoard board) {
		strategy.placeBoats(board);
	}

	public void doTurn(BattleshipGame game) {
		strategy.doTurn(game);
	}

}
