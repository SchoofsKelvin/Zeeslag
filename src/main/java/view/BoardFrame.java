package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class BoardFrame extends JFrame {

	private static final long serialVersionUID = 8506260212595300722L;

	public final BoardPanel left, right;

	public BoardFrame(int gridSize, BoardCellFactory factory) {
		super("Battleship");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		left = new BoardPanel("Player", gridSize, factory);
		right = new BoardPanel("Computer", gridSize, factory);
		setLayout(new BorderLayout());
		add(left, BorderLayout.LINE_START);
		add(right, BorderLayout.LINE_END);
		setVisible(true);
		validate();
		pack();
		setMinimumSize(getSize());
	}

	public void setLeftName(String name) {
		left.setName(name);
	}

	public void setRightName(String name) {
		right.setName(name);
	}

	/**
	 * Using setSize() you can give the size of frame you
	 * want but if you use pack(), it will automatically change the size of the
	 * frames according to the size of components in it. It will not consider
	 * the size you have mentioned earlier.don't use the this two method
	 * together We have a Class Named Button which extends the java Jbutton
	 * Class and implements the observer Interface ActionEventlistener Button
	 * class is the observer. I've created the variable of instance ImageIcon,
	 * if we click on grid the empty grid box will replace with the image. the
	 * method actionPerformed will call, whenever the one grid box is clicked.
	 * the method setIcon is called and replaced with the right image!
	 */

}
