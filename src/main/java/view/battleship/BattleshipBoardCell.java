package view.battleship;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.listener.CellClickedListener;
import view.BoardCell;
/**
 * @author Kelvin Schoofs, Surendra Sapkota
 *
 */
public class BattleshipBoardCell extends BoardCell implements ActionListener {

	private static final long			serialVersionUID	= -3288098740775513486L;

	public final CellClickedListener	listener;

	public BattleshipBoardCell(int x, int y, int size, CellClickedListener listener) {
		super(x, y, size);
		this.listener = listener;
		addActionListener(this);
		setColor(CellColor.Empty);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		listener.clickedCell(x, y);
	}

	public void setColor(CellColor c) {
		setBackground(c.color);
	}

}
