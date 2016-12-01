package view.battleship;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import exception.DomainException;
import model.battleship.BattleshipBoard;
import model.battleship.BattleshipCell;
import view.BoardCell;

public class BattleshipBoardCell extends BoardCell implements ActionListener {

	private static final long		serialVersionUID	= -3288098740775513486L;
	public static final ImageIcon	X					= new ImageIcon("images/x.png");
	public static final ImageIcon	O					= new ImageIcon("images/o.png");

	public final BattleshipBoard	board;

	private boolean					other;

	public BattleshipBoardCell(int x, int y, int size, BattleshipBoard board, boolean other) {
		super(x, y, size);
		this.board = board;
		this.other = other;
		addActionListener(this);
		setColor(CellColor.Empty);
	}

	@Override
	public void updateCell() throws DomainException {
		BattleshipCell cell = board.getCell(x, y);
		if (cell.hasBoat()) {
			setColor(cell.isShot() ? CellColor.Shot : CellColor.Empty);
		} else {
			setColor(cell.isShot() ? CellColor.Hit : CellColor.Boat);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		board.clickedCell(x, y, other);
	}

	public void setColor(CellColor c) {
		setBackground(c.color);
	}

	public enum CellColor {
		Empty(new Color(100, 197, 255)), Boat(Color.WHITE), Hit(Color.RED), Shot(Color.GRAY);

		public final Color color;

		CellColor(Color col) {
			this.color = col;
		}
	}

}
