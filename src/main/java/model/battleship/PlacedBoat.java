package model.battleship;
/**
 * @author Thomas Goris, Surendra Sapkota
 *
 */
public class PlacedBoat {

	public final int				x, y;
	public final Boat				boat;
	public final boolean			horizontal;
	public final BattleshipBoard	board;

	public PlacedBoat(int x, int y, Boat boat, boolean horizontal, BattleshipBoard board) {
		this.x = x;
		this.y = y;
		this.horizontal = horizontal;
		this.boat = boat;
		this.board = board;
	}

	public BattleshipCell[] getCells() {
		BattleshipCell[] res = new BattleshipCell[boat.length];
		for (int i = 0; i < res.length; i++) {
			int xx = horizontal ? x + i : x;
			int yy = horizontal ? y : y + i;
			res[i] = board.getCell(xx, yy);
		}
		return res;
	}
	

	public boolean isDead() {
		for (BattleshipCell c : getCells()) {
			if ( !c.isShot()) return false;
		}
		return true;
	}

}
