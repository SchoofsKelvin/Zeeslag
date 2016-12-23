package view;

import java.awt.Dimension;

import javax.swing.JButton;
/**
 * @author Thomas Goris, Kelvin Schoofs, Surendra Sapkota
 *
 */
public class BoardCell extends JButton {

	private static final long	serialVersionUID	= 1L;

	public final int			x, y;

	public BoardCell(int x, int y, int size) {
		Dimension dim = new Dimension(size, size);
		setMinimumSize(dim);
		setMaximumSize(dim);
		setPreferredSize(dim);
		setSize(dim);
		this.x = x;
		this.y = y;
		
	}

}
