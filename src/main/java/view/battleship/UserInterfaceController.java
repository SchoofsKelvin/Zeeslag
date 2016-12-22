package view.battleship;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;

import model.Player;
import model.battleship.*;
import model.battleship.ai.AllSeeingEnemyStrategy;
import model.battleship.ai.FollowUpStrategy;
import model.battleship.ai.RandomStrategy;
import model.battleship.ai.Strategy;
import model.battleship.ai.Strategy.StrategyBuilder;

public class UserInterfaceController implements BattleshipInput {

	private BattleshipGame						game;
	private BattleshipBoardFrame				frame;

	private final ArrayList<StrategyBuilder>	strategies	= new ArrayList<>();
	private final Properties					props;
	private final boolean						isFirstPlayer;
	private StrategyBuilder						strategy;
	private static final File					file		=
		new File("battleship.properties");

	public UserInterfaceController(BattleshipGame game, boolean isFirstPlayer) {
		props = new Properties();
		if (file.exists()) {
			try {
				props.load(new FileInputStream(file));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		addStrategyFactory(RandomStrategy.builder);
		addStrategyFactory(FollowUpStrategy.builder);
		addStrategyFactory(AllSeeingEnemyStrategy.builder);
		this.game = game;
		this.isFirstPlayer = isFirstPlayer;
		BattleshipBoard leftBoard = isFirstPlayer ? game.board1 : game.board2;
		BattleshipBoard rightBoard = isFirstPlayer ? game.board2 : game.board1;
		Player leftPlayer = isFirstPlayer ? game.player1 : game.player2;
		Player rightPlayer = isFirstPlayer ? game.player2 : game.player1;
		frame = new BattleshipBoardFrame(BattleshipGame.gridSize,
			(x, y, buttonsize) -> new BattleshipBoardCell(x, y, buttonsize,
				(x1, y1) -> leftBoard.clickedCell(x1, y1, false)),
			(x, y, buttonsize) -> new BattleshipBoardCell(x, y, buttonsize,
				(x1, y1) -> rightBoard.clickedCell(x1, y1, true)));
		frame.setLeftName(leftPlayer.getName() + " (" + leftPlayer.getScore() + ")");
		frame.setRightName(rightPlayer.getName() + " (" + rightPlayer.getScore() + ")");
		frame.addGameStartedListener(game::startGame);
		frame.addGameResettedListener(game::resetGame);
		game.addGameReadyChangedListener(frame.getShipPicker());
		game.board1.addObserver(this::cellUpdated);
		game.board2.addObserver(this::cellUpdated);
	}

	@Override
	public Boat getPickedBoat() {
		return frame.getShipPicker().getBoat();
	}

	@Override
	public boolean isFinishedPickingBoats() {
		return frame.getShipPicker().isFinished();
	}

	@Override
	public boolean isRotationHorizontal() {
		return frame.getShipPicker().rotationIsHorizontal();
	}

	@Override
	public void removePickableBoat(Boat boat) {
		frame.getShipPicker().removeBoat(boat);
	}

	@Override
	public void reset() {
		frame.resetBoards();
		frame.getShipPicker().reset();
		updateScore();
	}

	public void cellUpdated(int x, int y) {
		BattleshipBoard leftBoard = isFirstPlayer ? game.board1 : game.board2;
		BattleshipBoard rightBoard = isFirstPlayer ? game.board2 : game.board1;
		BattleshipCell cellA = leftBoard.getCell(x, y);
		BattleshipCell cellB = rightBoard.getCell(x, y);
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
			return cell.hasBoat() && !other ? CellColor.Boat : CellColor.Empty;
	}

	@Override
	public void updateScore() {
		Player leftPlayer = isFirstPlayer ? game.player1 : game.player2;
		Player rightPlayer = isFirstPlayer ? game.player2 : game.player1;
		frame.setLeftName(leftPlayer.getName() + " (" + leftPlayer.getScore() + ")");
		frame.setRightName(rightPlayer.getName() + " (" + rightPlayer.getScore() + ")");
	}

	@Override
	public Strategy createStrategy() {
		return strategy.build();
	}

	public String askPlayerName() {
		String name = "";
		while (name == null || name.trim().isEmpty() || name.length() < 3
			|| name.length() > 9) {
			name = JOptionPane.showInputDialog(null, "What is your name?");
		}
		return name;
	}

	public void askStrategy() {
		int key = Integer.parseInt(props.getProperty("strategy", "0"));
		StrategyBuilder fac = (StrategyBuilder) JOptionPane.showInputDialog(null,
			"Which strategy for the AI do you want to use?", "Choose an AI Strategy",
			JOptionPane.QUESTION_MESSAGE, null, strategies.toArray(), strategies.get(key));
		if (fac != null) {
			key = strategies.indexOf(fac);
			props.setProperty("strategy", key + "");
			save();
		}
		strategy = fac == null ? RandomStrategy.builder : fac;
	}

	private void save() {
		try {
			props.store(new FileWriter(file), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addStrategyFactory(StrategyBuilder factory) {
		strategies.add(factory);
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

}
