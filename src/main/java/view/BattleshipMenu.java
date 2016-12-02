package view;

import javax.swing.JOptionPane;

import model.battleship.ai.AllSeeingEnemyStrategy;
import model.battleship.ai.RandomStrategy;
import model.battleship.ai.Strategy;

public class BattleshipMenu {

	private final static String[]	gridSize	= { "5", "9", "10", "20" };
	private final static Strategy[]	strategies	=
		{ RandomStrategy.singleton, AllSeeingEnemyStrategy.singleton };

	public BattleshipMenu() {}

	public int getGridSize() {
		String size = (String) JOptionPane.showInputDialog(null,
			"On which grid size, do you want to play?", "Choose the Grid Size",
			JOptionPane.QUESTION_MESSAGE, null, gridSize, gridSize[2]);
		return Integer.parseInt(size);
	}

	public String askPlayerName() {
		String name = "";
		while (name == null || name.trim().isEmpty() || name.length() < 3
			|| name.length() > 9) {
			name = JOptionPane.showInputDialog(null, "What is your name?");
		}
		return name;
	}

	public Strategy askStrategy() {
		return (Strategy) JOptionPane.showInputDialog(null,
			"Which strategy for the AI do you want to use?", "Choose an AI Strategy",
			JOptionPane.QUESTION_MESSAGE, null, strategies, strategies[0]);
	}
}
