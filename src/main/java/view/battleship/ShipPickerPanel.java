package view.battleship;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import model.battleship.Boat;

public class ShipPickerPanel extends JPanel {

	private static final long	serialVersionUID	= 1L;

	private JComboBox<Boat>		box;
	private ArrayList<Boat>		boats				= new ArrayList<>();

	private ButtonGroup			buttongroup;

	public ShipPickerPanel() {
		setLayout(new GridLayout(2, 1));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel list = new JPanel(new GridLayout(2, 1));
		add(list);
		list.add(new JLabel("Beschikbare schepen:"));
		list.add(box = new JComboBox<Boat>());
		reset();

		list.setBorder(BorderFactory.createEmptyBorder(10, 0, 50, 0));

		JPanel direction = new JPanel(new GridLayout(2, 1));
		add(direction);
		direction.add(new JLabel("Richting:"));
		JPanel buttons = new JPanel(new GridLayout(1, 2));
		direction.add(buttons);

		buttongroup = new ButtonGroup();
		JRadioButton hor = new JRadioButton("Horizontal");
		JRadioButton ver = new JRadioButton("Vertical");
		buttongroup.add(hor);
		buttongroup.add(ver);
		direction.add(hor);
		direction.add(ver);
	}

	public void reset() {
		boats.clear();
		for (Boat b : Boat.values()) {
			boats.add(b);
		}
		box.setModel(new DefaultComboBoxModel<>(boats.toArray(new Boat[boats.size()])));
	}

	public void removeBoat(Boat boat) {
		boats.remove(boat);
		box.setModel(new DefaultComboBoxModel<>(boats.toArray(new Boat[boats.size()])));
	}
}
