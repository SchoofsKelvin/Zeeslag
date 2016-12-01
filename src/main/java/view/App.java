package view;

import model.Player;
import model.battleship.BattleshipGame;
import model.battleship.ai.Strategy;

public class App {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		GridsizeMenu menu = new GridsizeMenu();
		String name = menu.askPlayerName();
		Strategy strat = menu.askStrategy();
		try {
			BattleshipGame game = new BattleshipGame(new Player(name), strat);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
