package view.battleship;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import model.battleship.BattleshipGame;
import view.BoardCell;

public class BattleshipBoardCell extends BoardCell implements ActionListener {

	private static final long		serialVersionUID	= -3288098740775513486L;
	private static final ImageIcon	X					= new ImageIcon("images/x.png");
	private static final ImageIcon	O					= new ImageIcon("images/o.png");

	private int						a					= 0;

	private final BattleshipGame	game;

	public BattleshipBoardCell(BattleshipGame game, int size) {
		super(size);
		this.game = game;
		addActionListener(this);
		setColor(CellColor.Empty);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		a = ++a % 3;
		switch (a) {
			case 0:
				// setIcon(null);
				setBackground(Color.GRAY);
				break;
			case 1:
				// setIcon(X);
				setBackground(Color.GREEN);
				break;
			case 2:
				// setIcon(O);
				setBackground(Color.WHITE);
				break;
		}
	}

	public void setColor(CellColor c) {
		setBackground(c.color);
	}

	public enum CellColor {
		Empty(Color.GRAY), Ship(Color.WHITE), Hit(Color.RED), Shot(Color.DARK_GRAY);

		public final Color color;

		CellColor(Color col) {
			this.color = col;
		}
	}

}
