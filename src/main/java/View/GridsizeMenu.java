package View;


import javax.swing.JOptionPane;

public class GridsizeMenu {
	private String[] gridSize = { "5", "9", "10", "20" };

	public GridsizeMenu() {
	}

	public int getGridSize() {
		String size = (String) JOptionPane.showInputDialog(null, "On which grid size, do you want to play?",
				"Choose the Grid Size", JOptionPane.QUESTION_MESSAGE, null, gridSize, gridSize[2]);
		return Integer.parseInt(size);
	}

	public String askPlayerName() {
		String name = "";
		while (name.trim().isEmpty() || name.length() < 3) {
			name = JOptionPane.showInputDialog(null, "What is your name?");
		}
		return name;
	}

	/**
	 * By Surendra Sapkota: this class is used to create a scroll menu!
	 */
}
