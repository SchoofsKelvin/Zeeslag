package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import exception.DomainException;
import model.CellUpdatedObserver;

public class BoardPanel extends JPanel implements CellUpdatedObserver {

	private static final long	serialVersionUID	= 1L;

	private static final int	buttonSize			= 30;

	private final BoardCell[][]	cells;
	private final JLabel		nametag;

	public BoardPanel(String name, int gridSize, BoardCellFactory factory) {
		cells = new BoardCell[gridSize][gridSize];
		nametag = new JLabel(name);
		setLayout(new GridBagLayout());
		add(nametag);
		JPanel holder = new JPanel();
		add(holder);
		holder.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1 / gridSize;
		c.weighty = 1 / (gridSize + 1);
		setSize(gridSize * buttonSize, gridSize * buttonSize);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				BoardCell b = factory.createCell(x, y, buttonSize);
				cells[x][y] = b;
				c.gridy = y + 1;
				holder.add(b, c);
			}
		}
		validate();
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

	public void updateCell(int x, int y) throws DomainException {
		BoardCell cell = getCell(x, y);
		cell.updateCell();
	}

	@Override
	public void cellUpdated(int x, int y) {
		System.out.println("cellUpdated " + x + ", " + y + " for " + nametag.getText());
		updateCell(x, y);
	}

}
