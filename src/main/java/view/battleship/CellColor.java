package view.battleship;

import java.awt.Color;

public enum CellColor {
	Empty(new Color(100, 197, 255)),
	Boat(Color.WHITE),
	Hit(Color.YELLOW),
	Shot(Color.GRAY),
	Dead(Color.RED);

	public final Color color;

	CellColor(Color col) {
		this.color = col;
	}
}
