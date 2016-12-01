package model.battleship.ai;

import model.battleship.BattleshipBoard;
import model.battleship.BattleshipGame;

public interface Strategy {

	public void placeBoats(BattleshipBoard board);

	public void doTurn(BattleshipGame game);
}
