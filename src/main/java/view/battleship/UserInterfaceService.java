package view.battleship;

import model.battleship.BattleshipGame;
import model.battleship.Boat;

public class UserInterfaceService {

	private BattleshipGame			game;
	private BattleshipBoardFrame	frame;

	public UserInterfaceService(BattleshipGame game) {
		this.game = game;
		frame = new BattleshipBoardFrame(BattleshipGame.gridSize,
			(x, y, buttonsize) -> new BattleshipBoardCell(x, y, buttonsize, game.board1,
				false),
			(x, y, buttonsize) -> new BattleshipBoardCell(x, y, buttonsize, game.board2,
				true));
		frame.setLeftName(game.player1.getName());
		frame.setRightName(game.player2.getName());
		frame.addGameStartedListener(game::startGame);
		frame.addGameResettedListener(game::resetGame);
		game.board1.addObserver(frame.left);
		game.board2.addObserver(frame.right);
	}

	public BattleshipGame getGame() {
		return game;
	}

	public Boat getPickedBoat() {
		return frame.getShipPicker().getBoat();
	}

	public boolean isFinishedPickingBoats() {
		return frame.getShipPicker().isFinished();
	}

	public boolean isRotationHorizontal() {
		return frame.getShipPicker().rotationIsHorizontal();
	}

	public void removePickableBoat(Boat boat) {
		frame.getShipPicker().removeBoat(boat);
	}

	public void reset() {
		frame.resetBoards();
		frame.getShipPicker().reset();
	}

}
