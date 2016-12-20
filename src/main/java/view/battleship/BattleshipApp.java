package view.battleship;

import model.Player;
import model.battleship.BattleshipGame;

public class BattleshipApp {

	public static UserInterfaceController	uis;
	public static BattleshipGame			game;

	public static void main(String[] args) {
		try {
			game = new BattleshipGame(new Player("Player"));
			uis = new UserInterfaceController(game);
			game.player1.setName(uis.askPlayerName());
			game.setInput(uis);
			game.resetGame();
			uis.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
