package view.battleship;

import java.awt.Color;

public enum CellColor {
	Empty(new Color(100, 197, 255)), Boat(Color.WHITE), Hit(Color.RED), Shot(Color.GRAY);

	public final Color color;

	CellColor(Color col) {
		this.color = col;
	}
}