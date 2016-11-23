package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardFrame extends JFrame {

	private static final long	serialVersionUID	= 8506260212595300722L;
	private List<BoardCell>		buttons;
	private static final int	buttonSize			= 50;

	public BoardFrame(int gridSize, String name) {
		super("Battleship");
		// setSize(new Dimension(800, 400));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel player1 = new JLabel(name);
		JLabel player2 = new JLabel("Computer");

		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		header.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		header.add(player1, BorderLayout.WEST);
		header.add(player2, BorderLayout.EAST);

		JPanel west = createGrid(gridSize);
		JPanel east = createGrid(gridSize);
		setLayout(new BorderLayout());
		add(west, BorderLayout.LINE_START);
		add(east, BorderLayout.LINE_END);
		add(header, BorderLayout.PAGE_START);
		setVisible(true);
		validate();
		pack();
	}

	public JPanel createGrid(int gridSize) {
		JPanel panel = new JPanel();
		buttons = new ArrayList<>(gridSize * gridSize);
		panel.setLayout(new GridLayout(gridSize, gridSize));
		panel.setSize(gridSize * buttonSize, gridSize * buttonSize);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		for (int i = 0; i < gridSize * gridSize; i++) {
			BoardCell b = new BoardCell(buttonSize);
			buttons.add(b);
			panel.add(b);
		}
		return panel;
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
