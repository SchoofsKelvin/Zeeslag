package view.battleship;

import model.Player;
import model.battleship.BattleshipGame;
import model.battleship.ai.Strategy;

public class BattleshipApp {

	public static void main(String[] args) {
		BattleshipMenu menu = new BattleshipMenu();
		String name = menu.askPlayerName();
		Strategy strat = menu.askStrategy();
		try {
			new BattleshipGame(new Player(name), strat);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
