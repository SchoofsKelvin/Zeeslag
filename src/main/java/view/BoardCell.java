package view;

import java.awt.Dimension;

import javax.swing.JButton;

public class BoardCell extends JButton {

	private static final long serialVersionUID = 1L;

	public BoardCell(int size) {
		Dimension dim = new Dimension(size, size);
		setMinimumSize(dim);
		setMaximumSize(dim);
		setPreferredSize(dim);
		setSize(dim);
	}

	public void updateCell() {}

}
