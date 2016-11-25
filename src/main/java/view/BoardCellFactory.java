package view;

public interface BoardCellFactory {

	public BoardCell createCell(int x, int y, int buttonsize);

	public final BoardCellFactory standard =
		(x, y, buttonsize) -> new BoardCell(x, y, buttonsize);
	// public final BoardCellFactory standard = new BoardCellFactory() {
	// public BoardCell createCell(int buttonsize) {
	// return new BoardCell(buttonsize);
	// };
	// }

}
