package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

	private static final long	serialVersionUID	= 1L;

	private static final int	buttonSize			= 50;

	private final BoardCell[][]	cells;
	private final JLabel		nametag;

	BoardPanel(String name, int gridSize, BoardCellFactory factory) {
		cells = new BoardCell[gridSize][gridSize];
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		nametag = new JLabel(name);
		add(nametag, c);
		c.weightx = 1 / gridSize;
		c.weighty = 1 / (gridSize + 1);
		setSize(gridSize * buttonSize, gridSize * buttonSize);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				BoardCell b = factory.createCell(buttonSize);
				cells[x][y] = b;
				c.gridy = y + 1;
				add(b, c);
			}
		}
		validate();
	}

	public BoardCell getCell(int x, int y) {
		if (x < 0 || x >= cells.length) throw new IllegalArgumentException("Invalid X");
		if (y < 0 || y >= cells.length) throw new IllegalArgumentException("Invalid Y");
		return cells[x][y];
	}

	@Override
	public void setName(String name) {
		nametag.setText(name);
	}

}
