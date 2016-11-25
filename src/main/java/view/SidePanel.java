package view;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.battleship.Boat;

public class SidePanel extends JPanel {
	private JLabel nametag;
	private String boat[] = { Boat.AircraftCarrier + "(" + Boat.AircraftCarrier.length + ")",
			Boat.Battleship + "(" + Boat.Battleship.length + ")", Boat.Submarine + "(" + Boat.Submarine.length + ")",
			Boat.Destroyer + "(" + Boat.Destroyer.length + ")", Boat.PatrolShip + "(" + Boat.PatrolShip.length + ")" };

	public SidePanel() {
		this.nametag = new JLabel("Beschikbare Schepen");
		add(nametag);
		revalidate();
	}
}
