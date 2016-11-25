package view;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.battleship.Boat;

public class SidePanel extends JPanel {
	private JLabel nametag;
	private String boat[] = {};

	public SidePanel() {
		this.nametag = new JLabel("Beschikbare Schepen");
		add(nametag);
		revalidate();
	}
	
	private void addAllShip(){
		this.boat = new String[Boat.values().length];
		for(int i=0;i<Boat.values().length;i++){
			boat[i]=Boat.values()[i].toString();
		}
	}
}
