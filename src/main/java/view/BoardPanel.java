package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

	private static final long	serialVersionUID	= 1L;

	private BoardCell[][]		cells;
	private static final int	buttonSize			= 50;

	BoardPanel(String name, int gridSize) {
		cells = new BoardCell[gridSize][gridSize];
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		JLabel nametag = new JLabel(name);
		add(nametag, c);
		c.weightx = 1 / gridSize;
		c.weighty = 1 / (gridSize + 1);
		setSize(gridSize * buttonSize, gridSize * buttonSize);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				BoardCell b = new BoardCell(buttonSize);
				cells[x][y] = b;
				c.gridy = y + 1;
				add(b, c);
			}
		}
		validate();
	}

}
