package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JFrame {

	private static final long serialVersionUID = 8506260212595300722L;
	private List<Button> buttons = new ArrayList<>();
	private JPanel main = new JPanel();

	public Board(int gridSize, String name) {
		super("Battleship");
		setSize(new Dimension(800, 400));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel player1 = new JLabel(name);
		JLabel player2 = new JLabel("Computer");
		
		JPanel header = new JPanel();
		header.setLayout(new FlowLayout());
		header.add(player1);
		header.add(player2);

		JPanel west = createGrid(gridSize);
		JPanel east = createGrid(gridSize);
		main.setLayout(new BorderLayout());
		main.add(west, BorderLayout.LINE_START);
		main.add(east, BorderLayout.LINE_END);
		main.add(header, BorderLayout.PAGE_START);
		this.add(main);
		setVisible(true);
	}
	
	public JPanel createGrid(int gridSize){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(gridSize, gridSize));
		for (int i = 0; i < (gridSize * gridSize); i++) {
			Button b = new Button();
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
	 * 
	 * We have a Class Named Button which extends the java Jbutton Class and
	 * implements the observer Interface ActionEventlistener Button class is the
	 * observer. I've created the variable of instance ImageIcon, if we click on
	 * grid the empty grid box will replace with the image. the method
	 * actionPerformed will call, whenever the one grid box is clicked. the
	 * method setIcon is called and replaced with the right image!
	 * 
	 */

}
