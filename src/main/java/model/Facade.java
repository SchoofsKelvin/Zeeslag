package model;

import model.battleship.BattleshipGame;

public class Facade {
	
	private BattleshipGame game;
	private Player player;
	
	public Facade(String name, String strategy){
		player = new Player(name);
		StrategyFactory fac = new StrategyFactory();//TODO
		game = new BattleshipGame(player, fac.create(strategy));
	}
	
	public void click(int x, int y, boolean player1Board){
		//TODO
	}

}
