package model.battleship.ai;

import java.util.Random;

import model.battleship.BattleshipBoard;
import model.battleship.BattleshipCell;
import model.battleship.BattleshipGame;
/**
 * @author Daan Adams, Kelvin Schoofs, Surendra Sapkota
 *
 */
public class FollowUpStrategy extends RandomStrategy {

	public static final StrategyBuilder	builder	= new StrategyBuilder("Follow up Strategy") {

													@Override
													public Strategy build() {
														return new FollowUpStrategy();
													}
												};

	private final static Random			random	= new Random();

	private GoodHit						goodHit	= null;

	@Override
	public void doTurn(BattleshipGame game) {
		BattleshipBoard board = game.board1;
		if (goodHit != null) {
			boolean done = goodHit.doTurn(board);
			if (goodHit.finished()) {
				goodHit = null;
			}
			if (done) return;
		}
		int size = BattleshipGame.gridSize;
		while (true) {
			int x = random.nextInt(size);
			int y = random.nextInt(size);
			BattleshipCell cell = board.getCell(x, y);
			if ( !cell.isShot()) {
				if (game.shoot(x, y)) {
					if (cell.hasBoat()) {
						goodHit = new GoodHit(cell);
					}
					return;
				}
			}
		}
	}

	private static class GoodHit {

		private int		dir		= 0;	// starting south,
										// counterclockwise
		private int		amount	= 1;
		private boolean	knows	= false;
		private boolean	found	= false;
		private boolean	done	= false;
		private int		x, y;

		public GoodHit(BattleshipCell cell) {
			x = cell.x;
			y = cell.y;
		}

		private int getDirX() {
			return dir == 1 ? x + amount : dir == 3 ? x - amount : x;
		}

		private int getDirY() {
			return dir == 0 ? y + amount : dir == 2 ? y - amount : y;
		}

		public boolean doTurn(BattleshipBoard board) {
			if (board.getCell(x, y).hasDeadBoat()) {
				done = true;
				return false;
			}
			if (board.game.shoot(getDirX(), getDirY())) {
				found = board.getCell(getDirX(), getDirY()).hasBoat();
				knows = knows || found;
				amount = found ? amount + 1 : 1;
				if ( !found && ++dir == 4) {
					done = true;
				} else if ( !found && knows) {
					if ((dir += 1) >= 4) {
						done = true;
					}
				}
				return true;
			} else {
				if (++dir == 4) {
					done = true;
					return false;
				} else {
					amount = 1;
				}
				return doTurn(board);
			}
		}

		public boolean finished() {
			return done;
		}

	}

}
