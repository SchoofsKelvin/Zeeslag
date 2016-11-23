package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardFrame extends JFrame {

	private static final long serialVersionUID = 8506260212595300722L;

	public BoardFrame(int gridSize, String name) {
		super("Battleship");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel west = new BoardPanel(name, gridSize);
		JPanel east = new BoardPanel("Computer", gridSize);
		setLayout(new BorderLayout());
		add(west, BorderLayout.LINE_START);
		add(east, BorderLayout.LINE_END);
		setVisible(true);
		validate();
		pack();
		setMinimumSize(getSize());
	}

	/**
	 * By:Surendra Sapkota Using setSize() you can give the size of frame you
	 * want but if you use pack(), it will automatically change the size of the
	 * frames according to the size of components in it. It will not consider
	 * the size you have mentioned earlier.don't use the this two method
	 * together
	 * We have a Class Named Button which extends the java Jbutton Class and
	 * implements the observer Interface ActionEventlistener Button class is the
	 * observer. I've created the variable of instance ImageIcon, if we click on
	 * grid the empty grid box will replace with the image. the method
	 * actionPerformed will call, whenever the one grid box is clicked. the
	 * method setIcon is called and replaced with the right image!
	 */

}
