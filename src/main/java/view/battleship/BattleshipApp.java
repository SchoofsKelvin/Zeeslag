package view.battleship;

import javax.swing.JOptionPane;

import model.Player;
import model.battleship.BattleshipGame;

/**
 * @author Kelvin Schoofs, Surendra Sapkota
 *
 */
public class BattleshipApp {

	public static void main(String[] args) {
		int res = JOptionPane.showOptionDialog(null, "How many players?", "Battleship",
			JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
			new Object[] { "1 player", "2 players" }, null);
		switch (res) {
			case 0:
				onePlayer();
				return;
			case 1:
				twoPlayers();
				return;
		}
	}

	public static void onePlayer() {
		BattleshipGame game = new BattleshipGame(new Player());
		UserInterfaceController uis = new UserInterfaceController(game, true);
		game.player1.setName(uis.askPlayerName());
		uis.askStrategy();
		game.setInput(uis);
		game.resetGame();
		uis.setVisible(true);
	}

	public static void twoPlayers() {
		BattleshipGame game = new BattleshipGame(new Player(), new Player());
		UserInterfaceController uis1 = new UserInterfaceController(game, true);
		UserInterfaceController uis2 = new UserInterfaceController(game, false);
		game.player1.setName(uis1.askPlayerName());
		game.player2.setName(uis2.askPlayerName());
		game.setInput(uis1, uis2);
		game.resetGame();
		uis1.setVisible(true);
		uis2.setVisible(true);
	}

}
