package view;

import model.Player;
import model.battleship.BattleshipGame;

public class App {

	public static void main(String[] args) {
		GridsizeMenu menu = new GridsizeMenu();
		String name = menu.askPlayerName();
		BattleshipGame game = new BattleshipGame(new Player(name));
	}

}
