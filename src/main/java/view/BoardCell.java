package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoardCell extends JButton implements ActionListener {

	private static final long	serialVersionUID	= 1L;
	private ImageIcon			X, O;
	int							a					= 0;

	public BoardCell(int size) {
		X = new ImageIcon("images/x.png");
		O = new ImageIcon("images/o.png");
		addActionListener(this);
		Dimension dim = new Dimension(size, size);
		setMinimumSize(dim);
		setMaximumSize(dim);
		setPreferredSize(dim);
		setSize(dim);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		a = ++a % 3;
		switch (a) {
			case 0:
				setIcon(null);
				break;
			case 1:
				setIcon(X);
				break;
			case 2:
				setIcon(O);
				break;
		}
	}
	/**
	 * when you click on the grid box that box will call the function
	 * actionPerformed on this class.
	 */

}
