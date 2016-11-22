package View;


public class App {

	public static void main(String[] args) {
		GridsizeMenu menu = new GridsizeMenu();
		String name = menu.askPlayerName();
		int size = menu.getGridSize();
		Board board = new Board(size,name);
	}

}
