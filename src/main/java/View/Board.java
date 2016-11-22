package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Board extends JFrame {

	private static final long serialVersionUID = 8506260212595300722L;
	private List<Button> button = new ArrayList<>();
	private JPanel panel = new JPanel();
	private JPanel headerPanel = new JPanel();

	public Board(int gridSize, String name) {
		super("Battleship");
		setSize(new Dimension(400, 400));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// TODO add the name of the player and computer jlabel to the panel
		JLabel computer = new JLabel("Computer");
		JTextField player1 = new JTextField(name);
		// TODO 
		createGrid(gridSize);
		setContentPane(panel);
		setVisible(true);
	}
	
	public void createGrid(int gridSize){
		panel.setLayout(new GridLayout(gridSize, gridSize));
		for (int i = 0; i < (gridSize * gridSize); i++) {
			button.add(new Button());
			panel.add(button.get(i));
		}
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
