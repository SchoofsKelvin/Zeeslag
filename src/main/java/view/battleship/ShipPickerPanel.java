package view.battleship;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import model.battleship.BattleshipGame;
import model.battleship.Boat;

public class ShipPickerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JComboBox<BoatStock> box;
	private BoatStock[] stocks;
	private int amount;
	private ButtonGroup buttongroup;
	private JRadioButton horizontal, vertical;
	private DefaultComboBoxModel<BoatStock> model;
	private JButton start;

	public ShipPickerPanel(BattleshipBoardFrame frame) {
		setLayout(new GridLayout(3, 1));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel list = new JPanel(new GridLayout(2, 1));
		add(list);
		list.add(new JLabel("Beschikbare schepen:"));
		list.add(box = new JComboBox<>());
		reset();

		list.setBorder(BorderFactory.createEmptyBorder(10, 0, 50, 0));

		JPanel direction = new JPanel(new GridLayout(2, 1));
		add(direction);
		direction.add(new JLabel("Richting:"));
		JPanel buttons = new JPanel(new GridLayout(1, 2));
		direction.add(buttons);

		buttongroup = new ButtonGroup();
		buttongroup.add(horizontal = new JRadioButton("Horizontal"));
		buttongroup.add(vertical = new JRadioButton("Vertical"));
		direction.add(horizontal);
		direction.add(vertical);
		horizontal.setSelected(true);

		add(start = new JButton("Start game"));
		start.setEnabled(false);
		start.addActionListener(e -> {
			frame.fireGameStarted();
			start.setEnabled(false);
		});
		start.setPreferredSize(new Dimension(200, 50));
	}

	public void reset() {
		amount = 0;
		Boat[] boats = Boat.values();
		stocks = new BoatStock[boats.length];
		for (int i = 0; i < boats.length; i++) {
			stocks[i] = new BoatStock(boats[i]);
		}
		box.setModel(model = new DefaultComboBoxModel<>(stocks));
	}

	public void removeBoat(Boat boat) {
		for (BoatStock stock : stocks) {
			if (stock.boat.equals(boat)) {
				if (stock.decreaseStock() == 0) {
					model.removeElement(stock);
				}
				amount++;
				box.repaint();
			}
		}
		if (isFinished()) {
			start.setEnabled(true);
		}
	}

	public Boat getBoat() {
		if (isFinished())
			return null;
		return ((BoatStock) box.getSelectedItem()).boat;
	}

	public boolean isFinished() {
		return amount == 5;
	}

	public boolean rotationIsHorizontal() {
		return horizontal.isSelected();
	}

	public static class BoatStock {

		public final Boat boat;
		private int stock;

		public BoatStock(Boat boat) {
			this.boat = boat;
			stock = boat.amount;
		}

		public int getStock() {
			return stock;
		}

		public int decreaseStock() {
			return --stock;
		}

		@Override
		public String toString() {
			return boat.toString() + " " + stock + "x";
		}
	}
}
