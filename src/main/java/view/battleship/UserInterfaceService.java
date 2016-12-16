package view.battleship;

import model.battleship.BattleshipCell;
import model.battleship.BattleshipGame;
import model.battleship.Boat;

public class UserInterfaceService {

	private BattleshipGame			game;
	private BattleshipBoardFrame	frame;

	public UserInterfaceService(BattleshipGame game) {
		this.game = game;
		frame = new BattleshipBoardFrame(BattleshipGame.gridSize,
			(x, y, buttonsize) -> new BattleshipBoardCell(x, y, buttonsize,
				(x1, y1) -> game.board1.clickedCell(x1, y1, false)),
			(x, y, buttonsize) -> new BattleshipBoardCell(x, y, buttonsize,
				(x1, y1) -> game.board2.clickedCell(x1, y1, true)));
		frame.setLeftName(game.player1.getName() + " (" + game.player1.getScore() + ")");
		frame.setRightName(game.player2.getName() + " (" + game.player2.getScore() + ")");
		frame.addGameStartedListener(game::startGame);
		frame.addGameResettedListener(game::resetGame);
		// game.board1.addObserver(frame.left);
		// game.board2.addObserver(frame.right);
		game.board1.addObserver(this::cellUpdated);
		game.board2.addObserver(this::cellUpdated);
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
		updateScore();
	}

	public void cellUpdated(int x, int y) {
		BattleshipCell cellA = game.board1.getCell(x, y);
		BattleshipCell cellB = game.board2.getCell(x, y);
		BattleshipBoardCell targetA = (BattleshipBoardCell) frame.left.getCell(x, y);
		BattleshipBoardCell targetB = (BattleshipBoardCell) frame.right.getCell(x, y);
		targetA.setColor(calculateCellColor(cellA, false));
		targetB.setColor(calculateCellColor(cellB, true));
	}

	public CellColor calculateCellColor(BattleshipCell cell, boolean other) {
		if (cell.hasDeadBoat())
			return CellColor.Dead;
		else if (cell.isShot())
			return cell.hasBoat() ? CellColor.Hit : CellColor.Shot;
		else
			return (cell.hasBoat() && !other) ? CellColor.Boat : CellColor.Empty;
	}

	public void updateScore() {
		frame.setLeftName(game.player1.getName() + " (" + game.player1.getScore() + ")");
		frame.setRightName(game.player2.getName() + " (" + game.player2.getScore() + ")");
	}

}
