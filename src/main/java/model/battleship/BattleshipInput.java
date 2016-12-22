package model.battleship;

import model.battleship.ai.Strategy;

public interface BattleshipInput {

	public final BattleshipInput empty = new BattleshipInput() {

		@Override
		public void updateScore() {}

		@Override
		public void reset() {}

		@Override
		public void removePickableBoat(Boat boat) {}

		@Override
		public boolean isRotationHorizontal() {
			return false;
		}

		@Override
		public boolean isFinishedPickingBoats() {
			return false;
		}

		@Override
		public Boat getPickedBoat() {
			return null;
		}

		@Override
		public Strategy createStrategy() {
			return null;
		}
	};

	public boolean isFinishedPickingBoats();

	public Boat getPickedBoat();

	public boolean isRotationHorizontal();

	public void removePickableBoat(Boat boat);

	public Strategy createStrategy();

	public void reset();

	public void updateScore();

}
