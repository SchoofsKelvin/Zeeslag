package model.battleship;

import model.battleship.ai.Strategy;

public interface BattleshipInput {

	public boolean isFinishedPickingBoats();

	public Boat getPickedBoat();

	public boolean isRotationHorizontal();

	public void removePickableBoat(Boat boat);

	public Strategy createStrategy();

	public void reset();

	public void updateScore();

}
