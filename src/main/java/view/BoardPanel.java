package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import exception.DomainException;

public class BoardPanel extends JPanel {

	private static final long	serialVersionUID	= 1L;

	private static final int	buttonSize			= 30;

	private final BoardCell[][]	cells;
	private final JLabel		nametag;

	private BoardCellFactory	factory;

	private int					gridSize;

	private JPanel				holder;

	public BoardPanel(String name, int gridSize, BoardCellFactory factory) {
		cells = new BoardCell[gridSize][gridSize];
		nametag = new JLabel(name);
		this.gridSize = gridSize;
		this.factory = factory;
		setLayout(new BorderLayout());
		add(nametag);
		setSize(gridSize * buttonSize, gridSize * buttonSize);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		holder = new JPanel();
		add(holder, BorderLayout.SOUTH);
		holder.setLayout(new GridBagLayout());
		resetBoard();
		validate();
	}

	public void resetBoard() {
		holder.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0 / gridSize;
		c.weighty = 1.0 / (gridSize + 1);
		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				BoardCell b = factory.createCell(x, y, buttonSize);
				cells[x][y] = b;
				c.gridy = y + 1;
				holder.add(b, c);
			}
		}
		holder.revalidate();
	}

	public BoardCell getCell(int x, int y) throws DomainException {
		if (x < 0 || x >= cells.length) throw new DomainException("Invalid X");
		if (y < 0 || y >= cells.length) throw new DomainException("Invalid Y");
		return cells[x][y];
	}

	@Override
	public void setName(String name) {
		nametag.setText(name);
	}

}
